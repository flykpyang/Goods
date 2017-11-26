package com.goods.cn.dao;

import com.goods.cn.po.Love;

public interface ILoveDao {
    /**
     * 更新操作
     *
     * @param t
     */
    void update(Love t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Love t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Love t) throws Exception;

    Love findLoveByOpenidAndAppidAndGoodsId(String openid,int appid,String goodsid)throws Exception;

    long findLoveGoods(String goodsid,int appid)throws Exception;

    Love findLoveById(int id)throws Exception;

}
