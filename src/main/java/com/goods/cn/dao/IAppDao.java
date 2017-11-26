package com.goods.cn.dao;

import com.goods.cn.po.App;

import java.util.List;

public interface IAppDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(App t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(App t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(App t) throws Exception;

    /**
     * 根据appid查找app
     * @param appId
     * @return
     * @throws Exception
     */
    App  findAppByAppId(String appId)throws  Exception;

    List<App> findAllApp()throws Exception;

    App  findAppById(int id)throws Exception;
}
