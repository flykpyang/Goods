package com.goods.cn.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.util.ErrorMsg;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.fixtime.FixTimeGoodsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class AppAcessTokenController {


    @Autowired
    FixTimeGoodsManager fixTimeGoodsManager;

    /**
     * 获得app的acessthoken
     *
     * @return
     */
    @RequestMapping(value = "/getappacess", method = RequestMethod.GET, params = {"appid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap getAllGoods(String appid) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            String acessToken= BaseConfig.aceessthokenMap.get(appid);
            if(acessToken!=null){
                supplyMap.put("acesstoken",acessToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        //System.out.println("getappacess josn=" + JSONObject.toJSONString(supplyMap));
        return supplyMap;
    }

    /**
     * 更新fixtime的同步
     *
     * @return
     */
    @RequestMapping(value = "/updatefixtime", method = RequestMethod.GET, params = {"appid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap updateFixtime(int appid) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            fixTimeGoodsManager.initMap(appid);
        } catch (Exception e) {
            e.printStackTrace();
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        System.out.println("updatefixtime josn=" + JSONObject.toJSONString(supplyMap));
        return supplyMap;
    }
}
