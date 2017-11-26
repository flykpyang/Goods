package com.goods.cn.dao;

import com.goods.cn.po.Faredes;

public interface IFaredesDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Faredes t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Faredes t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Faredes t) throws Exception;

    Faredes findFaredesByProCode(String proCode,int tempId)throws Exception;

}
