package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IFaredesDao;
import com.goods.cn.po.Faredes;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("FareDesDaoImpl")
public class FareDesDaoImpl extends BaseDao<Faredes> implements IFaredesDao{
    @Override
    public Faredes findFaredesByProCode(final String proCode,final int tempId) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Faredes f where f.cContain like ?");
        builder.append(" and f.cTempid=?");
        return getHibernateTemplate().execute(new HibernateCallback<Faredes>() {
            @Override
            public Faredes doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(builder.toString());
                query.setParameter(0,"%"+proCode+"%");
                query.setParameter(1,tempId);
                List<Faredes> faredes=query.list();
                if(faredes!=null&&faredes.size()>0){
                    return faredes.get(0);
                }
                return null;
            }
        });
    }
}
