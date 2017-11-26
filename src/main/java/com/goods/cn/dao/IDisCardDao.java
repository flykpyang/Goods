package com.goods.cn.dao;


import com.goods.cn.po.Discard;

import java.util.List;

/**
 * Created by fly on 2017/7/19.
 */
public interface IDisCardDao {
    /**
     * 更新操作
     *
     * @param t
     */
    void update(Discard t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Discard t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Discard t) throws Exception;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    Discard findDisCardById(int id) throws Exception;


    /**
     * @return
     * @throws Exception
     */
    List<Discard> findIsChangeCard(int appid) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    List<Discard> findAllDisCard(int appid) throws Exception;

}
