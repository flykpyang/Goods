package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IUserBuyLogDao;
import com.goods.cn.po.UserFav;
import com.goods.cn.po.Userbuylog;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("UserBuyLogDaoImpl")
public class UserBuyLogDaoImpl extends BaseDao<Userbuylog> implements IUserBuyLogDao{
    public List<UserFav> findFavByOpenid(String openid, int size, int appid) throws Exception {
        //SELECT d_mama_dish.t_order_info.c_creator , count( * ) AS count
        //FROM  d_mama_dish.t_order_info
        //GROUP BY c_openid
        //ORDER BY count DESC
        //LIMIT 100
        Criteria cri = getSessionFactory().getCurrentSession().createCriteria(Userbuylog.class);
        cri.setProjection(Projections.projectionList()
                .add(Projections.groupProperty("cGoodsid"), "c_goodsid")
                .add(Projections.count("cGoodsid"), "total")
        );
        cri.add(Restrictions.eq("cOpenid", openid));
        cri.addOrder(Order.desc("total"));
        cri.setMaxResults(size);
        //TODO 测试阶段先关闭 打开查询缓存
        //cri.setCacheable(true);
        cri.setResultTransformer(Transformers.aliasToBean(UserFav.class));
        return cri.list();
    }

    @Override
    public long findUserBuyCountByTime(String openid, Date btime, Date etime, int appid) throws Exception {
        StringBuilder builder=new StringBuilder();
        List<Object> paramList=new ArrayList<Object>();
        builder.append(" select count(*) from Userbuylog cf where ");
        builder.append("  cf.cOpenid=?");
        paramList.add(openid);
        builder.append(" and date(cf.cCtime)>=?");
        paramList.add(btime);
        builder.append(" and date(cf.cCtime)<=?");
        paramList.add(etime);
        builder.append(" and cf.cAppid=?");
        paramList.add(appid);
        Long count = (Long)find(builder.toString(),paramList.toArray()).listIterator().next();
        return count.longValue();
    }

    @Override
    public long findUserAllBuyCount(String openid,int appid) throws Exception {
        StringBuilder builder=new StringBuilder();
        builder.append("select count(*) from  Userbuylog cf where cf.cOpenid='"+openid+"'");
        builder.append(" and cf.cAppid="+appid);
        Long count = (Long)find(builder.toString()).listIterator().next();
        return count.longValue();
    }
}
