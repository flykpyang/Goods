package com.goods.cn.controller;

import com.fly.cn.dao.PageInfo;
import com.fly.cn.dao.PageQueryResult;
import com.fly.cn.util.ErrorMsg;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.po.*;
import com.goods.cn.service.OrderService;
import com.goods.cn.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by fly on 17/7/18.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class OrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    TypeService typeService;

    @RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json;charset=utf-8"
            , consumes = "application/json;charset=utf-8")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap orderGoods(@RequestBody OrderInfo orderInfo) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            //发送支付成功的消息
            OrderModel orderModel = orderService.order(orderInfo);
            if (orderModel != null) {
                int isfree = orderModel.isfree;
                String orderid = orderModel.orderid;
                supplyMap.put("isfree", isfree);
                supplyMap.put("order_id", orderid);
                supplyMap.put("addsore", orderModel.addsore);
                if (isfree == 0) {
                    //如果不是免费
                    String appId = orderModel.appId;
                    //System.out.println("appid="+appId);
                    supplyMap.put("appId", appId);
                    String nonceStr = orderModel.nonceStr;
                    supplyMap.put("nonceStr", nonceStr);
                    //System.out.println("nonceStr="+nonceStr);
                    String paySign = orderModel.paySign;
                    supplyMap.put("paySign", paySign);
                    //System.out.println("paySign="+paySign);
                    String signType = orderModel.signType;
                    supplyMap.put("signType", signType);
                    String timeStamp = orderModel.timeStamp;
                    supplyMap.put("timeStamp", timeStamp);
                    supplyMap.put("package", orderModel.preid);
                    //System.out.println("package="+orderModel.preid);
                }
            } else {
                supplyMap.setEorrInfo(ErrorMsg.STATUS_ORDER_FAILURE, ErrorMsg.MSG_ORDER_FAILURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        //System.out.println("order josn=" + JSONObject.toJSONString(supplyMap));
        return supplyMap;
    }

    /**
     * @param appid
     * @param openid
     * @param pageindex 从1开始
     * @param pagesize
     * @return
     */
    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET, params = {"appid", "openid", "pageindex", "pagesize"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap getOrderInfo(int appid, String openid, int pageindex, int pagesize) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            PageQueryResult<DestDTO> pageQueryResult = orderService.getAllOrderInfoByOpenid(openid, pageindex, pagesize, appid);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPageQueryResult(pageQueryResult);
            supplyMap.put("page_info", pageInfo);
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }


    @RequestMapping(value = "/getalltype", method = RequestMethod.GET, params = "appid")
    @ResponseBody
    public com.fly.cn.servlet.SupplyMap getallType(int appid) {
        com.fly.cn.servlet.SupplyMap supplyMap = new com.fly.cn.servlet.SupplyMap();
        try {
            List<Type> types = typeService.findAllTypeByAppid(appid);
            if (types != null) {
                supplyMap.put("types", types);
            }
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }


    @RequestMapping(value = "/computerfare", method = RequestMethod.POST, produces = "application/json;charset=utf-8"
            , consumes = "application/json;charset=utf-8")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap computerFare(@RequestBody OrderInfo orderInfo) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            double farePrice = orderService.computeFare(orderInfo);
            supplyMap.put("fareprice", farePrice);
        } catch (Exception e) {
            e.printStackTrace();
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        //System.out.println("computerFare=" + JSONObject.toJSONString(supplyMap));
        return supplyMap;
    }

    @RequestMapping(value = "/getattchgoods", method = RequestMethod.POST, produces = "application/json;charset=utf-8"
            , consumes = "application/json;charset=utf-8")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap getAttchgoods(@RequestBody OrderInfo orderInfo) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            HashMap<Attachgoods,Integer> attachgoods = orderService.findAttchGoods(orderInfo);
            List<AttachGoodsMap> list=new ArrayList<AttachGoodsMap>();
            Iterator iterator=attachgoods.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry=(Map.Entry)iterator.next();
                Attachgoods goods=(Attachgoods)entry.getKey();
                Integer     number=(Integer)entry.getValue();
                AttachGoodsMap map=new AttachGoodsMap();
                map.attachgoods=goods;
                map.number=number;
                list.add(map);
            }
            supplyMap.put("attachgoods", list);
        } catch (Exception e) {
            e.printStackTrace();
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        //System.out.println("computerFare=" + JSONObject.toJSONString(supplyMap));
        return supplyMap;
    }

}
