package com.goods.cn.util;

import com.goods.cn.po.UserInfo;

/**
 * Created by fly on 17/5/24.
 */
public class ResolveUtil {


    /**
     * 生成新的用户
     *
     * @param openid
     * @return
     */
    public static UserInfo creatNewUserInfo(String openid, int appid) {
        UserInfo userInfo = new UserInfo();
        userInfo.setcOpenid(openid);
        userInfo.setcPhone("");
        userInfo.setcName("");
        userInfo.setcAppid(appid);
        userInfo.setcSore(0);
        userInfo.setcVid(0);
        userInfo.setcMonetary("0.00");
        return userInfo;
    }

    public static void main(String[] args) {
        String test = "    #1#2";
        String[] strings = test.split("#");
        System.out.println("size=" + strings.length + " 0=" + strings[0].trim().length());
    }
}
