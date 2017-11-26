package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IAdddicDao;
import com.goods.cn.po.Adddic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AdddicDaoimpl")
public class AdddicDaoimpl extends BaseDao<Adddic> implements IAdddicDao{
    @Override
    public Adddic getAdddicByCode(String code) throws Exception {
        StringBuilder builder=new StringBuilder();
        builder.append("from Adddic addr where addr.cCode='"+code+"'");
        List<Adddic> adddics=find(builder.toString());
        if(adddics!=null&&adddics.size()>0){
            return adddics.get(0);
        }
        return null;
    }
}
