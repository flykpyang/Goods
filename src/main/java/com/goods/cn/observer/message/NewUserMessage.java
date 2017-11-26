package com.goods.cn.observer.message;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.Message;
import com.goods.cn.po.UserInfo;


/**
 * Created by fly on 17/7/13.
 */
public class NewUserMessage extends Message {

    public UserInfo userInfo;

    public NewUserMessage(UserInfo userInfo) {
        this.userInfo=userInfo;
        this.messageid = MessageConfig.REGISTER;
    }

}
