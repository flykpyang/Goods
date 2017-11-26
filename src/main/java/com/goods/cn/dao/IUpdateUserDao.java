package com.goods.cn.dao;


import com.goods.cn.po.Userupdatelog;

public interface IUpdateUserDao {
    /**
     * 更新操作
     *
     * @param t
     */
    void update(Userupdatelog t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Userupdatelog t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Userupdatelog t) throws Exception;


    /**
     * 查询用户有没有更新信息的领奖记录
     * @param openid
     * @return
     * @throws Exception
     */
    Userupdatelog findUserUpdateLog(String openid,int appid) throws Exception;


}
