package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IGoodsInfoDao;
import com.goods.cn.po.GoodsInfo;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository("GoodsInfoDaoImpl")
public class GoodsInfoDaoImpl extends BaseDao<GoodsInfo> implements IGoodsInfoDao {
    public List<GoodsInfo> findAllGoodsList(final int appid) throws Exception {
        final StringBuilder builder = new StringBuilder();
        builder.append("from GoodsInfo g where g.cAppid=?");
        builder.append("  and  g.cEnable=1");
        return getHibernateTemplate().execute(new HibernateCallback<List<GoodsInfo>>() {
            public List<GoodsInfo> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0, appid);
                return query.list();
            }
        });
    }

    public  GoodsInfo findGoodsInfoByid(final String goodsid) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from GoodsInfo g where g.cGoodsid=?");
        return getHibernateTemplate().execute(new HibernateCallback<GoodsInfo>() {
            public GoodsInfo doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0, goodsid);
                return (GoodsInfo)query.uniqueResult();
            }
        });
    }

    public  GoodsInfo findGoodsInfoByidLock(final String goodsid) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from GoodsInfo g where g.cGoodsid=?");
        return getHibernateTemplate().execute(new HibernateCallback<GoodsInfo>() {
            public GoodsInfo doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0, goodsid);
                query.setLockMode("g", LockMode.PESSIMISTIC_WRITE);
                //query.setLockOptions(LockOptions.UPGRADE);
                return (GoodsInfo)query.uniqueResult();
            }
        });
    }

    public List<GoodsInfo> findAllUseGoodsList(final int appid) throws Exception {
        final StringBuilder builder = new StringBuilder();
        builder.append("from GoodsInfo g where g.cAppid=?");
        builder.append("  and  g.cEnable=1");
        builder.append("  order by g.cOrder");
        return getHibernateTemplate().execute(new HibernateCallback<List<GoodsInfo>>() {
            public List<GoodsInfo> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(builder.toString());
                //query.setCacheable(true);
                query.setParameter(0, appid);
                return query.list();
            }
        });
    }
}
