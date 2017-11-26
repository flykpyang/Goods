package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.dao.IFixTimeGoodsLogDao;
import com.goods.cn.po.Fixtimegoodslog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("FixTimeGoodsLogDaoImpl")
public class FixTimeGoodsLogDaoImpl extends BaseDao<Fixtimegoodslog> implements IFixTimeGoodsLogDao{
    public List<Fixtimegoodslog> findAllFixTimeLogByTimeAndOpenId(String openid, String fixTime, String goodsId,int appid) throws Exception {
        StringBuilder builder=new StringBuilder();
        List<Object> paraList=new ArrayList<Object>();
        builder.append("from Fixtimegoodslog f where f.cOpenid=?");
        paraList.add(openid);
        builder.append(" and f.cStutas=1");
        builder.append(" and f.cFixtime=?");
        builder.append(" and f.cGoodsid=?");
        builder.append(" and date(f.cTime)=?");
        builder.append(" and f.cAppid=?");
        paraList.add(fixTime);
        paraList.add(goodsId);
        paraList.add(TimeUtil.getCurrDay(0));
        paraList.add(appid);
        return find(builder.toString(),paraList.toArray());
    }

    public List<Fixtimegoodslog> findFixTimeLogByOrderId(String orderId,int appid) throws Exception {
        StringBuilder builder=new StringBuilder();
        builder.append("from Fixtimegoodslog f where f.cOrderid='"+orderId+"'");
        builder.append("  and f.cAppid="+appid);
        List<Fixtimegoodslog> fixTimeCoffees=find(builder.toString());
        return fixTimeCoffees;
    }
}
