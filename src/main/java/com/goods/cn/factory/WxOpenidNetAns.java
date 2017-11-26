package com.goods.cn.factory;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.netframe.BaseNetAns;
import com.fly.cn.netframe.BaseNetWork;
import com.goods.cn.config.BaseConfig;


public class WxOpenidNetAns extends BaseNetAns {

    public String realUrl;

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
            String conetent = new String(baseNetWork.content, "utf-8");
            String tempstate = baseNetWork.requestInfo.state;
            String appid=baseNetWork.requestInfo.appId;
            JSONObject object = JSONObject.parseObject(conetent);
            //System.out.println("object="+object);
            if (!object.containsKey("errcode")) {
                String openid = object.getString("openid");
                if (tempstate != null) {
                    String url = "?openid=" + openid+"&appid="+appid;
                    if (tempstate.equals("get")) {
                        //点mm跳转
                        realUrl = BaseConfig.baseGoodsurl + url;
                        //System.out.println("get come in ");
                    }else if (tempstate.equals("plant")) {
                        //种咖啡跳转
                        realUrl = BaseConfig.baseGoodsurl  + url +"#/growGoods";
                    }else if(tempstate.equals("lottery")){
                        //抽奖
                        realUrl=BaseConfig.baseGoodsurl +url+"#/seckill";
                    }else if(tempstate.equals("")){
                        //获取订单信息
                        realUrl=BaseConfig.baseGoodsurl +url+"#/orders";
                    }
                }
            }
        } catch (Exception e) {
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
