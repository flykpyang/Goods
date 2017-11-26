package com.goods.cn.requestinfo;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.netframe.BaseHttpNetWork;
import com.fly.cn.netframe.RequestInfo;

/**
 * 兑换商品请求
 *
 * @author fly
 * @create 2017-11-24 14:20
 **/

public class WeixinTempChangeRequest extends BaseHttpNetWork {

    public WeixinTempChangeRequest(RequestInfo info) {
        super(info);
        method = "POST";

    }

    @Override
    public void setHeader() {

    }

    @Override
    public byte[] setBody(RequestInfo info) {

        WeixinTempChangeRequestInfo weixinTempChangeRequestInfo = (WeixinTempChangeRequestInfo) info;
        try {
            JSONObject object = new JSONObject();
            object.put("touser", weixinTempChangeRequestInfo.openid);
            object.put("template_id", weixinTempChangeRequestInfo.template_id);
            object.put("url", weixinTempChangeRequestInfo.callUrl);
            JSONObject array = new JSONObject();
            //frist
            JSONObject fristremark = new JSONObject();
            fristremark.put("value", weixinTempChangeRequestInfo.frist);
            //"color":"#173177"
            fristremark.put("color", "#173177");
            array.put("first", fristremark);
            //keynote1 changeTime
            JSONObject changeTimeObject = new JSONObject();
            changeTimeObject.put("value", weixinTempChangeRequestInfo.changeTime);
            array.put("keyword1", changeTimeObject);
            //keynote2 price
            JSONObject priceObject = new JSONObject();
            priceObject.put("value", weixinTempChangeRequestInfo.price);
            array.put("keyword2", priceObject);
            //keynote6 remark
            JSONObject remarkObject = new JSONObject();
            remarkObject.put("value", weixinTempChangeRequestInfo.remark);
            array.put("remark", remarkObject);
            object.put("data", array);
            String requeString = object.toString();
            System.out.println("发送模版包体 ==" + requeString);
            return requeString.getBytes("utf-8");
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    @Override
    public void modifyUrl(RequestInfo info) {

    }
}
