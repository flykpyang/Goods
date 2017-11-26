package com.goods.cn.factory;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.netframe.BaseNetAns;
import com.fly.cn.netframe.BaseNetWork;

/**
 * 关注回调
 *
 * @author fly
 * @create 2017-11-17 01:17
 **/

public class CheckSubscribeAns extends BaseNetAns {

    public int subscribe;
    @Override
    public void onReadyAns(BaseNetWork baseNetWork) {

    }

    @Override
    public void onStartAns(BaseNetWork baseNetWork) {

    }

    @Override
    public void onDowningAns(byte[] buf, int len, long downsize, BaseNetWork baseNetWork) {

    }

    @Override
    public void onReceiveAns(BaseNetWork baseNetWork) {
        try {
            String content = new String(baseNetWork.content, "utf-8");
            System.out.println("CheckSubscribeAns="+content);
            JSONObject object = JSONObject.parseObject(content);
            subscribe=object.getInteger("subscribe");

        }catch (Exception e){
            isError=true;
        }
    }

    @Override
    public void onStopAns(BaseNetWork baseNetWork) {

    }

    @Override
    public void onErrorAns(BaseNetWork baseNetWork) {
        isError=true;
    }
}
