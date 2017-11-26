package com.goods.cn.aop;

import com.goods.cn.po.Exceptionlog;
import com.goods.cn.service.ExceptionlogService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Created by fly on 2017/7/19.
 */
//@Order(1000)
//@Component  //加入到IoC容器
//@Aspect  //指定当前类为切面类
public class ExceptionAop {

    @Autowired
    ExceptionlogService exceptionLogService;

    private final Logger logger = Logger.getLogger(ExceptionAop.class);

    @Pointcut("execution(* com.goods.cn.service.*.*(..))")
    public void pointCut() {

    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Throwable e) throws Exception {
        //只截获没有捕获的异常
        System.out.println(e.toString());
        Exceptionlog exceptionlog = new Exceptionlog();
        //获取类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        String location = className + "." + methodName + ":";
        exceptionlog.setcLog(location+e.getClass().getSimpleName()+e.getMessage());
        exceptionLogService.save(exceptionlog);
    }
}

