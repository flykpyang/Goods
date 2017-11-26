package com.goods.cn.service;


import com.goods.cn.po.Lotterylog;
import org.springframework.stereotype.Service;

@Service("lotterylogService")
public class LotteryLogService extends BaseService{

    public void save(Lotterylog lotterylog) throws Exception{
        lotteryLogDao.save(lotterylog);
    }

    public void update(Lotterylog lotterylog) throws Exception{
        lotteryLogDao.update(lotterylog);
    }
}
