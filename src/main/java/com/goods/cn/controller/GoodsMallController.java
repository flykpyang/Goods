package com.goods.cn.controller;

import com.fly.cn.util.ErrorMsg;
import com.goods.cn.po.Change;
import com.goods.cn.po.GetChangeAllModel;
import com.goods.cn.po.UserChangeModel;
import com.goods.cn.service.GoodsMallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class GoodsMallController {

    @Autowired
    GoodsMallService goodsMallService;

    /**
     * 获得所有可兑换的商品
     * @param appid
     * @param openid
     * @return
     */
    @RequestMapping(value = "/getallchange", method = RequestMethod.GET, params = {"appid", "openid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap getAllChange(int appid, String openid) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            GetChangeAllModel getChangeAllModel = goodsMallService.getAllChange(openid,appid);
            supplyMap.put("allgoods", getChangeAllModel);
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }

    /**
     * 获得所有用户的兑换
     * @param appid
     * @param openid
     * @param isuse
     * @return
     */
    @RequestMapping(value = "/getuserchange", method = RequestMethod.GET, params = {"appid", "openid", "isuse"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap getAllUserChange(int appid, String openid, int isuse) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            UserChangeModel userChangeModel = goodsMallService.getAllUserChangeGoods(openid,isuse,appid);
            supplyMap.put("userchange", userChangeModel);
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }

    @RequestMapping(value = "/change",method = RequestMethod.POST,produces = "application/json;charset=utf-8"
    ,consumes = "application/json;charset=utf-8")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap changeCoffeeMall(@RequestBody Change change){
        SupplyMap supplyMap=new SupplyMap();
        try{
            goodsMallService.changeCoffeeGoods(change);
        }catch (Exception e){
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR,ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }
}
