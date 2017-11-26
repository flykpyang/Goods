package com.goods.cn.observer;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.IObservesr;
import com.fly.cn.observes.Message;
import com.fly.cn.observes.MessageManager;
import com.fly.cn.observes.TheadPool;
import com.goods.cn.action.BaseAction;
import com.goods.cn.action.Gift;
import com.goods.cn.observer.message.ActionGiftMessage;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component("actiongiftob")
public class ActionGiftObserver implements IObservesr {


    @PostConstruct
    public void init() {
        MessageManager.getInstanceMessage().registerMessage(this, MessageConfig.ACTIONGIFT);
        System.out.println("init ActionGiftObserver");
    }

    //  how  validate the  destory method is  a question
    @PreDestroy
    public void cleanUp() {
        MessageManager.getInstanceMessage().unRegisterMessage(this, MessageConfig.ACTIONGIFT);
        System.out.println("cleanUp");
    }


    public void onNoticeMessage(Message message) {
        final ActionGiftMessage actionGiftMessage = (ActionGiftMessage) message;
        //发放奖励
        TheadPool.sendGiftThreadPoolExecutor.submit(new Runnable() {
            public void run() {
                System.out.println("action send gift come in");
                BaseAction action = actionGiftMessage.action;
                List<Gift> gifts = actionGiftMessage.gifts;
                try {
                    action.giveGift(gifts);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
