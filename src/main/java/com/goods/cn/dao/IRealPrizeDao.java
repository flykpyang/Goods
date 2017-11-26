package com.goods.cn.dao;



import com.goods.cn.po.Realprize;

import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
public interface IRealPrizeDao {


    /**
     * 更新操作
     *
     * @param t
     */
    void update(Realprize t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Realprize t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Realprize t) throws Exception;

    /**
     * @return
     * @throws Exception
     */
    List<Realprize> getAllRealPrize() throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Realprize getRealPrizeById(int id) throws Exception;
}
