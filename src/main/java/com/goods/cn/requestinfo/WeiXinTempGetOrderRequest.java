package com.goods.cn.requestinfo;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.netframe.BaseHttpNetWork;
import com.fly.cn.netframe.RequestInfo;
import com.fly.cn.util.TimeUtil;

import java.util.Date;


/**
 * Created by fly on 17/4/20.
 */
public class WeiXinTempGetOrderRequest extends BaseHttpNetWork {


    public WeiXinTempGetOrderRequest(RequestInfo info) {
        super(info);
        method="POST";
    }

    @Override
    public void setHeader() {

    }

    @Override
    public byte[] setBody(RequestInfo info) {
        WeixinGetOrderRequestInfo weixinGetOrderRequestInfo = (WeixinGetOrderRequestInfo) info;
        try {
            JSONObject object = new JSONObject();
            object.put("touser", weixinGetOrderRequestInfo.openid);
            object.put("template_id", weixinGetOrderRequestInfo.template_id);
            object.put("url",weixinGetOrderRequestInfo.callurl);
            JSONObject array=new JSONObject();
            //frist
            JSONObject fristremark=new JSONObject();
            fristremark.put("value", "订单号:"+weixinGetOrderRequestInfo.orderid);
            //"color":"#173177"
            fristremark.put("color", "#173177");
            array.put("first",fristremark);
            //keynote1 price
            JSONObject priceObject=new JSONObject();
            priceObject.put("value", weixinGetOrderRequestInfo.price);
            array.put("keyword1",priceObject);
            //keynote2 order
            JSONObject orderObject=new JSONObject();
            orderObject.put("value", weixinGetOrderRequestInfo.orderInfo);
            array.put("keyword2",orderObject);
            //keynote3 userinfo
            JSONObject userObject=new JSONObject();
            userObject.put("value", weixinGetOrderRequestInfo.userInfo);
            array.put("keyword3",userObject);
            //keynote4 addr
            JSONObject addrObject=new JSONObject();
            addrObject.put("value", weixinGetOrderRequestInfo.address);
            array.put("keyword4",addrObject);
            //keynote5 time
            JSONObject timeObject=new JSONObject();
            timeObject.put("value", TimeUtil.coverFormatTime(new Date()));
            array.put("keyword5",timeObject);
            //keynote6 remark
            JSONObject remarkObject=new JSONObject();
            remarkObject.put("value", weixinGetOrderRequestInfo.remark);
            array.put("remark",remarkObject);
            object.put("data", array);
            String requeString=object.toString();
            System.out.println("发送模版包体 =="+requeString);
            return requeString.getBytes("utf-8");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void modifyUrl(RequestInfo info) {

    }
}

