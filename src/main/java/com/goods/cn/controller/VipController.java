package com.goods.cn.controller;

import com.fly.cn.util.ErrorMsg;
import com.goods.cn.po.NextVip;
import com.goods.cn.po.Vip;
import com.goods.cn.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class VipController {


    @Autowired
    VipService vipService;


    //@LoginRequired
    @RequestMapping(value = "/getallvip", method = RequestMethod.GET,params = {"appid"})
    @ResponseBody
    public SupplyMap getallVip(int appid) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            List<Vip> vips = vipService.getAllVip(appid);
            if (vips != null) {
                supplyMap.put("vips", vips);
            }
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }

    @RequestMapping(value = "/nextvip", method = RequestMethod.GET,params = {"appid","openid"})
    @ResponseBody
    public SupplyMap getnextVip(int appid,String openid) {
        SupplyMap supplyMap = new SupplyMap();
        try{
            NextVip vip=vipService.findNextVip(openid,appid);
            supplyMap.put("nextvip",vip);
        }catch (Exception e){
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }
}
