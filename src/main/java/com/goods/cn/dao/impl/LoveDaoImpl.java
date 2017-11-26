package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.ILoveDao;
import com.goods.cn.po.Love;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("LoveDaoImpl")
public class LoveDaoImpl extends BaseDao<Love> implements ILoveDao{
    @Override
    public long findLoveGoods(String goodsid,int appid) throws Exception {
        StringBuilder builder=new StringBuilder();
        List<Object> paramList=new ArrayList<Object>();
        builder.append(" select count(*) from Love l where ");
        builder.append(" l.cGoodsid=?");
        paramList.add(goodsid);
        builder.append(" and l.cAppid=?");
        paramList.add(appid);
        Long count = (Long)find(builder.toString(),paramList.toArray()).listIterator().next();
        return count.longValue();
    }

    @Override
    public Love findLoveById(int id) throws Exception {
        return get(Love.class,id);
    }

    @Override
    public Love findLoveByOpenidAndAppidAndGoodsId(final String openid, final int appid,final String goodsid) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Love l where l.cOpenid=?");
        builder.append("  and l.cGoodsid=?");
        builder.append("  and l.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<Love>() {
            @Override
            public Love doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(builder.toString());
                query.setParameter(0,openid);
                query.setParameter(1,goodsid);
                query.setParameter(2,appid);
                List<Love> loves=query.list();
                if(loves!=null&&loves.size()>0){
                    return loves.get(0);
                }
                return null;
            }
        });
    }
}
