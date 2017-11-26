package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.ILockDao;
import com.goods.cn.po.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("LockDaoImpl")
public class LockDaoImpl extends BaseDao<Lock> implements ILockDao{
    public List<Lock> getAllLock() throws Exception {
        return loadAll(Lock.class);
    }

    public Lock findLockByid(int id) throws Exception {
        return get(Lock.class,id);
    }
}
