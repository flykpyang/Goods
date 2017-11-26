package com.goods.cn.service;


import com.goods.cn.po.Randiscount;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
@Service("randpriceservice")
public class RandDisPriceService extends BaseService{

    public List<Randiscount> findAllRandPrice(int appid) throws Exception{
        return randDisPriceDao.findAllRandPrice(appid);
    }
}
