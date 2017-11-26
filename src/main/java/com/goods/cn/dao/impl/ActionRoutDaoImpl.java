package com.goods.cn.dao.impl;


import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IActionRoutDao;
import com.goods.cn.po.ActionRout;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("actionroutDao")
public class ActionRoutDaoImpl extends BaseDao<ActionRout> implements IActionRoutDao {

    public ActionRout getActionByDate(final Date date, final String openid, final int appid) throws Exception {
        final StringBuilder builder = new StringBuilder();
        builder.append("from ActionRout ar where date(ar.cTime)=?");
        builder.append(" and ar.cOpenid=?");
        builder.append(" and ar.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<ActionRout>() {
            public ActionRout doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0, date);
                query.setParameter(1, openid);
                query.setParameter(2,appid);
                //TODO 测试阶段先关闭
                //query.setCacheable(true);
                query.setLockMode("ar", LockMode.PESSIMISTIC_WRITE);
                List<ActionRout> actionRouts = query.list();
                if (actionRouts != null && actionRouts.size() > 0) {
                    return actionRouts.get(0);
                }
                return null;
            }
        });
    }
}
