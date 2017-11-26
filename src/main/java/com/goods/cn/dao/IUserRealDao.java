package com.goods.cn.dao;



import com.goods.cn.po.Userreal;

import java.util.List;

public interface IUserRealDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Userreal t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Userreal t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Userreal t) throws Exception;

    /**
     *
     * @param openid
     * @param isuse
     * @return
     * @throws Exception
     */
    List<Userreal> findAllUserRealByOpenid(String openid, boolean isuse,int appid)throws Exception;
}
