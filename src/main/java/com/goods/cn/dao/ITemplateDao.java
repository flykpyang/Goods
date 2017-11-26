package com.goods.cn.dao;

import com.goods.cn.po.Template;

public interface ITemplateDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Template t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Template t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Template t) throws Exception;

    Template findTemplateByappidAndType(String appid,int type)throws Exception;

}
