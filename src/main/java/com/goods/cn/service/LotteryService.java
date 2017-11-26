package com.goods.cn.service;

import com.alibaba.fastjson.JSONArray;

import com.fly.cn.lottery.LotteryManager;
import com.fly.cn.lottery.Prize;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.po.*;
import com.goods.cn.util.GoodsUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("lotteryService")
public class LotteryService extends BaseService {

    /**
     * 找到当天可以抢购的活动
     *
     * @param openid
     * @return
     * @throws Exception
     */
    public Lottery findCurrUserfulLottery(String openid,int appid) throws Exception {
        Lotterylog lotterylog = lotteryLogDao.findLogByOpenid(openid, TimeUtil.getCurrDay(0),appid);
        Lottery lottery = new Lottery();
        if (lotterylog == null) {
            lottery = findUseLotteryAction(appid);
        }
        return lottery;
    }

    public Lottery findUseLotteryAction(int appid) throws Exception {
        Lottery lottery = lotteryDao.findUserfulLotteryAction(appid);
        if (lottery != null) {
            String lists = lottery.getcPrizeid();
            JSONArray array = JSONArray.parseArray(lists);
            List<Realprize> realprizes = new ArrayList<Realprize>();
            for (int i = 0; i < array.size(); i++) {
                String pid = array.getString(i);
                Realprize realprize = realPrizeDao.getRealPrizeById(Integer.valueOf(pid));
                if (realprize != null) {
                    String key=realprize.getcKey();
                    realprizes.add(realprize);
                }
            }
            lottery.realprizes = realprizes;
            lottery.leftPrizenumber=LotteryManager.getIntanceLotteryManager().getLeftNumberPrice(appid);
        }
        return lottery;
    }

    /**
     * 抽奖抢购
     *
     * @param openid
     * @return
     * @throws Exception
     */
    public Prize lottery(String openid,int appid) throws Exception {
        Lotterylog lotterylog = lotteryLogDao.findLogByOpenid(openid, TimeUtil.getCurrDay(0),appid);
        if (lotterylog == null) {
            //在抽奖时间范围类
            Lottery lottery = lotteryDao.findUserfulLotteryAction(appid);
            if (lottery != null && lottery.bc == 0) {
                Prize prize = LotteryManager.getIntanceLotteryManager().peopleLottery(appid);
                //插入log
                lotterylog = new Lotterylog();
                lotterylog.setcOpenid(openid);
                if (prize != null) {
                    lotterylog.setcPrizekey(prize.value);
                    byte type=prize.type;
                    String key=prize.key;
                    UserInfo userInfo=userDao.checkUserByOpenid(openid,appid);
                    if(type==(byte)0){
                        //卡卷 入库
                        Discard discard=disCardDao.findDisCardById(Integer.valueOf(key));
                        if(userInfo!=null&&discard!=null) {
                            Usercard usercard = GoodsUtil.coverUserCardByDisCard(discard,userInfo,
                                    BaseConfig.LOTTERYFROM,null);
                            userCardDao.save(usercard);
                        }
                    }else if(type==(byte)1){
                        //实物入库
                        Realgoods realgoods=realGoodsDao.findRealGoodsById(Integer.valueOf(key));
                        if(userInfo!=null&&realgoods!=null){
                            List<Useraddress> useraddress=userAddressDao.findUserAddress(openid,appid);
                            if(useraddress!=null&&useraddress.size()>0) {
                                int addrid=useraddress.get(0).getcId();
                                Userreal userReal = GoodsUtil.coverUserRealGoodsByRealGoods(realgoods, userInfo, addrid);
                                userRealDao.save(userReal);
                            }
                        }
                    }
                }
                lotteryLogDao.save(lotterylog);
                return prize;
            }
        }
        return null;
    }
}
