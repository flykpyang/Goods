package com.goods.cn.requestinfo;


import com.fly.cn.netframe.BaseHttpNetWork;
import com.fly.cn.netframe.RequestInfo;

/**
 * Created by fly on 17/4/7.
 */
public class GetOpenidAndAcessTokenRequest extends BaseHttpNetWork {
    public GetOpenidAndAcessTokenRequest(RequestInfo info) {
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

    }
}
