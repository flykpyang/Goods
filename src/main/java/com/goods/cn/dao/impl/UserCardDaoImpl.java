package com.goods.cn.dao.impl;


import com.fly.cn.dao.BaseDao;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.dao.IUserCardDao;
import com.goods.cn.po.Usercard;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
@Repository("usercardDao")
public class UserCardDaoImpl extends BaseDao<Usercard> implements IUserCardDao {

    public List<Usercard> findAllCardByOpenid(String openid,int appid,boolean isuse) throws Exception {
        StringBuilder builder = new StringBuilder();
        List<Object> paramsList = new ArrayList<Object>();
        builder.append(" FROM Usercard uc where uc.cOpenid=?");
        paramsList.add(openid);
        builder.append(" and uc.cAppid=?");
        paramsList.add(appid);
        //查询有效卡片
        if (isuse) {
            builder.append(" and uc.cExpiretime>=? and uc.cIsuse=0");
        } else {
            builder.append(" and (uc.cExpiretime<=? or uc.cIsuse=1)");
        }
        Date currTime = TimeUtil.getCurrDay(0);
        paramsList.add(currTime);
        builder.append(" order by uc.cIsuse, uc.cExpiretime asc ");
        List<Usercard> usercards = (List<Usercard>) getHibernateTemplate().find(builder.toString(), paramsList.toArray());
        return usercards;
    }


    public Usercard findCardByCardid(String cardid,int appid) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("FROM Usercard uc where uc.cCardid='" + cardid + "' ");
        builder.append("  and uc.cAppid="+appid);
        List<Usercard> list = find(builder.toString());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    public List<Usercard> findAllCardInfoPastTime(final int appid) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Usercard ca where  ca.cExpiretime>?");
        builder.append("  and ca.cExpiretime<?  ");
        builder.append("and ca.cIsuse=0");
        builder.append(" and ca.cAppid=?");
        return  getHibernateTemplate().execute(new HibernateCallback<List<Usercard>>() {

            public List<Usercard> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(builder.toString());
                Date now=TimeUtil.getCurrDay(0);
                query.setParameter(0,now);
                //+3天
                Date date=TimeUtil.getCurrDay(3);
                query.setParameter(1,date);
                query.setParameter(2,appid);
                query.setCacheable(true);
                return query.list();
            }
        });
    }


    public Usercard findCardByForderid(String orderid) throws Exception {
        StringBuilder builder=new StringBuilder();
        builder.append("from Usercard u where u.cFromorderid='"+orderid+"'");
        List<Usercard> usercards=find(builder.toString());
        if(usercards!=null&&usercards.size()>0){
            return usercards.get(0);
        }
        return null;
    }


    public List<Usercard> findUserBirthCardByYear(String openid,int appid) throws Exception {
        List<Object> paraList=new ArrayList<Object>();
        StringBuilder builder=new StringBuilder();
        builder.append("from Usercard u where u.cOpenid=?");
        paraList.add(openid);
        builder.append(" and u.cBegintime>?");
        System.out.println(TimeUtil.getCurrYear(0));
        paraList.add(TimeUtil.getCurrYear(0));
        builder.append(" and u.cBegintime<?");
        System.out.println(TimeUtil.getCurrYear(1));
        paraList.add(TimeUtil.getCurrYear(1));
        builder.append(" and u.cFrom="+ BaseConfig.BIRTHDAYACTION);
        builder.append(" and u.cAppid=?");
        paraList.add(appid);
        return find(builder.toString(),paraList.toArray());
    }


    public Usercard checkUserCard(Usercard userCard) throws Exception {
        String openid = userCard.getcOpenid();
        String cardid = userCard.getcCardid();
        StringBuilder builder = new StringBuilder();
        builder.append("from Usercard uc where uc.cOpenid='" + openid + "'");
        builder.append("  and uc.cCardid='" + cardid + "'");
        List<Usercard> list = find(builder.toString());
        if (list != null && list.size() == 1) {
            //继续判断是否过期
            Usercard searchCard = list.get(0);
            userCard = searchCard;
            int isuseful = userCard.getcIsuse();
            Date extime = userCard.getcExpiretime();
            Date now=TimeUtil.getCurrDay(0);
            if (isuseful == 0 && extime.after(now)) {
                return userCard;
            }
        }
        return null;
    }
}
