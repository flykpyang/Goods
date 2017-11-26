package com.goods.cn.dao;



import com.goods.cn.po.Realgoods;

import java.util.List;

public interface IRealGoodsDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Realgoods t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Realgoods t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Realgoods t) throws Exception;

    /**
     * @return
     * @throws Exception
     */
    List<Realgoods> findIsChangeRealGoods(int appid) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Realgoods findRealGoodsById(int id) throws Exception;
}
