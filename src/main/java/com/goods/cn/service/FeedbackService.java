package com.goods.cn.service;


import com.fly.cn.dao.PageQueryResult;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.po.Feedback;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("feedbackService")
public class FeedbackService extends BaseService{

    public void save(Feedback feedback) throws Exception{
        feedbackDao.save(feedback);
    }

    public PageQueryResult<DestDTO> findFeedBackByDate(int pageindex, int pagesize, Date date,int appid)throws Exception{
        return feedbackDao.findAllFeedBack(pageindex,pagesize,date,appid);
    }
}
