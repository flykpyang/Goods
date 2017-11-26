package com.goods.cn.observer.message;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.Message;
import com.goods.cn.po.Usercard;


import java.util.List;

public class DayTipsMessage extends Message {

    public List<Usercard> usercards;
    public DayTipsMessage(List<Usercard> usercards){
        this.usercards=usercards;
        this.messageid= MessageConfig.DAYCARDSTIPS;
    }
}
