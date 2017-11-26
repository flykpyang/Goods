package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.ILotteryLogDao;
import com.goods.cn.po.Lotterylog;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository("lotteryLogDao")
public class LotteryLogDaoImpl extends BaseDao<Lotterylog> implements ILotteryLogDao {

    public Lotterylog findLogByOpenid(final String openid, final Date date, final int appid) throws Exception {
        final StringBuilder builder = new StringBuilder();
        builder.append("from Lotterylog l where l.cOpenid=?");
        builder.append(" and date(l.cTime)=?");
        builder.append(" and l.appid=?");
        return getHibernateTemplate().execute(new HibernateCallback<Lotterylog>() {
            public Lotterylog doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0, openid);
                query.setParameter(1, date);
                query.setParameter(2, appid);
                //TODO 测试阶段先关闭
                //query.setCacheable(true);
                query.setLockMode("l", LockMode.PESSIMISTIC_WRITE);
                List<Lotterylog> lotterylogs = query.list();
                if (lotterylogs != null && lotterylogs.size() > 0) {
                    return lotterylogs.get(0);
                }
                return null;
            }
        });
    }
}
