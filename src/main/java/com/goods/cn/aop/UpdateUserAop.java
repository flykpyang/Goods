package com.goods.cn.aop;
import com.fly.cn.observes.MessageManager;
import com.goods.cn.action.ActionProxy;
import com.goods.cn.action.BaseAction;
import com.goods.cn.action.Gift;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.dao.impl.UserUpdateLogDao;
import com.goods.cn.observer.message.ActionGiftMessage;
import com.goods.cn.observer.message.UpdateUserInfoMessage;
import com.goods.cn.po.UserInfo;
import com.goods.cn.po.Userupdatelog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 更新用户aop
 */
@Component  //加入到IoC容器
@Aspect  //指定当前类为切面类
public class UpdateUserAop {

    @Autowired
    ActionProxy actionProxy;
    @Autowired
    UserUpdateLogDao userUpdateLogDao;

    @Pointcut("execution(* com.goods.cn.service.UserService.updateAndlog(..))")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("updateUserAop aroud come in");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        UserInfo userinfo = (UserInfo) args[0];
        Userupdatelog userupdatelog = (Userupdatelog) args[1];
        Object returnValue = point.proceed(args);
        //System.out.print("userinfo =="+userinfo.getcOpenid()+" value="+returnValue);
        //查询活动
        if (userUpdateLogDao.findUserUpdateLog(userinfo.getcOpenid(),userinfo.getcAppid()) == null) {
            //没有发放的礼物才发放
            BaseAction action = actionProxy.createRealAction(BaseConfig.UPDATEUSERINFOACTION, userinfo, null);
            List<Gift> gifts = actionProxy.findActionGift(action);
            int addsore = 0;
            for (Gift gift : gifts) {
                addsore += gift.coffeesore;
            }
            userinfo.addsore += addsore;
            System.out.println("updateUserAop userinfo=" + userinfo.getcId());
            //发送注册成功的消息
            UpdateUserInfoMessage updateUserInfoMessage = new UpdateUserInfoMessage(userinfo);
            MessageManager.getInstanceMessage().putMessage(updateUserInfoMessage);
            //发送奖励消息
            if (gifts != null && gifts.size() > 0) {
                //更新
                userupdatelog.setcIsaction((byte) 1);
                userinfo.giftsMap.put(action.remark, gifts);
                ActionGiftMessage actionGiftMessage = new ActionGiftMessage(action, gifts);
                MessageManager.getInstanceMessage().putMessage(actionGiftMessage);
            }
        }
        //入库 记录用户更改信息
        userUpdateLogDao.save(userupdatelog);
    }
}
