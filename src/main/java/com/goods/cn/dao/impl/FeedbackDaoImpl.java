package com.goods.cn.dao.impl;


import com.fly.cn.dao.BaseDao;
import com.fly.cn.dao.PageQueryResult;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.dao.IFeedbackDao;
import com.goods.cn.po.Feedback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("feedbackDao")
public class FeedbackDaoImpl extends BaseDao<Feedback> implements IFeedbackDao {

    public PageQueryResult<DestDTO> findAllFeedBack(int pageindex, int pagesize, Date date,int appid) throws Exception {
        StringBuilder builder=new StringBuilder();
        builder.append("from Feedback f where 1=1");
        List<Object> paramsList = new ArrayList<Object>();
        if(date!=null){
            builder.append(" and date(f.cTime)=?");
            paramsList.add(date);
        }
        builder.append(" and f.cAppid=?");
        paramsList.add(appid);
        return this.commonQuery(builder.toString(), paramsList.toArray(), pageindex, pagesize, DestDTO.class);
    }
}
