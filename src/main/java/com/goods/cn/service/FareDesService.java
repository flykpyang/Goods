package com.goods.cn.service;

import com.goods.cn.po.Faredes;
import org.springframework.stereotype.Service;

@Service("FareDesService")
public class FareDesService extends BaseService{
    public Faredes fidnFareDesByProCode(String proCode,int tempId)throws Exception{
        return faredesDao.findFaredesByProCode(proCode,tempId);
    }
}
