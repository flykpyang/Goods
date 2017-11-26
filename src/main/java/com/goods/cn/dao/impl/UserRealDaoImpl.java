package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IUserRealDao;
import com.goods.cn.po.Userreal;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("userrealDao")
public class UserRealDaoImpl extends BaseDao<Userreal> implements IUserRealDao {

    public List<Userreal> findAllUserRealByOpenid(final String openid, boolean isuse, final int appid) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Userreal ul where ul.cOpenid=?");
        int flag=1;
        if(isuse){
            flag=0;
        }
        builder.append(" and ul.cIsuse="+flag);
        builder.append(" and ul.cAppid=?");
        builder.append(" order by ul.cIsuse  asc ");
        return  getHibernateTemplate().execute(new HibernateCallback<List<Userreal>>() {
            public List<Userreal> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0,openid);
                query.setParameter(1,appid);
                query.setCacheable(true);
                return query.list();
            }
        });
    }
}
