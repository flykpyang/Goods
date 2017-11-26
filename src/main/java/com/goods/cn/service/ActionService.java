package com.goods.cn.service;

import com.goods.cn.po.Action;
import com.goods.cn.po.UserInfo;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by fly on 2017/7/19.
 */
@Service("actionservice")
public class ActionService extends BaseService{


    public List<Action> findAllActionBy(int balance, UserInfo userInfo)throws Exception{
        List<Action> actions=actionDao.findAllAction(balance,userInfo,userInfo.getcAppid());
        return actions;
    }

    public List<Action> findUseAction(int balance,int appid) throws Exception{
        return actionDao.findDayAction(balance,appid);
    }
}
