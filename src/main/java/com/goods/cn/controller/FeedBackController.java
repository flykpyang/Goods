package com.goods.cn.controller;

import com.fly.cn.dao.PageInfo;
import com.fly.cn.dao.PageQueryResult;
import com.fly.cn.util.ErrorMsg;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.po.Feedback;
import com.goods.cn.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class FeedBackController {

    @Autowired
    FeedbackService feedbackService;

    @RequestMapping(value = "/getfeedback", method = RequestMethod.GET)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap getfeedback(@RequestParam(value = "date",required = false)Date date,
                                 @RequestParam(value = "pageindex")int pageindex,
                                 @RequestParam(value = "pagesize")int pagesize,
                                 @RequestParam(value = "appid") int appid) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            PageQueryResult<DestDTO> pageQueryResult= feedbackService.findFeedBackByDate(pageindex,pagesize,date,appid);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPageQueryResult(pageQueryResult);
            supplyMap.put("page_info", pageInfo);
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST, produces = "application/json;charset=utf-8"
            , consumes = "application/json;charset=utf-8")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap feedback(@RequestBody Feedback feedback){
        SupplyMap supplyMap=new SupplyMap();
        try{
            feedbackService.save(feedback);
            supplyMap.put("feedback",feedback);
        }catch (Exception e){
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }
}
