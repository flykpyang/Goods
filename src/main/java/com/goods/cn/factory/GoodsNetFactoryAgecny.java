package com.goods.cn.factory;

import com.fly.cn.netframe.BaseNetAns;
import com.fly.cn.netframe.BaseNetWork;
import com.fly.cn.netframe.INetFactoryAgecny;
import com.fly.cn.netframe.RequestInfo;
import com.fly.cn.requestinfo.TempTestAns;
import com.goods.cn.config.CgiConfig;
import com.goods.cn.requestinfo.*;
import org.springframework.stereotype.Component;

@Component("NetFactoryAgecny")
public class GoodsNetFactoryAgecny implements INetFactoryAgecny {

    public BaseNetWork creatHttpNetWork(RequestInfo info) {
        String key = info.requestKey;
        if (CgiConfig.WXORDER.equals(key)) {
            return new WeiXinPayRequest(info);
        } else if (CgiConfig.WXORDERTEMPLATE.equals(key)) {
            return new WeiXinTempGetOrderRequest(info);
        } else if (CgiConfig.WXASKADVICETEMPLATE.equals(key)) {
            return new WeiXinTempAskRequest(info);
        } else if(CgiConfig.WXSUBSCRIBE.equals(key)){
            return new CheckSubscribeRequest(info);
        } else if(CgiConfig.WXCHANGETEMPLATE.equals(key)){
            return new WeixinTempChangeRequest(info);
        }
        return new NormalRequest(info);
    }

    public BaseNetAns creatHttpAns(RequestInfo info) {
        String key = info.requestKey;
        if (CgiConfig.WXORDER.equals(key)) {
            return new WeiXinOrderNetAns();
        } else if (CgiConfig.WXOPENID.equals(key)) {
            return new WxOpenidNetAns();
        }else if(CgiConfig.WXSUBSCRIBE.equals(key)){
            return new CheckSubscribeAns();
        }
        return new TempTestAns();
    }
}
