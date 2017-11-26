package com.goods.cn.observer;

import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.IObservesr;
import com.fly.cn.observes.Message;
import com.fly.cn.observes.MessageManager;
import com.fly.cn.observes.TheadPool;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.observer.message.ChangeRealGoodsMessage;
import com.goods.cn.po.App;
import com.goods.cn.po.Realgoods;
import com.goods.cn.po.Template;
import com.goods.cn.po.Userreal;
import com.goods.cn.service.AppService;
import com.goods.cn.service.GoodsMallService;
import com.goods.cn.service.TemplateService;
import com.goods.cn.util.SendTempUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component("ChangeRealGoodsObserver")
public class ChangeRealGoodsObserver implements IObservesr {


    @Autowired
    AppService appService;

    @Autowired
    TemplateService templateService;

    @Autowired
    GoodsMallService goodsMallService;

    @PostConstruct
    public void init() {
        MessageManager.getInstanceMessage().registerMessage(this, MessageConfig.CHANGEREALGOODS);
        System.out.println("init ChangeRealGoodsObserver");
    }

    //  how  validate the  destory method is  a question
    @PreDestroy
    public void cleanUp() {
        MessageManager.getInstanceMessage().unRegisterMessage(this, MessageConfig.CHANGEREALGOODS);
        //System.out.println("cleanUp");
    }

    @Override
    public void onNoticeMessage(Message message) {
        ChangeRealGoodsMessage changeRealGoodsMessage = (ChangeRealGoodsMessage) message;
        final List<Userreal> userreals = changeRealGoodsMessage.userreals;
        final int appid = changeRealGoodsMessage.appid;
        TheadPool.messageObserverWorkhreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                //发送推送消息
                //发送信息模版
                try {
                    App app = appService.findAppById(appid);
                    if (app != null) {
                        Template template = templateService.findTemplateByappidAndType(app.getcAppid(), BaseConfig.TEMPLATECHANGE);
                        for (Userreal userreal : userreals) {
                            int rid = Integer.valueOf(userreal.getcRealid());
                            Realgoods realgoods = goodsMallService.findRealGoods(rid);
                            int sore = realgoods.getcSore();
                            SendTempUtil.sendChangeTemp(userreal, template, app.getcNotifyOpenid(), String.valueOf(sore));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
