package com.goods.cn.action;
import com.goods.cn.po.Action;
import com.goods.cn.po.UserInfo;
import java.util.List;

/**
 * Created by fly on 17/3/28.
 */
public interface IAction<E> {
    /**
     *
     * @param balance 结算点 活动类型
     * @param userInfo 用户
     * @return 如果为空 则不能享受
     */
    List<Action> getAllAction(int balance, UserInfo userInfo);

    /**
     *
     * @param action
     * @param O
     * @return true 则可以享受
     */
    boolean filterAction(Action action, E O);

    /**
     *
     * @param action 有效活动
     * @return 所有活动设定的卡卷
     */
    Gift getActionGift(Action action);
}
