package com.goods.cn.aop;

import com.fly.cn.observes.MessageManager;
import com.goods.cn.action.ActionProxy;
import com.goods.cn.action.BaseAction;
import com.goods.cn.action.Gift;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.dao.IUserDao;
import com.goods.cn.observer.message.ActionGiftMessage;
import com.goods.cn.po.Feedback;
import com.goods.cn.po.UserInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  //加入到IoC容器
@Aspect  //指定当前类为切面类
public class FeedBackAop {

    @Autowired
    ActionProxy actionProxy;
    @Autowired
    IUserDao userDao;

    @Pointcut("execution(* com.goods.cn.service.FeedbackService.save(..))")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("FeedAop aroud come in");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        Feedback feedback = (Feedback) args[0];
        String openid = feedback.getcOpenid();
        UserInfo userinfo = userDao.checkUserByOpenid(openid,feedback.getcAppid());
        Object returnValue = point.proceed(args);
        if (userinfo != null) {
            //首次登录查询活动
            BaseAction action = actionProxy.createRealAction(BaseConfig.FEEDBACKACTION, userinfo, feedback);
            List<Gift> gifts = actionProxy.findActionGift(action);
            int addsore = 0;
            for (Gift gift : gifts) {
                addsore += gift.coffeesore;
            }
            feedback.addsore += addsore;
            //System.out.println("FeedAop userinfo=" + userinfo.getcId());
            //发送奖励消息
            if (gifts != null && gifts.size() > 0) {
                feedback.giftsMap.put(action.remark, gifts);
                ActionGiftMessage actionGiftMessage = new ActionGiftMessage(action, gifts);
                MessageManager.getInstanceMessage().putMessage(actionGiftMessage);
            }
        }
    }
}
