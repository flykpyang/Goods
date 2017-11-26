package com.goods.cn.dao;

import com.fly.cn.dao.PageQueryResult;
import com.goods.cn.po.Feedback;


import java.util.Date;

public interface IFeedbackDao {
    /**
     * 更新操作
     *
     * @param t
     */
    void update(Feedback t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Feedback t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Feedback t) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    PageQueryResult<DestDTO> findAllFeedBack(int pageindex, int pagesize, Date date,int appid) throws Exception;
}
