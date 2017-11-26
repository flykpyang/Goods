package com.goods.cn.service;

import com.goods.cn.config.BaseConfig;
import com.goods.cn.po.*;
import com.goods.cn.util.BaseUtil;
import com.goods.cn.util.GoodsCompare;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

@Service("GoodsInfoService")
public class GoodsInfoService extends BaseService{

    public UserLoveInfo findUserLove(String openid,String goodsid,int appid,int lid)throws Exception{
        UserLoveInfo userLoveInfo=new UserLoveInfo();
        Love love=loveDao.findLoveById(lid);
        if(love!=null){
            userLoveInfo.islove=true;
        }
        long count=loveDao.findLoveGoods(goodsid,appid);
        userLoveInfo.loveCount=count;
        userLoveInfo.goodsid=goodsid;
        userLoveInfo.openid=openid;
        return userLoveInfo;
    }


    public void save(GoodsInfo goodsInfo)throws Exception{
        goodsInfoDao.save(goodsInfo);
    }

    public GoodsInfo findGoodsInfoByid(String goodsid) throws Exception{
        GoodsInfo goodsInfo=goodsInfoDao.findGoodsInfoByid(goodsid);
        return goodsInfo;
    }

    public TreeMap<Type, List<GoodsInfo>> findAllGoodsInfoByAppId(String openid, int appid) throws Exception {
        //System.out.println("findAllGoodsInfoByAppId");
        UserInfo userInfo = userDao.checkUserByOpenid(openid,appid);
        List<GoodsInfo> goodsInfos = goodsInfoDao.findAllUseGoodsList(appid);
        String fav = "";
        if (userInfo != null) {
            fav = userInfo.getcFav();
        }
        String[] favs = BaseUtil.coverFav(fav);
        TreeMap<Type, List<GoodsInfo>> treeMapCoffee = new TreeMap<Type, List<GoodsInfo>>(new GoodsCompare());
        creatListCoffees(treeMapCoffee, goodsInfos, favs,openid,appid);
        return treeMapCoffee;
    }


    /**
     * //根据用户的喜好重新排序
     * @param treeMapCoffee
     * @param goodsInfos
     * @param goodss
     * @param openid
     * @param appid
     * @throws Exception
     */
    private void creatListCoffees(TreeMap<Type, List<GoodsInfo>> treeMapCoffee, List<GoodsInfo> goodsInfos, String[] goodss,String openid,int appid) throws Exception {
        //cover to map
        int goodssize = goodsInfos.size();
        HashMap<String, GoodsInfo> goodsInfoHashMap = new HashMap<String, GoodsInfo>();
        for (int i = 0; i < goodssize; i++) {
            GoodsInfo goodsInfo = goodsInfos.get(i);
            String goodsid = goodsInfo.getcGoodsid();
            creatGoodsInfoCheckAndCount(goodsInfo,openid,appid);
            int type = goodsInfo.getcType();

            //TODO
            Type ctype = typeDao.getTypeById(type);
            if (ctype != null) {
                if (treeMapCoffee.containsKey(ctype)) {
                    List<GoodsInfo> coffeesList = treeMapCoffee.get(ctype);
                    coffeesList.add(goodsInfo);
                } else {
                    List<GoodsInfo> coffeesList = new ArrayList<GoodsInfo>();
                    coffeesList.add(goodsInfo);
                    treeMapCoffee.put(ctype, coffeesList);
                }
            }
            goodsInfoHashMap.put(goodsid, goodsInfo);
        }

        //生成推荐
        if (goodss != null && goodss.length > 0) {
            int size = goodss.length;
            List<GoodsInfo> remendgoods = new ArrayList<GoodsInfo>();
            for (int i = 0; i < size; i++) {
                String coffeeid = goodss[i];
                if (coffeeid != null && coffeeid.trim().length() > 6 && !coffeeid.equals("")) {
                    GoodsInfo favgoods = goodsInfoHashMap.get(coffeeid);
                    //可能被下架了
                    if(favgoods!=null) {
                        int type = favgoods.getcType();
                        //TODO
                        Type ctype = typeDao.getTypeById(type);
                        List<GoodsInfo> list = treeMapCoffee.get(ctype);
                        if (list != null) {
                            list.remove(favgoods);
                        }
                        remendgoods.add(favgoods);
                    }
                }

            }
            //TODO
            Type rementType = typeDao.getTypeById(BaseConfig.RECOMMENTTYPE);
            treeMapCoffee.put(rementType, remendgoods);
        }
    }

    /**
     * 是否关注和关注人数更新
     * @param goodsInfo
     * @param openid
     * @param appid
     * @throws Exception
     */
    private void creatGoodsInfoCheckAndCount(GoodsInfo goodsInfo,String openid,int appid)throws Exception{
        String goodsid=goodsInfo.getcGoodsid();
        //给出是否关注
        Love love= loveDao.findLoveByOpenidAndAppidAndGoodsId(openid,appid,goodsid);
        if(love!=null){
            goodsInfo.islove=true;
        }
        //关注总数
        long count=loveDao.findLoveGoods(goodsid,appid);
        goodsInfo.setcLikecount((int)count);
    }
}
