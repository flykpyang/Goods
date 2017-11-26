package com.goods.cn.servlet;

import com.fly.cn.netframe.NetFactory;
import com.goods.cn.config.GoodsConfig;
import com.goods.cn.factory.GoodsNetFactoryAgecny;
import com.goods.cn.fixtime.FixTimeGoodsManager;
import com.goods.cn.po.App;
import com.goods.cn.po.Lock;
import com.goods.cn.po.Randiscount;
import com.goods.cn.service.AppService;
import com.goods.cn.service.LockService;
import com.goods.cn.service.RandDisPriceService;
import com.goods.cn.service.UserService;
import com.goods.cn.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


@Component("initDb")
public class InitManager {

    @Autowired
    LockService lockService;
    @Autowired
    UserService userService;
    @Autowired
    GoodsNetFactoryAgecny goodsNetFactoryAgecny;

    @Autowired
    RandDisPriceService randDisPriceService;

    @Autowired
    AppService  appService;

    @Autowired
    SpringConfigTool springConfigTool;

    @Autowired
    FixTimeGoodsManager fixTimeGoodsManager;

    @Autowired
    GoodsDataDicManager goodsDataDicManager;

    Lock orderlock;
    Lock discardlock;


    @PostConstruct
    public void init() throws Exception {
        System.out.println("init InitManager ");
        List<App> apps=appService.findAllApp();
        //重新初始化vip
        goodsDataDicManager.initAllVipMap(apps);
        //初始化限时抢购
        fixTimeGoodsManager.initAllFixMap(apps);
        for(App app:apps) {
            List<Randiscount> randDisPrices = randDisPriceService.findAllRandPrice(app.getcId());
            BaseUtil.initLottyer(randDisPrices,app.getcId());
        }
        orderlock = lockService.findLock(GoodsConfig.ORDERLOCKTYPE);
        discardlock = lockService.findLock(GoodsConfig.DISCARDLOCKTYPE);
        CountLockManager.getIntanceCountLockManager().init(orderlock.getcCount(), discardlock.getcCount());
        //初始化定时任务
        ScheduledManager.getInstanceScheduleManager().init();
        //初始化netfactory
        NetFactory.getIntance().setiNetFactoryAgecny(goodsNetFactoryAgecny);
    }

    @PreDestroy
    public void destory() throws Exception {

        int orderlockcount = CountLockManager.getIntanceCountLockManager().getOrderCount().getCount();
        orderlock.setcCount(orderlockcount);
        lockService.updateLock(orderlock);

        //入库上次的lock
        int discardlockcount = CountLockManager.getIntanceCountLockManager().getDisCardCount().getCount();
        discardlock.setcCount(discardlockcount);
        lockService.updateLock(discardlock);
        //取消定时任务
        ScheduledManager.getInstanceScheduleManager().destory();
    }
}
