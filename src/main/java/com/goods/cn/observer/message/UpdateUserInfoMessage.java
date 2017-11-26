package com.goods.cn.observer.message;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.Message;
import com.goods.cn.po.UserInfo;


public class UpdateUserInfoMessage extends Message {

    public UserInfo userInfo;

    public UpdateUserInfoMessage(UserInfo userInfo) {
        this.userInfo=userInfo;
        this.messageid = MessageConfig.UPDATEUSERINFO;
    }

}
