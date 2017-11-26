package com.goods.cn.requestinfo;


import com.fly.cn.netframe.RequestInfo;
import com.fly.cn.util.CipherUtil;
import com.fly.cn.util.WeiXinCompare;
import com.goods.cn.util.BaseUtil;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by fly on 17/4/5.
 */
public class WeiXinPayRequestInfo extends RequestInfo {

    public String appid;
    public String attach;
    public String mch_id;
    public String device_info;
    public String nonce_str;
    public String body;
    public String detail;
    public String out_trade_no;
    public String fee_type = "CNY";
    public int total_fee;
    public String spbill_create_ip;
    public String notify_url;
    public String trade_type;
    public String sign;
    public TreeMap<String, String> signMap = new TreeMap<String, String>(
            new WeiXinCompare());
    private String paykey;

    public WeiXinPayRequestInfo(String appid,String mch_id,String paykey) {
        Random tRandom = new Random();
        Long count = Math.abs(tRandom.nextLong());
        nonce_str = String.valueOf(count);
        this.appid =appid;
        device_info = "WEB";
        this.mch_id = mch_id;
        this.paykey=paykey;
        trade_type = "JSAPI";
        spbill_create_ip = "10.189.88.81";
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println(ip);
            spbill_create_ip = ip;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void initSign() {
        sign = creatSign();
    }

    public String creatSign() {
        signMap.put("appid", appid);
        signMap.put("mch_id", mch_id);
        signMap.put("device_info", device_info);
        signMap.put("nonce_str", nonce_str);
        signMap.put("body", body);
        signMap.put("out_trade_no", out_trade_no);
        signMap.put("fee_type", fee_type);
        signMap.put("total_fee", String.valueOf(total_fee));
        signMap.put("spbill_create_ip", spbill_create_ip);
        signMap.put("notify_url", notify_url);
        signMap.put("trade_type", trade_type);
        signMap.put("openid", openid);
        signMap.put("attach", attach);
        StringBuffer buffer = new StringBuffer();
        Iterator iterator=signMap.entrySet().iterator();
        BaseUtil.creatBufferByIterator(iterator,buffer);
        String singstr = buffer.append("&key="+paykey)
                .toString();
        System.out.println("buffer=" + singstr);
        String sign = CipherUtil.generatePassword(singstr).toUpperCase();
        signMap.put("sign", sign);
        return sign;
    }
}
