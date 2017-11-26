package com.goods.cn.util;

import com.alibaba.fastjson.JSONArray;
import com.fly.cn.netframe.NetService;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.config.CgiConfig;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Template;
import com.goods.cn.po.Useraddress;
import com.goods.cn.po.Userreal;
import com.goods.cn.requestinfo.WeixinGetOrderRequestInfo;
import com.goods.cn.requestinfo.WeixinTempChangeRequestInfo;

import java.util.Date;

public class SendTempUtil {

    public static void sendOrderTemp(OrderInfo orderInfo, Template template, Useraddress useraddress,String appOpenid) {
        //System.out.println("sendOrderTemp come in");
        String openid = orderInfo.getcOpenid();
        String orderid = orderInfo.getcOrderid();
        String aceessthoken = BaseConfig.aceessthokenMap.get(template.getcAppid());
        //System.out.println("aceessthoken="+aceessthoken);
        if (aceessthoken != null) {
            //发送给用户
            WeixinGetOrderRequestInfo userinfo=creatWeiXinOrderInfo(orderInfo,aceessthoken,openid,template,useraddress,orderid);
            //System.out.println("temp begin");
            NetService.getInstance().startAsyHttpNetWork(userinfo);
            if(appOpenid!=null) {
                //发送给商家
                userinfo = creatWeiXinOrderInfo(orderInfo, aceessthoken, appOpenid, template, useraddress, orderid);
                NetService.getInstance().startAsyHttpNetWork(userinfo);
            }
        }
    }



    private static WeixinGetOrderRequestInfo creatWeiXinOrderInfo(OrderInfo orderInfo,String aceessthoken,String openid
    ,Template template, Useraddress useraddress,String orderid){
        WeixinGetOrderRequestInfo info = new WeixinGetOrderRequestInfo();
        info.requestKey = CgiConfig.WXORDERTEMPLATE;
        info.url = BaseConfig.WXSENDTEMPURL + aceessthoken;
        info.openid = openid;
        info.callurl = BaseConfig.baseGoodsurl + "?openid=" + orderInfo.getcOpenid() + "&orderid=" + orderInfo.getcOrderid()+"&appid="+orderInfo.getcAppid();
        info.template_id = template.getcTemplateId();
        info.orderid = orderid;
        info.address = useraddress.getcAddressname()+useraddress.getcOther();
        info.orderInfo = getOrderInfo(orderInfo.getcCoffeelist());
        info.price = orderInfo.getcTotalPrice() + "元 支付成功";
        info.userInfo = useraddress.getcLinkName() + ", 电话号码:" + useraddress.getcLinkPhone();
        info.orderCode=orderid;
        return info;
    }

    private static String getOrderInfo(String dishList) {
        StringBuffer orderinfo = new StringBuffer();
        try {
            JSONArray orderArray = JSONArray.parseArray(dishList);
            for (int j = 0; j < orderArray.size(); j++) {
                String dish_name = orderArray.getJSONObject(j).getString(
                        "name");
                int count = orderArray.getJSONObject(j).getInteger("count");
                if (j > 0) {
                    orderinfo.append(",");
                }
                orderinfo.append(dish_name + count + " 份");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return orderinfo.toString();
    }



    public static void sendChangeTemp(Userreal userreal, Template template, String appOpenid,String price) {
        //System.out.println("sendOrderTemp come in");
        String openid = userreal.getcOpenid();
        String aceessthoken = BaseConfig.aceessthokenMap.get(template.getcAppid());
        //System.out.println("aceessthoken="+aceessthoken);
        if (aceessthoken != null) {
            //发送给用户
            WeixinTempChangeRequestInfo changeRequestInfo=creatWeiXinChangeInfo(userreal,aceessthoken,openid,template,price);
            //System.out.println("temp begin");
            NetService.getInstance().startAsyHttpNetWork(changeRequestInfo);
            if(appOpenid!=null) {
                //发送给商家
                changeRequestInfo = creatWeiXinChangeInfo(userreal, aceessthoken, appOpenid, template, price);
                NetService.getInstance().startAsyHttpNetWork(changeRequestInfo);
            }
        }
    }


    private static WeixinTempChangeRequestInfo creatWeiXinChangeInfo(Userreal userreal,String aceessthoken,String openid
            ,Template template,String price){
        WeixinTempChangeRequestInfo info = new WeixinTempChangeRequestInfo();
        info.requestKey = CgiConfig.WXCHANGETEMPLATE;
        info.url = BaseConfig.WXSENDTEMPURL + aceessthoken;
        info.openid = openid;
//        info.callurl = BaseConfig.baseGoodsurl + "?openid=" + userreal.getcOpenid() + "&orderid=" + orderInfo.getcOrderid()+"&appid="+orderInfo.getcAppid();
        info.template_id = template.getcTemplateId();
        info.frist = "尊敬的会员，您已成功进行积分换礼";
        info.price = price;
        info.remark = "兑换"+userreal.getcRealname();
        info.changeTime= TimeUtil.coverFormatTime(new Date());
        return info;
    }

}
