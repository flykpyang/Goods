package com.goods.cn.dao;



import com.goods.cn.po.Action;
import com.goods.cn.po.UserInfo;

import java.util.List;

/**
 * Created by fly on 2017/7/19.
 */
public interface IActionDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Action t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Action t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Action t) throws Exception;

    /**
     * 找到所有的action
     *
     * @param balance
     * @param userInfo
     * @return
     * @throws Exception
     */
    List<Action> findAllAction(int balance, UserInfo userInfo,int appid) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    List<Action> findTotalAction(int appid) throws Exception;

    /**
     * 找到结算点的活动
     * @param balance
     * @return
     */
    List<Action>  findDayAction(int balance,int appid);
}
