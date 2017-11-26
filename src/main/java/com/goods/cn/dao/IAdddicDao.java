package com.goods.cn.dao;

import com.goods.cn.po.Adddic;

public interface IAdddicDao {
    /**
     * 更新操作
     *
     * @param t
     */
    void update(Adddic t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Adddic t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Adddic t) throws Exception;


    Adddic getAdddicByCode(String code)throws Exception;

}
