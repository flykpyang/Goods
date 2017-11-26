package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.fly.cn.dao.PageQueryResult;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.dao.IUserDao;
import com.goods.cn.po.UserInfo;
import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("UserDaoImpl")
public class UserDaoImpl extends BaseDao<UserInfo> implements IUserDao{
    public UserInfo checkUserByOpenid(final String openid, final int appid) throws Exception {
        final StringBuilder builder = new StringBuilder();
        builder.append("from UserInfo  user where user.cOpenid=?");
        builder.append(" and user.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<UserInfo>() {
            public UserInfo doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0, openid);
                query.setParameter(1,appid);
                query.setLockMode("user", LockMode.PESSIMISTIC_WRITE);
                //query.setLockOptions(LockOptions.UPGRADE);
                //TODO 测试阶段先关闭 打开查询缓存
                //query.setCacheable(true);
                List<UserInfo> list = query.list();
                if (list != null && list.size() > 0) {
                    return list.get(0);
                }
                return null;
            }
        });
       // return getHibernateTemplate().load(UserInfo.class,1016,LockMode.PESSIMISTIC_WRITE);
    }

    public String findUserFav(String openid,int appid) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("select  user.cFav from UserInfo user where user.cOpenid='" + openid + "'");
        builder.append("  and user.cAppid= "+appid);
        List<String> list = find(builder.toString());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public List<UserInfo> findAllUserByVid(final int vid, final int appid) throws Exception {
        final StringBuilder builder = new StringBuilder();
        builder.append("from UserInfo user where user.cVid>=?");
        builder.append("  and user.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<List<UserInfo>>() {
            public List<UserInfo> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(builder.toString());
                query.setParameter(0, vid);
                query.setParameter(1,appid);
                query.setCacheable(true);
                List<UserInfo> list = query.list();
                return list;
            }
        });
    }

    public PageQueryResult<DestDTO> findAllUserInfo(int pageindex, int pagesize,int appid) throws Exception {
        List<Object> paramsList = new ArrayList<Object>();
        StringBuilder builder = new StringBuilder();
        builder.append("from UserInfo user where user.cAppid=?");
        paramsList.add(appid);
        return this.commonQuery(builder.toString(), paramsList.toArray(), pageindex, pagesize, DestDTO.class);
    }
}
