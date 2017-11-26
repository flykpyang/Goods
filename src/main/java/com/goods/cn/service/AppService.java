package com.goods.cn.service;

import com.goods.cn.po.App;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("AppService")
public class AppService extends BaseService{

    public App findAppByAppId(String appId)throws Exception{
        return appDao.findAppByAppId(appId);
    }

    public List<App> findAllApp() throws Exception{
        return appDao.findAllApp();
    }

    public App findAppById(int appid)throws Exception{
        return appDao.findAppById(appid);
    }
}
