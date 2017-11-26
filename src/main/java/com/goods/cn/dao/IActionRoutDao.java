package com.goods.cn.dao;


import com.goods.cn.po.ActionRout;

import java.util.Date;

public interface IActionRoutDao {

    /**
     * 更新操作
     *
     * @param t
     */
    void update(ActionRout t) throws Exception;

    /**
     * 删除操作
     *
     * @param t
     */
    void delete(ActionRout t) throws Exception;

    /**
     * 增加操作
     *
     * @param t
     */
    void save(ActionRout t) throws Exception;

    /**
     * @param date
     * @param openid
     * @return
     * @throws Exception
     */
    ActionRout getActionByDate(Date date, String openid,int appid) throws Exception;

}
