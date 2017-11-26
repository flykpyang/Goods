package com.goods.cn.action;
import com.goods.cn.po.Action;
import com.goods.cn.po.UserInfo;

/**
 * Created by fly on 17/4/17.
 */
public class LoginAction extends BaseAction{

    public LoginAction(int balance, UserInfo userInfo, Object o) {
        super(balance, userInfo, o);
    }


    public boolean filterAction(Action action, Object O) {
       return true;
    }

}
