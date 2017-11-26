package com.goods.cn.util;


import com.fly.cn.Lock.CountLock;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.servlet.CountLockManager;

/**
 * Created by fly on 17/4/5.
 */
public class LockUtil {

    /**
     * 生成唯一订单号
     *
     * @return
     */
    public static String creatOrderCount() {
        String orderid = TimeUtil.coverFormatTimeByMin(String
                .valueOf(System.currentTimeMillis()));
        CountLock countLock = CountLockManager.getIntanceCountLockManager().getOrderCount();
        if (countLock != null) {
            int count = countLock.getCount();
            orderid = orderid + String.valueOf(count);
            return orderid;
        } else {
            return null;
        }

    }


    /**
     * 生成唯一打折卡卷号
     *
     * @return
     */
    public static String creatDisCardCount() {
        String discardid = TimeUtil.coverFormatTimeByMin(String
                .valueOf(System.currentTimeMillis()));
        CountLock discountLock=CountLockManager.getIntanceCountLockManager().getDisCardCount();
        if(discountLock!=null) {
            int count = discountLock.getCount();
            discardid = discardid + String.valueOf(count);
            System.out.println("creat cardid="+discardid);
            return discardid;
        }else{
            return null;
        }
    }
}
