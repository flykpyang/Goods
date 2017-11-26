package com.goods.cn.service;


import com.goods.cn.fixtime.FixTimeGoodsManager;
import com.goods.cn.po.Fixtimegoodslog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("FixTimeGoodsLogService")
public class FixTimeGoodsLogService extends BaseService {

    @Autowired
    FixTimeGoodsManager fixTimeGoodsManager;

    public int getUserFixTimeCoffeeCount(String openid, String time, String coffeeid,int appid) throws Exception {
        int number = 0;
        List<Fixtimegoodslog> logs = fixTimeGoodsLogDao.findAllFixTimeLogByTimeAndOpenId(openid, time, coffeeid,appid);
        if (logs != null && logs.size() > 0) {
            for (Fixtimegoodslog fixtimegoodslog : logs) {
                int count = fixtimegoodslog.getcCount();
                number += count;
            }
        }
        return number;
    }

    public int getCurrTimeCupNumber(String timekey, String coffeeid,int appid) throws Exception {
        return fixTimeGoodsManager.getCoffeeNumberCount(timekey, coffeeid,appid);
    }
}
