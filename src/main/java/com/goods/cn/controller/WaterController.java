package com.goods.cn.controller;

import com.fly.cn.util.ErrorMsg;
import com.goods.cn.action.Gift;
import com.goods.cn.po.WaterTaskModel;
import com.goods.cn.service.TaskProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class WaterController {

    @Autowired
    TaskProService taskProService;

    /**
     * 拿到浇花计划
     *
     * @return
     */
    @RequestMapping(value = "/watertask", method = RequestMethod.GET, params = {"appid", "openid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap waterTask(int appid, String openid) throws Exception {
        SupplyMap supplyMap = new SupplyMap();
        try {
            WaterTaskModel waterTaskModel = taskProService.findTaskProForUser(openid,appid);
            if (waterTaskModel != null) {
                supplyMap.put("watertask", waterTaskModel);
            } else {
                supplyMap.setEorrInfo(ErrorMsg.STATUS_INVALID_PARAMETERS, ErrorMsg.MSG_INVALID_PARAMETERS);
            }

        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }

    /**
     * 拿到浇花计划
     *
     * @return
     */
    @RequestMapping(value = "/water", method = RequestMethod.GET, params = {"appid", "openid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap water(int appid, String openid) throws Exception {
        SupplyMap supplyMap = new SupplyMap();
        try {
            Gift gift = taskProService.water(openid,appid);
            if (gift != null) {
                supplyMap.put("gift", gift);
            } else {
                supplyMap.setEorrInfo(ErrorMsg.STATUS_WATER_ERROR, ErrorMsg.MSG_WATER_ERROR);
            }

        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }
}
