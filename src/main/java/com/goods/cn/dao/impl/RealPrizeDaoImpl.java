package com.goods.cn.dao.impl;


import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IRealPrizeDao;
import com.goods.cn.po.Realprize;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
@Repository("realprizeDao")
public class RealPrizeDaoImpl extends BaseDao<Realprize> implements IRealPrizeDao {

    public List<Realprize> getAllRealPrize() throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("from Realprize rp");
        List<Realprize> list = find(builder.toString());
        return list;
    }


    public Realprize getRealPrizeById(int id) throws Exception {
        StringBuilder builder=new StringBuilder();
        builder.append("from Realprize r where r.cId="+id);
        List<Realprize> realprizes=find(builder.toString());
        if(realprizes!=null&&realprizes.size()>0){
            return realprizes.get(0);
        }
        return null;
    }
}
