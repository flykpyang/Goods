package com.goods.cn.dao;


import com.goods.cn.po.Task;
import com.goods.cn.po.UserInfo;

public interface ITaskDao {
    /**
     * 更新操作
     *
     * @param t
     */
    void update(Task t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(Task t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(Task t) throws Exception;

    /**
     * 找到对应的task
     * @param userInfo
     * @return
     * @throws Exception
     */
    Task  findUserTask(UserInfo userInfo,int appid) throws Exception;

}
