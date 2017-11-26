package com.goods.cn.dao;



import com.goods.cn.po.Randiscount;

import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
public interface IRandDisPriceDao {
    /**
     * 更新操作
     *
     * @param t
     */
    void update(Randiscount t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Randiscount t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Randiscount t) throws Exception;

    /**
     * @return
     * @throws Exception
     */
    List<Randiscount> findAllRandPrice(int appid) throws Exception;
}
