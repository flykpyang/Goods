package com.goods.cn.action;

import com.fly.cn.util.TimeUtil;
import com.goods.cn.po.Action;
import com.goods.cn.po.UserInfo;
import java.util.Date;

/**
 * Created by fly on 17/4/17.
 */
public class HolidayAction extends BaseAction {
    public HolidayAction(int balance, UserInfo userInfo, Object o) {

        super(balance, userInfo, o);
    }

    public HolidayAction() {
    }


    public boolean filterAction(Action action, Object O) {
        try {
            String holiday =action.getcCondition();
            Date today = TimeUtil.getCurrDay(0);
            long todaymis=today.getTime();
            if(holiday!=null&&holiday.trim().length()>0){
                long holidaymis=Long.valueOf(holiday);
                if(holidaymis==todaymis){
                    return true;
                }
            }
        } catch (Exception e) {

        }
        return false;
    }
}
