package com.goods.cn.dao;

import com.goods.cn.po.Useraddress;

import java.util.List;

public interface IUserAddressDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Useraddress t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Useraddress t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Useraddress t) throws Exception;


    /**
     * 查询用户有没有更新信息的领奖记录
     * @param openid
     * @return
     * @throws Exception
     */
    List<Useraddress> findUserAddress(String openid, int appid) throws Exception;

    Useraddress findUserAddressByOpenidAndId(String openid ,int appid,int id) throws Exception;
}
