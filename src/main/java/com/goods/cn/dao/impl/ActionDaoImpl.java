package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.dao.IActionDao;
import com.goods.cn.po.Action;
import com.goods.cn.po.UserInfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by fly on 2017/7/19.
 */

@Repository("actionDao")
public class ActionDaoImpl extends BaseDao<Action> implements IActionDao {
    public List<Action> findAllAction(final int balance, final UserInfo userInfo, final int appid) throws Exception {
        final StringBuilder builder = new StringBuilder();
        builder.append("from Action ac where ac.cBalance=?");
        builder.append(" and date(ac.cBegintime)<=?");
        final Date currTime = TimeUtil.getCurrDay(0);
        builder.append(" and date(ac.cEndtime)>=?");
        builder.append(" and ac.cVip<=?");
        builder.append(" and ac.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<List<Action>>() {

            public List<Action> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0, balance);
                query.setParameter(1, currTime);
                query.setParameter(2, currTime);
                int rank=0;
                if(userInfo!=null) {
                    rank = userInfo.getcVid();
                }
                query.setParameter(3, rank);
                query.setParameter(4,appid);
                //TODO 测试阶段先关闭 打开查询缓存
                //query.setCacheable(true);
                List<Action> actions = query.list();
                return actions;
            }
        });
    }


    public List<Action> findDayAction(final int balance, final int appid) {
        final StringBuilder builder = new StringBuilder();
        builder.append("from Action ac where ac.cBalance=?");
        builder.append(" and date(ac.cBegintime)<=?");
        final Date currTime = TimeUtil.getCurrDay(0);
        builder.append(" and date(ac.cEndtime)>=?");
        builder.append(" and ac.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<List<Action>>() {

            public List<Action> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0, balance);
                query.setParameter(1, currTime);
                query.setParameter(2, currTime);
                query.setParameter(3,appid);
                //TODO 测试阶段先关闭 打开查询缓存
                //query.setCacheable(true);
                List<Action> actions = query.list();
                return actions;
            }
        });
    }

    public List<Action> findTotalAction(final int appid) throws Exception {
        final StringBuilder builder = new StringBuilder();
        builder.append("from Action a where a.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<List<Action>>() {

            public List<Action> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(builder.toString());
                query.setCacheable(true);
                query.setParameter(0,appid);
                return query.list();
            }
        });
    }
}
