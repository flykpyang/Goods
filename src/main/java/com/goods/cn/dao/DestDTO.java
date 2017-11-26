package com.goods.cn.dao;

import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.UserInfo;

public class DestDTO {
    private OrderInfo orderinfo;
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public OrderInfo getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(OrderInfo orderinfo) {
        this.orderinfo = orderinfo;
    }
}
