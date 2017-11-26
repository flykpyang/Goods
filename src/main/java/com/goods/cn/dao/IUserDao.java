package com.goods.cn.dao;

import com.fly.cn.dao.PageQueryResult;
import com.goods.cn.po.UserInfo;

import java.util.List;

public interface IUserDao {

    /**
     * 更新操作
     * @param t
     */
    void update(UserInfo t) throws Exception;

    /**
     * 删除操作
     * @param t
     */
    void delete(UserInfo t) throws Exception;

    /**
     * 增加操作
     * @param t
     */
    void save(UserInfo t) throws  Exception;

    /**
     * @param openid
     * @return
     * @throws Exception
     */
    UserInfo checkUserByOpenid(String openid,int appid) throws Exception;

    /**
     * 根据openid 找用户嗜好
     *
     * @param openid
     * @return
     * @throws Exception
     */
    String findUserFav(String openid,int appid) throws Exception;

    /**
     * 找到所有用户根据>=vid
     * @param vid
     * @return
     * @throws Exception
     */
    List<UserInfo> findAllUserByVid(int vid,int appid) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    PageQueryResult<DestDTO> findAllUserInfo(int pageindex, int pagesize,int appid) throws Exception;
}
