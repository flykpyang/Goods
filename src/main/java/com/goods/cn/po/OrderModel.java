package com.goods.cn.po;

/**
 * Created by fly on 17/5/26.
 */
public class OrderModel {

    public String appId;
    public String timeStamp;
    public String preid;
    public String nonceStr;
    public String signType="MD5";
    public String paySign;
    public int    isfree=0;
    public String orderid;
    public int    addsore=0;
}
