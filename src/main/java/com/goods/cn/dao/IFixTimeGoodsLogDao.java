package com.goods.cn.dao;



import com.goods.cn.po.Fixtimegoodslog;

import java.util.List;

public interface IFixTimeGoodsLogDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(Fixtimegoodslog t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Fixtimegoodslog t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Fixtimegoodslog t) throws Exception;

    List<Fixtimegoodslog> findAllFixTimeLogByTimeAndOpenId(String openid, String fixTime, String goodsId,int appid) throws Exception;

    List<Fixtimegoodslog>  findFixTimeLogByOrderId(String orderId,int appid)throws Exception;

}
