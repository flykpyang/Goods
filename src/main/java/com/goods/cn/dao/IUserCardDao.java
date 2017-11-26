package com.goods.cn.dao;



import com.goods.cn.po.Usercard;

import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
public interface IUserCardDao {


    /**
     * 更新操作
     *
     * @param t
     */
    void update(Usercard t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Usercard t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Usercard t) throws Exception;

    /**
     * @param userCard
     * @return
     * @throws Exception
     */
    Usercard checkUserCard(Usercard userCard) throws Exception;

    /**
     * @param openid
     * @param isuse
     * @return
     * @throws Exception
     */
    List<Usercard> findAllCardByOpenid(String openid,int appid,boolean isuse) throws Exception;

    /**
     * @param cardid
     * @return
     * @throws Exception
     */
    Usercard findCardByCardid(String cardid,int appid) throws Exception;

    //查询所有离过期还有3天的卡片
    List<Usercard>  findAllCardInfoPastTime(int appid) throws Exception;

    /**
     *
     * @param orderid
     * @return
     * @throws Exception
     */
    Usercard findCardByForderid(String orderid) throws Exception;

    /**
     * 查找用户是否领过生日卡卷
     * @return
     * @throws Exception
     */
    List<Usercard> findUserBirthCardByYear(String openid,int appid)throws Exception;
}
