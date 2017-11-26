package com.goods.cn.dao.impl;


import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.ITaskProDao;
import com.goods.cn.po.Taskpro;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskproDao")
public class TaskProDaoImpl extends BaseDao<Taskpro> implements ITaskProDao {

    public Taskpro findTaskProByOpenidAndStutas(final String openid, final int staus, final int appid) throws Exception {
        final StringBuilder builder = new StringBuilder();
        builder.append("from Taskpro t where t.cOpenid=?");
        builder.append(" and t.cStatus=?");
        builder.append(" and t.cAppid=?");
        builder.append(" order by t.cTime desc");
        return getHibernateTemplate().execute(new HibernateCallback<Taskpro>() {
            public Taskpro doInHibernate(Session session) throws HibernateException {

                Query query = session.createQuery(builder.toString());
                query.setParameter(0, openid);
                query.setParameter(1,(byte) staus);
                query.setParameter(2,appid);
                query.setMaxResults(1);
                query.setLockMode("t", LockMode.PESSIMISTIC_WRITE);
                List<Taskpro> taskpros = query.list();
                if (taskpros != null && taskpros.size() > 0) {
                    return taskpros.get(0);
                }
                return null;
            }
        });
    }
}
