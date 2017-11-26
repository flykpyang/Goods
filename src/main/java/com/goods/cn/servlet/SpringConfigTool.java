package com.goods.cn.servlet;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by fly on 17/5/8.
 * spring的工具类
 * 可以拿到bean
 */

@Component("SpringConfigTool")
public class SpringConfigTool implements ApplicationContextAware {

    private static ApplicationContext ac = null;
    private static SpringConfigTool springConfigTool = null;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("SpringConfigTool set");
        this.ac=applicationContext;
    }

    /**
     * 单例
     * @return
     */
    public synchronized static SpringConfigTool init() {
        if (springConfigTool == null) {
            springConfigTool = new SpringConfigTool();
        }
        return springConfigTool;
    }

    /**
     * 通过beanid 拿到 对应的bean
     * @param beanName
     * @return
     */
    public synchronized static Object getBean(String beanName) {
        //System.out.println("getBean come in "+beanName+" ac="+ac);
        return ac.getBean(beanName);
    }
}

