package com.goods.cn.requestinfo;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.netframe.BaseHttpNetWork;
import com.fly.cn.netframe.RequestInfo;


/**
 * Created by fly on 17/4/20.
 */
public class WeiXinTempAskRequest extends BaseHttpNetWork {
    public WeiXinTempAskRequest(RequestInfo info) {
        super(info);
        method="POST";
    }

    @Override
    public void setHeader() {

    }

    @Override
    public byte[] setBody(RequestInfo info) {
        WeixinTempAskRequestInfo weixinTempAskRequstInfo = (WeixinTempAskRequestInfo) info;
        try {
            JSONObject object = new JSONObject();
            object.put("touser", weixinTempAskRequstInfo.openid);
            object.put("template_id", weixinTempAskRequstInfo.tempid);
            object.put("url","");
            JSONObject array=new JSONObject();
            //frist
            JSONObject fristremark=new JSONObject();
            fristremark.put("value", weixinTempAskRequstInfo.frist);
            //"color":"#173177"
            fristremark.put("color", "#173177");
            array.put("first",fristremark);
            //keynote1 price
            JSONObject priceObject=new JSONObject();
            priceObject.put("value", weixinTempAskRequstInfo.buss);
            array.put("keyword1",priceObject);
            //keynote2 order
            JSONObject orderObject=new JSONObject();
            orderObject.put("value",new java.util.Date());
            array.put("keyword2",orderObject);
            //keynote6 remark
            JSONObject remarkObject=new JSONObject();
            remarkObject.put("value", weixinTempAskRequstInfo.remark);
            array.put("remark",remarkObject);
            object.put("data", array);
            String requeString=object.toString();
            System.out.println("发送模版包体 =="+requeString);
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
