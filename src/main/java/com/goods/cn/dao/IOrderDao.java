package com.goods.cn.dao;

import com.fly.cn.dao.PageQueryResult;
import com.goods.cn.po.OrderInfo;


import java.util.Date;
import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
public interface IOrderDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(OrderInfo t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(OrderInfo t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(OrderInfo t) throws Exception;

    /**
     * 找到所有的order
     *
     * @param orderId
     * @param openid
     * @return
     * @throws Exception
     */
    List<OrderInfo> findOrderByOrderId(String orderId, String openid,Integer appid) throws Exception;


    /**
     * 找到所有已经完成的order
     *
     * @param openid
     * @return
     * @throws Exception
     */
    List<OrderInfo> findFinishOrderByOpneid(String openid,int appid) throws Exception;

    /**
     * 分页查找所有的orderinfo
     * @param openid
     * @param pageindex
     * @param pagesize
     * @return
     * @throws Exception
     */
    PageQueryResult<DestDTO> getOrderInfoPage(String openid, int pageindex, int pagesize,int appid) throws Exception;

    /**
     *
     * @param openid
     * @param btime
     * @param etime
     * @return
     * @throws Exception
     */
    List<OrderInfo> findUserOrderByTime(final String openid, final Date btime, final Date etime,int appid)throws Exception;

    /**
     * 找到指定天数的正在配送中的订单
     * @param daynumber
     * @return
     * @throws Exception
     */
    PageQueryResult<DestDTO> findOrderByTimeAndStutas(int pageindex, int pagesize,int daynumber,int appid)throws Exception;
}
