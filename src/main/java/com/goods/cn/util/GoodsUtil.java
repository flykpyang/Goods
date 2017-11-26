package com.goods.cn.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.cn.util.TimeUtil;
import com.fly.cn.util.WeiXinCompare;
import com.goods.cn.action.Gift;
import com.goods.cn.po.*;

import java.util.*;
import java.util.Map.Entry;

import static com.fly.cn.util.ToolUtil.shaUtil;

/**
 * Created by fly on 17/3/30.
 */
public class GoodsUtil {

    /**
     * 首次验证
     */
    //signature != null && timestamp != null && echostr != null
    public static boolean fristValidate(String signature, String timestamp, String echostr, String nonce) {
        List<String> strings = new ArrayList<String>();
        strings.add("goods");
        strings.add(timestamp);
        strings.add(nonce);
        Collections.sort(strings, new WeiXinCompare());
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < strings.size(); i++) {
            buffer.append(strings.get(i));
        }
        String result = shaUtil(buffer.toString());
        //System.out.println("signature=" + signature + "  result=" + result);
        if (result.equals(signature)) {
            return true;
        }
        return false;
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
        signMap.put("jsapi_ticket", ticket);
        StringBuffer buffer = new StringBuffer();
        Iterator iterator = signMap.entrySet().iterator();
        int j = 0;
        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (j == 0) {
                buffer.append(key + "=" + value);
            } else {
                buffer.append("&" + key + "=" + value);
            }
            j++;
        }
        String result2 = shaUtil(buffer.toString());
        return result2;
    }




    /**
     * 把treemap 转化为json
     *
     * @param map
     * @return
     */
    public static String coverMapToJson(TreeMap<String, WaterAction> map) {
        try {
            Iterator iterator = map.entrySet().iterator();
            JSONArray array = new JSONArray();
            while (iterator.hasNext()) {
                Entry entry = (Entry) iterator.next();
                String key = (String) entry.getKey();
                WaterAction value = (WaterAction) entry.getValue();
                JSONObject object = new JSONObject();
                object.put("date", key);
                object.put("iswater", value.iswater);
                Gift gift = value.gift;
                if (gift != null) {
                    if (gift.userCards != null && gift.userCards.size() > 0) {
                        List<Discard> disCards = gift.userCards;
                        JSONArray cardArray = new JSONArray();
                        for (int i = 0; i < disCards.size(); i++) {
                            Discard card = disCards.get(i);
                            JSONObject cardobject = new JSONObject();
                            cardobject.put("iden", card.getcId());
                            cardArray.add(cardobject);
                        }
                        object.put("card", cardArray);
                    }
                    if (gift.coffeesore > 0) {
                        object.put("sore", gift.coffeesore);
                    }
                }
                array.add(object);
            }
            return array.toString();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 把礼物加入json
     *
     * @param gift
     * @param object
     */
    public static void coverGiftToJson(Gift gift, JSONObject object) {
        try {
            List<Discard> disCards = gift.userCards;
            int sore = gift.coffeesore;
            String remark = gift.remark;
            JSONObject giftobject = new JSONObject();
            JSONArray array = new JSONArray();
            if (disCards != null && disCards.size() > 0) {
                for (int i = 0; i < disCards.size(); i++) {
                    Discard disCard = disCards.get(i);
                    JSONObject discardobject = new JSONObject();
                    discardobject.put("identification", disCard.getcIdentification());
                    discardobject.put("name", disCard.getcName());
                    discardobject.put("value", disCard.getcValue());
                    if (disCard.getcRemark() != null && !disCard.getcRemark().equals("")) {
                        discardobject.put("remark", disCard.getcRemark());
                    }
                    discardobject.put("pic", disCard.getcPic());
                    array.add(discardobject);
                }
                giftobject.put("card", array);
            }
            if (sore > 0) {
                giftobject.put("sore", sore);
            }
            object.put("gift", giftobject);
        } catch (Exception e) {

        }
    }


    /**
     * 把discard 转化为usercard
     *
     * @param disCard
     * @return
     */
    public static Usercard coverUserCardByDisCard(Discard disCard, UserInfo userInfo, int from, Object c) {

        Usercard userCard = new Usercard();
        String openid = userInfo.getcOpenid();
        userCard.setcOpenid(openid);
        String phone = userInfo.getcPhone();
        userCard.setcPhone(phone);
        String cardid = LockUtil.creatDisCardCount();
        userCard.setcCardid(cardid);
        int timevalue = disCard.getcEffecttime();
        Date exDate = TimeUtil.creatAddDay(timevalue, new Date());
        userCard.setcExpiretime(exDate);
        int iden = disCard.getcIdentification();
        userCard.setcIdentification(iden);
        String cardname = disCard.getcName();
        userCard.setcCardname(cardname);
        String carddes = disCard.getcDes();
        userCard.setcCarddes(carddes);
        String cardpic = disCard.getcPic();
        userCard.setcCardpic(cardpic);
        int cardvalue = disCard.getcValue();
        userCard.setcCardvalue(String.valueOf(cardvalue));
        userCard.setcCardremark(disCard.getcRemark());
        userCard.setcIsuse(0);
        userCard.setcAppid(userInfo.getcAppid());
        userCard.setcFrom(from);
        if (c != null && (c instanceof OrderInfo)) {
            OrderInfo orderInfo = (OrderInfo) c;
            userCard.setcFromorderid(orderInfo.getcOrderid());
        }
        return userCard;

    }


    /**
     * 把realgoos 转化为userrealgoos
     *
     * @param realGoods
     * @param userInfo
     * @return
     */
    public static Userreal coverUserRealGoodsByRealGoods(Realgoods realGoods, UserInfo userInfo,int addrid) {

        Userreal userReal = new Userreal();
        String openid = userInfo.getcOpenid();
        userReal.setcOpenid(openid);
        int id = realGoods.getcId();
        String name = realGoods.getcName();
        userReal.setcRealid(String.valueOf(id));
        userReal.setcIsuse(0);
        userReal.setcRealname(name);
        userReal.setcImg(realGoods.getcPic());
        userReal.setcAppid(userInfo.getcAppid());
        userReal.setcAddrid(addrid);
        return userReal;

    }

}
