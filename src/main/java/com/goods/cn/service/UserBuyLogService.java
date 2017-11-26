package com.goods.cn.service;

import com.goods.cn.po.Userbuylog;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("UserBuyLogService")
public class UserBuyLogService extends BaseService{

    public void save(Userbuylog userbuylog)throws Exception{
        userBuyLogDao.save(userbuylog);
    }

    public long findBuyCount(String openid,int appid)throws Exception{
        return userBuyLogDao.findUserAllBuyCount(openid,appid);
    }

    public long findUserBuyCountByTime(String openid, Date btime, Date etime, int appid) throws Exception{
        return userBuyLogDao.findUserBuyCountByTime(openid,btime,etime,appid);
    }
}
