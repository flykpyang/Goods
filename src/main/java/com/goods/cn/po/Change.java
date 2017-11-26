package com.goods.cn.po;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fly on 17/5/17.
 */
public class Change {

    private List<Discard> disCards;
    private List<Realgoods> realGoods;
    public int totalsore;
    private String openid;
    private Integer appid;
    private int     addrid;

    public int getAddrid() {
        return addrid;
    }

    public void setAddrid(int addrid) {
        this.addrid = addrid;
    }

    public List<Discard> getDisCards() {
        return disCards;
    }

    public void setDisCards(List<Discard> disCards) {
        this.disCards = disCards;
    }

    public List<Realgoods> getRealGoods() {
        return realGoods;
    }

    public void setRealGoods(List<Realgoods> realGoods) {
        this.realGoods = realGoods;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public static void main(String[] agrs){
        Change change=new Change();
        change.appid=0;
        change.openid="abcddsssds";
        List<Discard> discards=new ArrayList<Discard>();
        Discard discard=new Discard();
        discard.setcId(1);
        discard.setcIdentification(1);
        discard.setcSore(200);
        discards.add(discard);
        change.disCards=discards;
        List<Realgoods> realGoods=new ArrayList<Realgoods>();
        Realgoods realgoods=new Realgoods();
        realgoods.setcId(1);
        realgoods.setcSore(500);
        realGoods.add(realgoods);
        change.realGoods=realGoods;
        String json= JSONObject.toJSONString(change);
        System.out.println(json);
    }
}
