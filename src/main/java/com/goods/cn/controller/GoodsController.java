package com.goods.cn.controller;

import com.fly.cn.util.ErrorMsg;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.po.*;
import com.goods.cn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by fly on 17/7/17.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class GoodsController {

    @Autowired
    GoodsInfoService goodsInfoService;

    @Autowired
    UserCardService userCardService;

    @Autowired
    AdddicService  adddicService;

    @Autowired
    AppService appService;

    @Autowired
    WxService  wxService;

    @Autowired
    ShareService shareService;


    /**
     * 拿到所有的货品的信息
     *
     * @return
     */
    @RequestMapping(value = "/goodslist", method = RequestMethod.GET, params = {"openid","appid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap getAllGoods(String openid,int appid) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            TreeMap<Type, List<GoodsInfo>> treeMap = goodsInfoService.findAllGoodsInfoByAppId(openid, appid);
            if (treeMap != null && treeMap.size() > 0) {
                supplyMap.put("count", treeMap.size());
                List<GoodsTypeInfos> infos = new ArrayList<GoodsTypeInfos>();
                Iterator iterator = treeMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    Type key = (Type) entry.getKey();
                    List<GoodsInfo> value = (List<GoodsInfo>) entry.getValue();
                    GoodsTypeInfos info = new GoodsTypeInfos();
                    info.setDes(key.getcDes());
                    info.setList(value);
                    info.setName(key.getcName());
                    info.setPic(key.getcPic());
                    info.setType(key.getcId());
                    info.setOrder(key.getcOrder());
                    infos.add(info);
                }
                supplyMap.put("data", infos);
            }
        } catch (Exception e) {
            e.printStackTrace();
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        //System.out.println("coffeelist josn=" + JSONObject.toJSONString(supplyMap));
        return supplyMap;
    }


    /**
     * 使用卡卷
     *
     * @param useCard
     * @return
     */
    @RequestMapping(value = "/usecard", method = RequestMethod.POST, produces = "application/json;charset=utf-8"
            , consumes = "application/json;charset=utf-8")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap UseCard(@RequestBody UseCard useCard) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            Usercard usercard = userCardService.useCardComputeValue(useCard);
            if (usercard != null) {
                supplyMap.put("usercard", usercard);
            } else {
                supplyMap.setEorrInfo(ErrorMsg.STATUS_INVALID_PARAMETERS, ErrorMsg.MSG_INVALID_PARAMETERS);
            }
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }

    /**
     * 跳转
     *
     * @return
     */
    @RequestMapping(value = "/wx/skip", method = RequestMethod.GET, params = {"appid", "action","openid"})
    public void skip(int appid, String action, String openid,HttpServletRequest request, HttpServletResponse response) {
        try {
            String wxurl = BaseConfig.WXSKIP;
            App app = appService.findAppById(appid);
            if (app != null) {
                String realAppid = app.getcAppid();
                String skipurl = String.format(wxurl, realAppid, realAppid, action);
                System.out.println(skipurl);
                response.sendRedirect(skipurl);
            }
        } catch (Exception e) {

        } finally {
            //关掉输流
            try {
                if (response != null) {
                    response.getWriter().close();
                }
            } catch (Exception e) {

            }
        }

    }

    /**
     * 咖啡拉起地图
     *
     * @return
     */
    @RequestMapping(value = "/goodsskip", method = RequestMethod.GET, params = {"openid","appid","url"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap goodsSkip(String openid,int appid,String url) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            WxModel wxModel = wxService.getWxMode(openid,appid,url);
            if (wxModel != null) {
                supplyMap.put("skip", wxModel);
            } else {
                supplyMap.setEorrInfo(ErrorMsg.STATUS_SAO_ERROR, ErrorMsg.MSG_SAO_ERROR);
            }
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        //System.out.println("goodsskip josn=" + JSONObject.toJSONString(supplyMap));
        return supplyMap;
    }



    /**
     * 咖啡拉起地图
     *
     * @return
     */
    @RequestMapping(value = "/sharedes", method = RequestMethod.GET, params = {"appid","action"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap shareDes(int appid,String action) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            Share share=shareService.getShare(action,appid);
            if(share!=null){
                supplyMap.put("sharedes",share);
            }
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        //System.out.println("sharedes josn=" + JSONObject.toJSONString(supplyMap));
        return supplyMap;
    }
}
