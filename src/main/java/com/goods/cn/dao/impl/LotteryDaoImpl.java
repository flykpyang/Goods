package com.goods.cn.dao.impl;


import com.fly.cn.dao.BaseDao;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.dao.ILotteryDao;
import com.goods.cn.po.Lottery;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository("lotteryDao")
public class LotteryDaoImpl extends BaseDao<Lottery> implements ILotteryDao {

    public Lottery findUserfulLotteryAction(final int appid) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Lottery l where l.cStatus=1");
        builder.append(" and l.cBeginday<=?");
        builder.append(" and l.cEndday>=?");
        builder.append(" and l.cAppid=?");
        final Date date= TimeUtil.getCurrDay(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        final  String time=sdf.format(new Date());
        return getHibernateTemplate().execute(new HibernateCallback<Lottery>() {

            public Lottery doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(builder.toString());
                //query.setCacheable(true);
                query.setParameter(0,date);
                query.setParameter(1,date);
                query.setParameter(2,appid);
                List<Lottery> lotteries=query.list();
                long bc=-24*3600*1000;
                Lottery templottery=null;
                if(lotteries!=null){
                    for(Lottery lottery:lotteries){
                        String bt=lottery.getcBegintime();
                        String et=lottery.getcEndtime();
                        long tempbc=TimeUtil.compareTimeByHM(time,bt);
                        long ec=TimeUtil.compareTimeByHM(time,et);
                        if(tempbc>=0&&ec<=0){
                            //可以抽奖
                            lottery.bc=0;
                            return lottery;
                        }
                        if(tempbc<0&&tempbc>bc){
                            bc=tempbc;
                            lottery.bc=tempbc;
                            templottery=lottery;
                        }
                    }
                }
                return templottery;
            }
        });
    }
}
