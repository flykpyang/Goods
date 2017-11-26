package com.goods.cn.dao;


import com.goods.cn.po.Lottery;

public interface ILotteryDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Lottery t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Lottery t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Lottery t) throws Exception;

    /**
     * 找到当前可以用的抽奖活动
     * @return
     * @throws Exception
     */
    Lottery findUserfulLotteryAction(int appid) throws Exception;

}
