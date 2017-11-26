package com.goods.cn.util;

import com.fly.cn.netframe.BaseNetAns;
import com.fly.cn.netframe.NetService;
import com.fly.cn.netframe.RequestInfo;
import com.fly.cn.util.CipherUtil;
import com.fly.cn.util.WeiXinCompare;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.config.CgiConfig;
import com.goods.cn.po.App;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;
import com.goods.cn.po.WxModel;
import com.goods.cn.requestinfo.WeiXinPayRequestInfo;
import net.sf.ehcache.util.concurrent.ThreadLocalRandom;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by fly on 17/7/7.
 */
public class WxPayUtil {

    /**
     * 根据order生成请求的info
     * @param order
     * @return
     */
    public static WeiXinPayRequestInfo creatWeiXinPayInfo(OrderInfo order, App app) {
        WeiXinPayRequestInfo info = new WeiXinPayRequestInfo(app.getcAppid(),app.getcMchid(),app.getcPaykey());
        info.requestKey = CgiConfig.WXORDER;
        info.url = BaseConfig.WXORDERURL;
        info.body = app.getcAppname();
        info.out_trade_no = order.getcOrderid();
        String price = order.getcTotalPrice();
        String dprice = String.valueOf(Double.valueOf(price) * 100);
        String[] prStrings = dprice.split("\\.");
        info.total_fee = Integer.valueOf(prStrings[0]);
        info.notify_url = BaseConfig.WXCALLBACKURL;
        info.openid = order.getcOpenid();
        // ++
        info.attach = "-1";
        List<Usercard> userCards = order.disCards;
        if (userCards != null && userCards.size() > 0) {
            StringBuffer tempcardid = new StringBuffer();
            for (int i = 0; i < userCards.size(); i++) {
                tempcardid.append(userCards.get(i).getcCardid() + ",");
            }
            info.attach = tempcardid.toString();
        }
        info.creatSign();
        return info;
    }

    /**
     * 发起网络请求
     * @param info
     * @return
     */
    public static BaseNetAns startSendWeiXin(RequestInfo info) {
        return NetService.getInstance().startSynHttpNetWork(info);
    }

    /**
     * 生成sign
     * @param appId
     * @param timeStamp
     * @param nonceStr
     * @param preid
     * @return
     */
    public static String computeSign(String appId,String timeStamp,String nonceStr,String preid,String paykey) {
        TreeMap<String, String> weixinMap = new TreeMap<String, String>(
                new WeiXinCompare());
        weixinMap.put("appId", appId);
        weixinMap.put("timeStamp", timeStamp);
        weixinMap.put("nonceStr", nonceStr);
        weixinMap.put("package", preid);
        weixinMap.put("signType", "MD5");
        StringBuffer buffer = new StringBuffer();
        Iterator iterator = weixinMap.entrySet().iterator();
        BaseUtil.creatBufferByIterator(iterator,buffer);
        String singstr = buffer.append("&key="+paykey)
                .toString();
        //System.out.println("buffer=" + singstr);
        String sign = CipherUtil.generatePassword(singstr).toUpperCase();
        return sign;
    }


    /**
     *
     * @param wxMap
     * @return
     */
    public  static String validateSignByMap(TreeMap<String,String> wxMap,String paykey) {
        StringBuffer buffer = new StringBuffer();
        Iterator iterator = wxMap.entrySet().iterator();
        BaseUtil.creatBufferByIterator(iterator,buffer);
        String singstr = buffer.append("&key=" + paykey)
                .toString();
        //System.out.println("buffer=" + singstr);
        String sign = CipherUtil.generatePassword(singstr).toUpperCase();
        return sign;
    }

    /**
     * 拉起wx内部的方法
     *
     * @param openid
     * @return
     */
    public static WxModel getWxTicket(String openid,String appid,int aid,String url) {
        long count = ThreadLocalRandom.current().nextLong(1000000000);
        String noncestr = String.valueOf(count);
        long timestamp = System.currentTimeMillis() / 1000;
        String ticket=BaseConfig.ticketmap.get(appid);
        //System.out.println("url=" + url);
        String signature = BaseUtil.saoSig(noncestr, String.valueOf(timestamp), url,ticket);
        WxModel wxModel = new WxModel();
        wxModel.noncestr = noncestr;
        wxModel.signature = signature;
        wxModel.saotimestamp = timestamp;
        wxModel.url = url;
        wxModel.appId=appid;
        return wxModel;
    }
}
