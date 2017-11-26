package com.goods.cn.service;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.MessageManager;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.observer.message.ChangeRealGoodsMessage;
import com.goods.cn.po.*;
import com.goods.cn.util.GoodsUtil;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service("GoodsMallService")
public class GoodsMallService extends BaseService {

    /**
     * 获得所有的可兑换商品
     *
     * @param openid
     * @return
     * @throws Exception
     */
    public GetChangeAllModel getAllChange(String openid,int appid) throws Exception {
        List<Discard> disCards = disCardDao.findIsChangeCard(appid);
        List<Realgoods> realGoods = realGoodsDao.findIsChangeRealGoods(appid);
        GetChangeAllModel getChangeAllModel = new GetChangeAllModel();
        getChangeAllModel.disCards = disCards;
        getChangeAllModel.realGoods = realGoods;
        return getChangeAllModel;
    }

    /**
     * 拿到用户的所有商品
     *
     * @param openid
     * @param isuse
     * @return
     * @throws Exception
     */
    public UserChangeModel getAllUserChangeGoods(String openid, int isuse,int appid) throws Exception {
        boolean use = false;
        if (isuse == 1) {
            use = true;
        }
        List<Usercard> userCards = userCardDao.findAllCardByOpenid(openid,appid,use);
        List<Userreal> userReals = userRealDao.findAllUserRealByOpenid(openid, use,appid);
        UserChangeModel userChangeModel = new UserChangeModel();
        userChangeModel.userCards = userCards;
        userChangeModel.userReals = userReals;
        return userChangeModel;
    }

    /**
     * 兑换
     * @param change
     */
    public void  changeCoffeeGoods(Change change) throws Exception{
        String openid=change.getOpenid();
        int  addrid=change.getAddrid();
        int appid=change.getAppid();
        UserInfo userInfo = userDao.checkUserByOpenid(openid,change.getAppid());
        if(userInfo!=null){
            int sore=userInfo.getcSore();
            int cosume=0;
            List<Discard> discards=change.getDisCards();
            if(discards!=null&&discards.size()>0){
                for(Discard discard:discards){
                    //计算
                    //入库
                    Discard realdiscard = disCardDao.findDisCardById(discard.getcId());
                    Usercard userCard = GoodsUtil.coverUserCardByDisCard(realdiscard, userInfo, BaseConfig.CHANGEFROM,null);
                    cosume+=realdiscard.getcSore();
                    userCardDao.save(userCard);
                }
            }
            List<Realgoods> realgoods=change.getRealGoods();
            List<Userreal>  userreals=new ArrayList<Userreal>();
            if(realgoods!=null&&realgoods.size()>0){
                for(Realgoods realGood : realgoods){
                    //入库
                    Realgoods goods=realGoodsDao.findRealGoodsById(realGood.getcId());
                    Userreal userReal= GoodsUtil.coverUserRealGoodsByRealGoods(goods,userInfo,addrid);
                    userreals.add(userReal);
                    cosume+=goods.getcSore();
                    userRealDao.save(userReal);
                }
            }
            if(sore>=cosume){
                int realsore=sore-cosume;
                userInfo.setcSore(realsore);
                userDao.update(userInfo);
                //发送消息
                ChangeRealGoodsMessage realGoodsMessage=new ChangeRealGoodsMessage(MessageConfig.CHANGEREALGOODS,userreals,appid);
                MessageManager.getInstanceMessage().putMessage(realGoodsMessage);
            }else{
                throw  new UnsupportedOperationException("兑换错误");
            }
        }
    }


    public Realgoods  findRealGoods(int id)throws Exception{
        return realGoodsDao.findRealGoodsById(id);
    }

}
