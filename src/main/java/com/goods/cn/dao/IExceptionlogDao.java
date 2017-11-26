package com.goods.cn.dao;

import com.goods.cn.po.Exceptionlog;

public interface IExceptionlogDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Exceptionlog t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Exceptionlog t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Exceptionlog t) throws Exception;
}
