package com.goods.cn.aop;

import com.fly.cn.observes.MessageManager;
import com.goods.cn.action.ActionProxy;
import com.goods.cn.action.BaseAction;
import com.goods.cn.action.Gift;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.observer.message.ActionGiftMessage;
import com.goods.cn.observer.message.NewUserMessage;
import com.goods.cn.po.UserInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fly on 17/7/13.
 * 注册活动切入 这里所有的注册都会到这
 */
@Component  //加入到IoC容器
@Aspect  //指定当前类为切面类
public class NewUserAop {

    @Autowired
    ActionProxy actionProxy;

    @Pointcut("execution(* com.goods.cn.service.UserService.saveUser(..))")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("NewUserAop aroud come in");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        UserInfo userinfo = (UserInfo) args[0];
        Object returnValue = point.proceed(args);
        //System.out.print("userinfo =="+userinfo.getcOpenid()+" value="+returnValue);
        //查询活动
        BaseAction action= actionProxy.createRealAction(BaseConfig.REGSTERACTION,userinfo,null);
        List<Gift> gifts=actionProxy.findActionGift(action);
        int addsore=0;
        for(Gift gift:gifts){
            addsore+=gift.coffeesore;
        }
        userinfo.addsore+=addsore;
        System.out.println("NewUserAop userinfo="+userinfo.getcId());
        //发送注册成功的消息
        NewUserMessage newUserMessage = new NewUserMessage(userinfo);
        MessageManager.getInstanceMessage().putMessage(newUserMessage);
        //发送奖励消息
        if (gifts != null && gifts.size() > 0) {
            userinfo.giftsMap.put(action.remark,gifts);
            ActionGiftMessage actionGiftMessage = new ActionGiftMessage(action, gifts);
            MessageManager.getInstanceMessage().putMessage(actionGiftMessage);
        }
    }
}
