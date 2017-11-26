package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IFareTemplateDao;
import com.goods.cn.po.Faretemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("FareTemplateDaoImpl")
public class FareTemplateDaoImpl extends BaseDao<Faretemplate> implements IFareTemplateDao {

    @Override
    public Faretemplate findFareTempLateById(int id, int appid) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("from Faretemplate f where  f.cId=" + id);
        builder.append(" and f.cAppid=" + appid);
        builder.append(" and f.cEnable=1");
        List<Faretemplate> faretemplates = find(builder.toString());
        if (faretemplates != null && faretemplates.size() > 0) {
            return faretemplates.get(0);
        }
        return null;
    }
}
