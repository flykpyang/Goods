package com.goods.cn.dao;


import com.goods.cn.po.Lotterylog;

import java.util.Date;

public interface ILotteryLogDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Lotterylog t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Lotterylog t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Lotterylog t) throws Exception;

    /**
     *
     * @param openid
     * @return
     * @throws Exception
     */
    Lotterylog findLogByOpenid(String openid, Date date,int appid)throws Exception;
}
