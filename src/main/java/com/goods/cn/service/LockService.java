package com.goods.cn.service;

import com.goods.cn.po.Lock;
import org.springframework.stereotype.Service;

@Service("LockService")
public class LockService extends BaseService{

    /**
     * 更新lock
     *
     * @param lock
     * @return
     */
    public void updateLock(Lock lock) throws Exception {
        lockDao.update(lock);
    }

    /**
     * 查询lock
     *
     * @param id
     * @return
     */
    public Lock findLock(int id) throws Exception {

        Lock lock = lockDao.findLockByid(id);
        return lock;

    }
}
