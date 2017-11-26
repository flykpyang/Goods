package com.goods.cn.action;
import com.goods.cn.po.Action;
import com.goods.cn.po.UserInfo;
/**
 * Created by fly on 17/3/28.
 */
public class RegisterAction extends  BaseAction{
    public RegisterAction(int balance, UserInfo userInfo, Object o) {

        super(balance, userInfo, o);
    }

    public boolean filterAction(Action action, Object O) {
        System.out.println("RegisterAction come in");
        return true;
    }

}
