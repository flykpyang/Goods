package com.goods.cn.dao;


import com.goods.cn.po.Vip;

import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
public interface IVipDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Vip t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Vip t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Vip t) throws Exception;

    /**
     * 找到VIP
     *
     * @param rank
     * @return
     * @throws Exception
     */
    Vip getVipByvid(int rank,int appid) throws Exception;

    /**
     * 获取所有vip
     * @return
     * @throws Exception
     */
    List<Vip> getAllVip(int appid) throws Exception;
}
