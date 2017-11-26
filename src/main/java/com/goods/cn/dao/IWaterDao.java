package com.goods.cn.dao;


import com.goods.cn.po.Water;

public interface IWaterDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Water t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Water t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Water t) throws Exception;

}
