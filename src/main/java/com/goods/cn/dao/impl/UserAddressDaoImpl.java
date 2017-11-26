package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IUserAddressDao;
import com.goods.cn.po.Useraddress;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("UserAddressDaoImpl")
public class UserAddressDaoImpl extends BaseDao<Useraddress> implements IUserAddressDao {
    public List<Useraddress> findUserAddress(final String openid, final int appid) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Useraddress u where u.cAppid=?");
        builder.append(" and u.cOpenid=?");
        builder.append(" and u.cEnable=1");
        builder.append(" order by u.cLastUse desc");
        return getHibernateTemplate().execute(new HibernateCallback<List<Useraddress>>() {
            public List<Useraddress> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(builder.toString());
                query.setParameter(0,appid);
                query.setParameter(1,openid);
                return query.list();
            }
        });
    }

    public Useraddress findUserAddressByOpenidAndId(final String openid,final int appid, final int id) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Useraddress u where u.cAppid=?");
        builder.append(" and u.cOpenid=?");
        builder.append(" and u.cId=?");
        builder.append(" and u.cEnable=1");
        return getHibernateTemplate().execute(new HibernateCallback<Useraddress>() {
            public Useraddress doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(builder.toString());
                query.setParameter(0,appid);
                query.setParameter(1,openid);
                query.setParameter(2,id);
                List<Useraddress> list=query.list();
                if(list!=null&&list.size()>0){
                    return list.get(0);
                }
                return null;
            }
        });
    }
}
