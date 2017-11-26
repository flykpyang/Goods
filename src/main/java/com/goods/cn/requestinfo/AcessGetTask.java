package com.goods.cn.requestinfo;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.netframe.BaseNetWork;
import com.fly.cn.netframe.INetWorkAgecny;
import com.fly.cn.netframe.RequestInfo;
import com.goods.cn.config.BaseConfig;

/**
 * Created by fly on 17/4/10.
 */
public class AcessGetTask implements INetWorkAgecny, Runnable {


    public AcessGetTask(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
    }

    String appid;
    String secret;


    public void onReady(BaseNetWork baseNetWork) {

    }


    public void onStart(BaseNetWork baseNetWork) {

    }


    public void onDownloading(byte[] buf, int len, long downsize, BaseNetWork baseNetWork) {

    }


    public void onReceive(BaseNetWork baseNetWork) {
        String cmd = baseNetWork.requestInfo.requestKey;
        if (cmd.equals("token")) {
            try {
                String conetent = new String(baseNetWork.content, "utf-8");
                System.out.println("content=" + conetent);
                int n = conetent.indexOf("=");
                conetent = conetent.substring(n + 1);
                JSONObject object = JSONObject.parseObject(conetent);
                //System.out.println("object=" + object);
                if (!object.containsKey("errcode")) {
                    String aceessthoken = object.getString("access_token");
                    BaseConfig.aceessthokenMap.put(appid, aceessthoken);
                    //System.out.println("新的token=" + aceessthoken);
                    // 抓取用户信息
                    // https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
                    RequestInfo info = new RequestInfo();
                    info.requestKey = "ticket";
                    info.url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                            + aceessthoken
                            + "&type=jsapi";
                    NormalRequest normalRequest = new NormalRequest(info);
                    normalRequest.netWorkAgecny = this;
                    normalRequest.startNetWork();
                }
            } catch (Exception e) {

            }
        } else if (cmd.equals("ticket")) {
            try {
                String conetent = new String(baseNetWork.content, "utf-8");
                System.out.println("content=" + conetent);
                int n = conetent.indexOf("=");
                conetent = conetent.substring(n + 1);
                JSONObject object = JSONObject.parseObject(conetent);
                //System.out.println("object=" + object);
                if (object.containsKey("errcode") && (Integer) (object.get("errcode")) == 0) {
                    String ticket = object.getString("ticket");
                    BaseConfig.ticketmap.put(appid, ticket);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }


    public void onStop(BaseNetWork baseNetWork) {

    }


    public void onError(BaseNetWork baseNetWork) {

    }


    public void run() {
        RequestInfo info = new RequestInfo();
        info.requestKey = "token";
        info.url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
                + "&appid=" + appid + "&secret=" + secret;
        NormalRequest normalRequest = new NormalRequest(info);
        normalRequest.netWorkAgecny = this;
        normalRequest.startNetWork();
    }
}
