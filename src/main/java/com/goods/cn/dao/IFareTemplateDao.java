package com.goods.cn.dao;

import com.goods.cn.po.Faretemplate;

public interface IFareTemplateDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Faretemplate t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Faretemplate t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Faretemplate t) throws Exception;

    Faretemplate findFareTempLateById(int id,int appid)throws Exception;

}
