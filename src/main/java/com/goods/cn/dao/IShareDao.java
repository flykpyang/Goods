package com.goods.cn.dao;

import com.goods.cn.po.Share;

public interface IShareDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Share t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Share t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Share t) throws Exception;

    Share getShareByAppidAndAction(String action,int appid)throws Exception;

}
