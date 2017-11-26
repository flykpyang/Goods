package com.goods.cn.controller;

import com.fly.cn.util.ErrorMsg;

import java.util.HashMap;

/**
 * Created by fly on 17/6/12.
 * 返回协议代码
 */
public class SupplyMap extends HashMap<Object, Object> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SupplyMap() {
        super();
        put(ErrorMsg.RECODEKEY, ErrorMsg.STATUS_OK);
        put(ErrorMsg.RETMSG, ErrorMsg.MSG_STATUS_OK);
        put(ErrorMsg.TIMESRAMP, System.currentTimeMillis());
    }

    public void setEorrInfo(int errCode, String errorMsg) {
        put(ErrorMsg.RECODEKEY, errCode);
        put(ErrorMsg.RETMSG, errorMsg);
    }
}
