package com.goods.cn.service;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.po.ActionRout;
import com.goods.cn.po.UserInfo;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("actionroutservice")
public class ActionRoutService extends BaseService {

    public void loginActionRout(UserInfo userInfo) throws Exception {
        Date currDay = TimeUtil.getCurrDay(0);
        String openid = userInfo.getcOpenid();
        int    appid=userInfo.getcAppid();
        ActionRout actionRout = actionRoutDao.getActionByDate(currDay, openid,appid);
        if (actionRout == null) {
            //这里的入侵太强了  <aop:aspectj-autoproxy expose-proxy="true" />
            ((ActionRoutService) AopContext.currentProxy()).saveActionRout(userInfo);
        }
    }

    public void saveActionRout(UserInfo userInfo) throws Exception {
        String openid = userInfo.getcOpenid();
        int    appid=userInfo.getcAppid();
        ActionRout longinRout = new ActionRout();
        longinRout.setcOpenid(openid);
        longinRout.setcRout("A");
        longinRout.setcAppid(appid);
        actionRoutDao.save(longinRout);
    }
}
