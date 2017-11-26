package com.goods.cn.observer;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.IObservesr;
import com.fly.cn.observes.Message;
import com.fly.cn.observes.MessageManager;
import com.fly.cn.observes.TheadPool;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.observer.message.DayTipsMessage;
import com.goods.cn.po.Usercard;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.List;

@Component("daycardtipob")
public class DayTipsObserver implements IObservesr {

    @PostConstruct
    public void init() {
        MessageManager.getInstanceMessage().registerMessage(this, MessageConfig.DAYCARDSTIPS);
        System.out.println("init DayTipsObserver");
    }

    //  how  validate the  destory method is  a question
    @PreDestroy
    public void cleanUp() {
        MessageManager.getInstanceMessage().unRegisterMessage(this, MessageConfig.DAYCARDSTIPS);
        //System.out.println("cleanUp");
    }


    public void onNoticeMessage(Message message) {
        final DayTipsMessage dayTipsMessage=(DayTipsMessage)message;
        TheadPool.messageObserverWorkhreadPoolExecutor.submit(new Runnable() {
            public void run() {
                List<Usercard> usercards=dayTipsMessage.usercards;
                if(usercards!=null){
                    Date now= TimeUtil.getCurrDay(0);
                    for(Usercard usercard:usercards){
                        Date exdate=usercard.getcExpiretime();
                        try {
                            int number = TimeUtil.daysBetween(now, exdate);
                            //SendTempUtil.sendDayTipsTemp(usercard,number);
                        }catch (Exception e){

                        }
                    }
                }
            }
        });
    }
}
