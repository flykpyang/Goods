package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.ITypeDao;
import com.goods.cn.po.Type;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
@Repository("typeDao")
public class TypeDaoImpl extends BaseDao<Type> implements ITypeDao {

    public Type getTypeById(final  int id) throws Exception {
        final   StringBuilder builder = new StringBuilder();
        builder.append("from Type type where type.cId=?");
        return getHibernateTemplate().execute(new HibernateCallback<Type>() {
            public Type doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0, id);
                //query.setCacheable(true);
                List<Type> types = query.list();
                if (types != null && types.size() > 0) {
                    return types.get(0);
                }
                return null;
            }
        });
    }


    public List<Type> getAllType(final int appid) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Type t where t.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<List<Type>>() {

            public List<Type> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0,appid);
                query.setCacheable(true);
                List<Type> types = query.list();
                return types;
            }
        });
    }
}
