package com.goods.cn.dao;

import com.goods.cn.po.Type;

import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
public interface ITypeDao {


    /**
     * 更新操作
     *
     * @param t
     */
    void update(Type t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Type t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Type t) throws Exception;


    /**
     * 根据id找到对应type
     *
     * @param id
     * @return
     * @throws Exception
     */
    Type getTypeById(int id) throws Exception;

    List<Type> getAllType(int appid) throws Exception;
}
