package com.goods.cn.dao;

import com.goods.cn.po.Attachgoods;

import java.util.List;

public interface IAttachGoodsDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Attachgoods t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Attachgoods t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Attachgoods t) throws Exception;

    List<Attachgoods> findAttchGoodsByGoodsId(int appid,String goodsid)throws Exception;

}
