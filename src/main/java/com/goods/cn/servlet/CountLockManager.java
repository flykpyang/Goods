package com.goods.cn.servlet;

import com.fly.cn.Lock.CountLock;

public class CountLockManager {

    private static volatile CountLockManager countLockManager;
    //订单lock
    private CountLock orderCount;

    //打折卡卷
    private CountLock disCardCount;

    private final static byte[] lock = new byte[0];

    public static CountLockManager getIntanceCountLockManager() {
        if (countLockManager == null) {
            synchronized (lock) {
                if (countLockManager == null) {
                    countLockManager = new CountLockManager();
                }
            }
        }
        return countLockManager;
    }

    private CountLockManager() {
        orderCount = new CountLock();
        disCardCount = new CountLock();
    }

    /**
     * 初始化
     *
     * @param ordernumber
     * @param discardnumber
     */
    public void init(int ordernumber, int discardnumber) {
        orderCount.set(ordernumber);
        disCardCount.set(discardnumber);
    }

    public CountLock getOrderCount() {
        return orderCount;
    }



    public CountLock getDisCardCount() {
        return disCardCount;
    }


}
