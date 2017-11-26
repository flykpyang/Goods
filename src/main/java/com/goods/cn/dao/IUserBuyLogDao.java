package com.goods.cn.dao;

import com.goods.cn.po.UserFav;
import com.goods.cn.po.Userbuylog;

import java.util.Date;
import java.util.List;

public interface IUserBuyLogDao {
    /**
     * 更新操作
     *
     * @param t
     */
    void update(Userbuylog t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Userbuylog t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Userbuylog t) throws Exception;

    List<UserFav> findFavByOpenid(String openid,int size,int appid)throws Exception;

    long findUserAllBuyCount(String openid,int appid) throws Exception;

    long findUserBuyCountByTime(String openid, Date btime, Date etime,int appid)throws Exception;


}
