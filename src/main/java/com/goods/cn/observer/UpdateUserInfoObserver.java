package com.goods.cn.observer;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.IObservesr;
import com.fly.cn.observes.Message;
import com.fly.cn.observes.MessageManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("updateuserinfoob")
public class UpdateUserInfoObserver implements IObservesr {

    @PostConstruct
    public void init() {
        MessageManager.getInstanceMessage().registerMessage(this, MessageConfig.UPDATEUSERINFO);
        System.out.println("init UpdateUserInfoObserver");
    }

    //  how  validate the  destory method is  a question
    @PreDestroy
    public void cleanUp() {
        MessageManager.getInstanceMessage().unRegisterMessage(this, MessageConfig.UPDATEUSERINFO);
        System.out.println("cleanUp  UpdateUserInfoObserver");
    }



    public void onNoticeMessage(Message message) {

    }
}
