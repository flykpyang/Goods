package com.goods.cn.observer.message;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.Message;
import com.goods.cn.action.BaseAction;
import com.goods.cn.action.Gift;


import java.util.List;

public class ActionGiftMessage extends Message {

    public List<Gift> gifts;
    public BaseAction action;

    public ActionGiftMessage(BaseAction action, List<Gift> gifts) {
        this.gifts = gifts;
        this.action=action;
        this.messageid = MessageConfig.ACTIONGIFT;
    }
}
