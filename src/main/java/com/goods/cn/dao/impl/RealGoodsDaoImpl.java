package com.goods.cn.dao.impl;


import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IRealGoodsDao;
import com.goods.cn.po.Realgoods;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("realgoodsDao")
public class RealGoodsDaoImpl extends BaseDao<Realgoods> implements IRealGoodsDao {

    public List<Realgoods> findIsChangeRealGoods(final int appid) throws Exception {
        final  StringBuilder builder=new StringBuilder();
        builder.append("from Realgoods r where r.cChange=1");
        builder.append(" and r.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<List<Realgoods>>() {
            public List<Realgoods> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0,appid);
                //TODO 测试阶段先关闭 打开查询缓存
                query.setCacheable(true);
                return  query.list();
            }
        });
    }


    public Realgoods findRealGoodsById(int id) throws Exception {
        //这里用get都可以用二级缓存查到
        return get(Realgoods.class,id);
    }
}
