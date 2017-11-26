package com.goods.cn.dao.impl;


import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IRandDisPriceDao;
import com.goods.cn.po.Randiscount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
@Repository("randDisPriceDao")
public class RandDisPriceDaoImpl extends BaseDao<Randiscount> implements IRandDisPriceDao {
    public List<Randiscount> findAllRandPrice(int appid) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("from Randiscount rt where rt.cAppid="+appid);
        List<Randiscount> list = find(builder.toString());
        return list;
    }
}
