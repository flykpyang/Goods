package com.goods.cn.dao;

import com.goods.cn.po.GoodsInfo;
import org.hibernate.LockMode;

import java.util.List;

public interface IGoodsInfoDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void  update(GoodsInfo t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(GoodsInfo t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(GoodsInfo t) throws Exception;

    /**
     * 找到所有咖啡
     *
     * @param appid
     * @return
     * @throws Exception
     */
    List<GoodsInfo> findAllGoodsList(int appid) throws Exception;

    /**
     * 根据id找到咖啡
     *
     * @param goodsid
     * @return
     * @throws Exception
     */
    GoodsInfo findGoodsInfoByid(String goodsid) throws Exception;

    /**
     * 根据id找到咖啡
     *
     * @param goodsid
     * @return
     * @throws Exception
     */
    GoodsInfo findGoodsInfoByidLock(String goodsid) throws Exception;

    /**
     * 找到上架所有咖啡
     *
     * @param appid
     * @return
     * @throws Exception
     */
    List<GoodsInfo> findAllUseGoodsList(int appid) throws Exception;

}
