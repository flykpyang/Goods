package com.goods.cn.observer;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.IObservesr;
import com.fly.cn.observes.Message;
import com.fly.cn.observes.MessageManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("fristloginob")
public class FristLoginObserver implements IObservesr {

    @PostConstruct
    public void init() {
        MessageManager.getInstanceMessage().registerMessage(this, MessageConfig.FRISTLOGIN);
        System.out.println("init FristLoginObserver");
    }

    //  how  validate the  destory method is  a question
    @PreDestroy
    public void cleanUp() {
        MessageManager.getInstanceMessage().unRegisterMessage(this, MessageConfig.FRISTLOGIN);
        System.out.println("cleanUp");
    }


    public void onNoticeMessage(Message message) {
        System.out.println("FristLoginObserver  onNoticeMessage");
    }
}
