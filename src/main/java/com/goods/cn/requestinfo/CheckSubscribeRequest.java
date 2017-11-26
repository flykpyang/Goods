package com.goods.cn.requestinfo;

import com.fly.cn.netframe.BaseHttpNetWork;
import com.fly.cn.netframe.RequestInfo;
import com.goods.cn.config.BaseConfig;

/**
 * 检测用户是否关注了公众号
 *
 * @author fly
 * @create 2017-11-17 01:00
 **/

public class CheckSubscribeRequest extends BaseHttpNetWork{

    public CheckSubscribeRequest(RequestInfo info) {
        super(info);
        method="GET";
    }

    @Override
    public void setHeader() {

    }

    @Override
    public byte[] setBody(RequestInfo info) {
        return new byte[0];
    }

    @Override
    public void modifyUrl(RequestInfo info) {
        String url=info.url;
        String openid=info.openid;
        String acess= BaseConfig.aceessthokenMap.get(info.appId);
        info.url=url+"?access_token="+acess+"&openid="+openid+"&lang=zh_CN";
    }
}
