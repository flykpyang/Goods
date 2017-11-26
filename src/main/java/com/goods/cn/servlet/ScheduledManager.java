package com.goods.cn.servlet;

import com.goods.cn.requestinfo.AcessTimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledManager {
    private static volatile ScheduledManager scheduledManager;
    //定时拉取acessToken的timer
    private ScheduledExecutorService accesTokenTimer;
    //定时模版任务
    private ScheduledExecutorService scheduledExecutorService;
    private final static byte[] lock = new byte[0];

    private ScheduledManager() {
        accesTokenTimer = Executors.newScheduledThreadPool(1);
        scheduledExecutorService = Executors.newScheduledThreadPool(50);
    }

    public void init() {
        //启动定时任务
        AcessTimerTask acessGetTask = new AcessTimerTask();
        accesTokenTimer.scheduleAtFixedRate(acessGetTask, 0, 5400, TimeUnit.SECONDS);
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public static ScheduledManager getInstanceScheduleManager() {
        if (scheduledManager == null) {
            synchronized (lock) {
                if (scheduledManager == null) {
                    scheduledManager = new ScheduledManager();
                }
            }
        }
        return scheduledManager;
    }

    public void destory() {
        if (!accesTokenTimer.isShutdown()) {
            accesTokenTimer.shutdown();
        }
        if (!scheduledExecutorService.isShutdown()) {
            scheduledExecutorService.shutdown();
        }
    }
}
