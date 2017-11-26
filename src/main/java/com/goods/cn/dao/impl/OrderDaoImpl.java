package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.fly.cn.dao.PageQueryResult;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.dao.IOrderDao;
import com.goods.cn.po.OrderInfo;
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
@Repository("orderDao")
public class OrderDaoImpl extends BaseDao<OrderInfo> implements IOrderDao {

    public List<OrderInfo> findOrderByOrderId(String orderId, String openid,Integer appid) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("from OrderInfo order where 1=1");
        if (orderId != null) {
            builder.append(" and order.cOrderid='" + orderId + "'");
        }
        if (openid != null) {
            builder.append(" and order.cOpenid='" + openid + "'");
        }
        if(appid!=null) {
            builder.append(" and order.cAppid=" + appid);
        }
        List<OrderInfo> list = find(builder.toString());
        return list;
    }


    public List<OrderInfo> findFinishOrderByOpneid(String openid,int appid) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("from OrderInfo order where 1=1");
        if (openid != null) {
            builder.append(" and order.cOpenid='" + openid + "'");
        }
        builder.append(" and order.cAppid="+appid);
        builder.append(" and order.cStatus=1");
        builder.append(" order by  order.cCtime desc ");
        List<OrderInfo> list = find(builder.toString());
        return list;
    }


    public PageQueryResult<DestDTO> getOrderInfoPage(String openid, int pageindex, int pagesize,int appid) throws Exception {
        List<Object> paramsList = new ArrayList<Object>();
        StringBuilder builder = new StringBuilder();
        builder.append("from OrderInfo order where 1=1");
        if (openid != null) {
            builder.append(" and order.cOpenid=?");
            paramsList.add(openid);
        }
        builder.append(" and order.cAppid=?");
        paramsList.add(appid);
        builder.append(" and order.cStatus>0");
        builder.append(" order by  order.cCtime desc ");
        return this.commonQuery(builder.toString(), paramsList.toArray(), pageindex, pagesize, DestDTO.class);
    }

    public List<OrderInfo> findUserOrderByTime(final String openid, final Date btime, final Date etime, final int appid)throws Exception{
        final StringBuilder builder=new StringBuilder();
        builder.append("from OrderInfo o where ");
        builder.append(" o.cStatus=1 ");
        builder.append(" and o.cOpenid=?");
        builder.append(" and date(o.cCtime)>=?");
        builder.append(" and date(o.cCtime)<=?");
        builder.append(" and o.cAppid=?");
        return getHibernateTemplate().execute(new HibernateCallback<List<OrderInfo>>() {

            public List<OrderInfo> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(builder.toString());
                query.setParameter(0,openid);
                query.setParameter(1,btime);
                query.setParameter(2,etime);
                query.setParameter(3,appid);
                return query.list();
            }
        });
    }

    @Override
    public PageQueryResult<DestDTO> findOrderByTimeAndStutas(int pageindex, int pagesize, int daynumber, int appid) throws Exception {
        List<Object> paramsList = new ArrayList<Object>();
        StringBuilder builder = new StringBuilder();
        builder.append("from OrderInfo orderinfo where 1=1");
        builder.append(" and orderinfo.cStatus=2");
        builder.append(" and orderinfo.cAppid=?");
        paramsList.add(appid);
        builder.append(" and date(orderinfo.cMtime)<=?");
        Date mday= TimeUtil.getCurrDay(daynumber);
        paramsList.add(mday);
        return this.commonQuery(builder.toString(), paramsList.toArray(), pageindex, pagesize, DestDTO.class);
    }
}
