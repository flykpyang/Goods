package com.goods.cn.util;

import com.fly.cn.lottery.LotteryManager;
import com.fly.cn.lottery.Prize;
import com.fly.cn.util.WeiXinCompare;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Randiscount;
import com.goods.cn.po.Realprize;
import com.goods.cn.po.UserInfo;

import java.util.*;

import static com.fly.cn.util.ToolUtil.shaUtil;

/**
 * Created by fly on 17/7/18.
 */
public class BaseUtil {
    /**
     * 生成新的用户
     *
     * @param openid
     * @return
     */
    public static UserInfo creatNewUserInfo(String openid,int appid) {
        UserInfo userInfo = new UserInfo();
        userInfo.setcOpenid(openid);
        userInfo.setcPhone("");
        userInfo.setcName("");
        userInfo.setcAppid(appid);
        userInfo.setcSore(0);
        return userInfo;
    }

    /**
     * 转化
     *
     * @param orderInfo
     * @return
     */
    public static UserInfo coverOrderToUser(OrderInfo orderInfo) {

        UserInfo userInfo = new UserInfo();
        String openid = orderInfo.getcOpenid();
        userInfo.setcOpenid(openid);
        userInfo.setcAppid(orderInfo.getcAppid());
        userInfo.setcSore(0);
        return userInfo;
    }


    public static void initLottyer(List<Randiscount> randDisPrices,int appid) throws Exception {
        if (randDisPrices != null) {
            List<Prize> prizes = new ArrayList<Prize>(randDisPrices.size());
            for (Randiscount randDisPrice : randDisPrices) {
                Prize prize = new Prize();
                prize.poll = randDisPrice.getcPoll();
                prize.key = randDisPrice.getcKey();
                prize.value = String.valueOf(randDisPrice.getcValue());
                prizes.add(prize);
            }
            LotteryManager.getIntanceLotteryManager().createTurningLottery(prizes,appid);
        }
    }

    public static void initPeopleLottery(List<Realprize> realprizes,int appid) {
        List<Prize> prizes = new ArrayList<Prize>(realprizes.size());
        int number = 0;
        for (int i = 0; i < realprizes.size(); i++) {
            Prize prize = new Prize();
            Realprize realprize = realprizes.get(i);
            prize.poll = realprize.getcPoll();
            number += prize.poll;
            prize.key = realprize.getcKey();
            prize.value = realprize.getcValue();
            prize.type = realprize.getcType();
            prizes.add(prize);
        }
        LotteryManager.getIntanceLotteryManager().createPeopleLottery(prizes, number,appid);
    }


    //根据fav生成idlist
    public static String[] coverFav(String fav) {
        if (fav != null && fav.trim().length() > 0) {
            String[] favs = fav.split(",");
            return favs;
        }
        return null;
    }

    /**
     * 扫一扫排序
     *
     * @param noncestr
     * @param timestamp
     * @param url
     * @return
     */
    public static String saoSig(String noncestr, String timestamp, String url,String ticket) {
        TreeMap<String, Object> signMap = new TreeMap<String, Object>(
                new WeiXinCompare());
        signMap.put("noncestr", noncestr);
        signMap.put("timestamp", timestamp);
        signMap.put("url", url);
        signMap.put("jsapi_ticket",ticket);
        StringBuffer buffer = new StringBuffer();
        Iterator iterator = signMap.entrySet().iterator();
        creatBufferByIterator(iterator,buffer);
        String result2 = shaUtil(buffer.toString());
        return result2;
    }

    public static void creatBufferByIterator(Iterator iterator,StringBuffer buffer){
        int j = 0;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (j == 0) {
                buffer.append(key + "=" + value);
            } else {
                buffer.append("&" + key + "=" + value);
            }
            j++;
        }
    }
}
