package com.goods.cn.aop;

import com.goods.cn.action.ActionProxy;
import com.goods.cn.action.BaseAction;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.dao.IUserDao;
import com.goods.cn.po.GoodsInfo;
import com.goods.cn.po.Type;
import com.goods.cn.po.UserInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TreeMap;

@Component  //加入到IoC容器
@Aspect  //指定当前类为切面类
public class GoodsListAop {

    @Autowired
    IUserDao userDao;

    @Autowired
    ActionProxy actionProxy;

    @Pointcut("execution(* com.goods.cn.service.GoodsInfoService.findAllGoodsInfoByAppId(..))")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("GoodsListAop aroud come in");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        String openid=(String)args[0];
        int    appid=(Integer) args[1];
        UserInfo userInfo=userDao.checkUserByOpenid(openid,appid);
        Object returnValue = point.proceed(args);
        System.out.println("CoffeeListAop run after come in");
        TreeMap<Type, List<GoodsInfo>> map=(TreeMap<Type, List<GoodsInfo>>)returnValue;
        BaseAction action = actionProxy.createRealAction(BaseConfig.FIXTIMECOFFEEACTION, userInfo, map);
        actionProxy.findActionGift(action);
        return map;
    }
}
