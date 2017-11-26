package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IAttachGoodsDao;
import com.goods.cn.po.Attachgoods;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * attachdao实现
 *
 * @author fly
 * @create 2017-11-15 21:35
 **/

@Repository("AttachGoodsDaoImpl")
public class AttachGoodsDaoImpl extends BaseDao<Attachgoods> implements IAttachGoodsDao{
    @Override
    public List<Attachgoods> findAttchGoodsByGoodsId(final int appid,final String goodsid) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Attachgoods a where a.cIsEnable=1");
        builder.append("  and a.cAppid=?");
        builder.append("  and a.cStrategy like ?");
        return getHibernateTemplate().execute(new HibernateCallback<List<Attachgoods>>() {
            @Override
            public List<Attachgoods> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(builder.toString());
                query.setParameter(0,appid);
                query.setParameter(1,"%"+goodsid+"%");
                return query.list();
            }
        });

    }
}
