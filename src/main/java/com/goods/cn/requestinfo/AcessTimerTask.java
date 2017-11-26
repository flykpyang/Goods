package com.goods.cn.requestinfo;

import com.fly.cn.netframe.NetService;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.config.BeanDaoImplConfig;
import com.goods.cn.po.App;
import com.goods.cn.service.AppService;
import com.goods.cn.servlet.SpringConfigTool;

import java.util.List;
import java.util.TimerTask;

public class AcessTimerTask extends TimerTask{

    public void run() {
        try{
            AppService appService=(AppService) SpringConfigTool.getBean(BeanDaoImplConfig.APPSERVICE);
            List<App> apps=appService.findAllApp();
            if(apps!=null&&apps.size()>0){
                BaseConfig.aceessthokenMap.clear();
                BaseConfig.ticketmap.clear();
                for(App app:apps){
                    String appid=app.getcAppid();
                    String secretkey=app.getcSecretkey();
                    AcessGetTask acessGetTask=new AcessGetTask(appid,secretkey);
                    NetService.getInstance().getNetWorkThreadPoolExecutor().submit(acessGetTask);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
