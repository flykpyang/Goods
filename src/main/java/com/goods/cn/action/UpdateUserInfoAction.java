package com.goods.cn.action;
import com.goods.cn.po.Action;
import com.goods.cn.po.UserInfo;
public class UpdateUserInfoAction extends BaseAction{

    public UpdateUserInfoAction(int balance, UserInfo userInfo, Object o) {
        super(balance, userInfo, o);
    }

    public UpdateUserInfoAction() {
    }


    public boolean filterAction(Action action, Object O) {
        return true;
    }
}
