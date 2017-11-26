package com.goods.cn.dao.impl;


import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IVipDao;
import com.goods.cn.po.Vip;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
@Repository("vipDao")
public class VipDaoImpl extends BaseDao<Vip> implements IVipDao {

    public Vip getVipByvid(int rank,int appid) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("from Vip vip where vip.cRank=" + rank);
        builder.append("  and vip.cAppid="+appid);
        List<Vip> list = find(builder.toString());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    public List<Vip> getAllVip(int appid) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Vip v where v.cAppid="+appid+" order by v.cRank desc");
        return getHibernateTemplate().execute(new HibernateCallback<List<Vip>>() {
            public List<Vip> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(builder.toString());
                query.setCacheable(true);
                return query.list();
            }
        });
    }
}
