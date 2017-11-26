package com.goods.cn.observer.message;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.Message;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.UserInfo;


import java.util.List;


/**
 * Created by fly on 17/7/10.
 */
public class PayMessage extends Message {


    public PayMessage(OrderInfo order, UserInfo userInfo) {
        this.messageid = MessageConfig.PAYSUCESS;
        this.order = order;
        this.userInfo=userInfo;
    }

    public OrderInfo order;
    public UserInfo  userInfo;
}
