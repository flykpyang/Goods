package com.goods.cn.dao.impl;


import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IDisCardDao;
import com.goods.cn.po.Discard;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by fly on 2017/7/19.
 */
@Repository("discardDao")
public class DisCardDaoImpl extends BaseDao<Discard> implements IDisCardDao {

    public Discard findDisCardById(int id) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("from Discard d where d.cId=" + id);
        List<Discard> list = find(builder.toString());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    public List<Discard> findIsChangeCard(int appid) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("from Discard d where d.cIschange=1");
        sql.append(" and d.cAppid="+appid);
        List<Discard> list = find(sql.toString());
        return list;
    }


    public List<Discard> findAllDisCard(final int appid) throws Exception {
        final  StringBuilder builder=new StringBuilder();
        builder.append(" from Discard d");
        builder.append(" where d.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<List<Discard>>() {

            public List<Discard> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(builder.toString());
                query.setParameter(0,appid);
                query.setCacheable(true);
                return query.list();
            }
        });
    }
}
