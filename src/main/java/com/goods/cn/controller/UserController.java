package com.goods.cn.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.util.ErrorMsg;
import com.goods.cn.po.*;
import com.goods.cn.service.ActionRoutService;
import com.goods.cn.service.GoodsInfoService;
import com.goods.cn.service.UserAddressService;
import com.goods.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fly on 2017/7/19.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ActionRoutService actionRoutService;

    @Autowired
    UserAddressService userAddressService;

    @Autowired
    GoodsInfoService goodsInfoService;

    /**
     * 拿到用户信息
     *
     * @return
     */
    @RequestMapping(value = "/userinfo", method = RequestMethod.GET, params = {"openid", "appid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap findUserInfo(String openid, int appid) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            UserModel userModel = userService.findUserByOpenId(openid, appid);
            //签到
            actionRoutService.loginActionRout(userModel.userInfo);
            supplyMap.put("data", userModel);
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        System.out.println("userinfo=" + JSONObject.toJSONString(supplyMap));
        return supplyMap;
    }


    /**
     * 查看微信用户信息
     *
     * @return
     */
    @RequestMapping(value = "/checksubscribe", method = RequestMethod.GET, params = {"openid", "appid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap checkSubscribe(String openid, int appid) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            String url = userService.checkUserSubscribe(openid, appid);
            if(!"-1".equals(url)) {
                supplyMap.put("orcodeurl", url);
                supplyMap.put("subscribe",0);
            }else{
                supplyMap.put("subscribe",1);
            }
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        System.out.println("checksubscribe=" + JSONObject.toJSONString(supplyMap));
        return supplyMap;
    }


    /**
     * 更新用户
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/userupdate", method = RequestMethod.POST, produces = "application/json;charset=utf-8"
            , consumes = "application/json;charset=utf-8")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap updateUserInfo(@RequestBody UpdateUserInfo userInfo) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            UserInfo user = userService.updateUserInfo(userInfo);
            if (user != null) {
                supplyMap.put("user", user);
            } else {
                supplyMap.setEorrInfo(ErrorMsg.STATUS_ILLEGAL_USER, ErrorMsg.MSG_ILLEGAL_USER);
            }
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }

    /**
     * 更新用户
     *
     * @param useraddress
     * @return
     */
    @RequestMapping(value = "/adduseraddr", method = RequestMethod.POST, produces = "application/json;charset=utf-8"
            , consumes = "application/json;charset=utf-8")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap addUserAddr(@RequestBody Useraddress useraddress) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            userAddressService.save(useraddress);
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }

    /**
     * 更新用户
     *
     * @param useraddress
     * @return
     */
    @RequestMapping(value = "/updateuseraddr", method = RequestMethod.POST, produces = "application/json;charset=utf-8"
            , consumes = "application/json;charset=utf-8")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap updateUserAddr(@RequestBody Useraddress useraddress) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            userAddressService.update(useraddress);
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }


    /**
     * 用户收藏
     *
     * @param openid
     * @param appid
     * @param goodsid
     * @return
     */
    @RequestMapping(value = "/lovegoods", method = RequestMethod.GET, params = {"openid", "appid", "goodsid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap loveGoods(String openid, int appid, String goodsid) {
        SupplyMap supplyMap = new SupplyMap();
        try {
            //关注或者取消
            Love love = userService.userLove(openid, appid, goodsid);
            //回传信息
            UserLoveInfo userLoveInfo = goodsInfoService.findUserLove(openid, goodsid, appid, love.getcId());
            supplyMap.put("userLoveInfo", userLoveInfo);
        } catch (Exception e) {
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR, ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }
}
