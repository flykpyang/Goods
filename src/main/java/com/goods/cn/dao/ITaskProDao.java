package com.goods.cn.dao;


import com.goods.cn.po.Taskpro;

public interface ITaskProDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Taskpro t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Taskpro t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Taskpro t) throws Exception;

    Taskpro findTaskProByOpenidAndStutas(String openid, int staus,int appid) throws Exception;
}
