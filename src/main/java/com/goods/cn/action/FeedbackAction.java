package com.goods.cn.action;
import com.goods.cn.po.Action;
import com.goods.cn.po.Feedback;
import com.goods.cn.po.UserInfo;

public class FeedbackAction extends BaseAction<Feedback>{

    public FeedbackAction(int balance, UserInfo userInfo, Feedback o) {
        super(balance, userInfo, o);
    }


    public boolean filterAction(Action action, Object O) {
        return true;
    }
}
