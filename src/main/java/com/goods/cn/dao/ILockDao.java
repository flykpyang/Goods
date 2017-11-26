package com.goods.cn.dao;

import com.goods.cn.po.Lock;

import java.util.List;

public interface ILockDao {
    /**
     * 更新操作
     * @param t
     */
    void update(Lock t) throws Exception;

    /**
     * 删除操作
     * @param t
     */
    void delete(Lock t) throws Exception;

    /**
     * 增加操作
     * @param t
     */
    void save(Lock t) throws  Exception;

    /**
     *
     * @return
     */
    List<Lock> getAllLock() throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    Lock findLockByid(int id) throws Exception;
}
