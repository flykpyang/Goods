package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IAppDao;
import com.goods.cn.po.App;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("AppDaoImpl")
public class AppDaoImpl extends BaseDao<App> implements IAppDao {
    public App findAppByAppId(final String appId) throws Exception {
        final StringBuilder builder = new StringBuilder();
        builder.append("from App a where a.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<App>() {
            public App doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(builder.toString());
                query.setCacheable(true);
                query.setParameter(0, appId);
                List<App> apps = query.list();
                if (apps != null && apps.size() > 0) {
                    return apps.get(0);
                }
                return null;
            }
        });
    }

    public List<App> findAllApp() throws Exception {
        StringBuilder builder=new StringBuilder();
        builder.append("from App a where a.cIsEnable=1");
        return find(builder.toString());
    }

    public App findAppById(int id) throws Exception {
        return get(App.class,id);
    }
}
