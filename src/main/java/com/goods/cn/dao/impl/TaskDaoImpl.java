package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.ITaskDao;
import com.goods.cn.po.Task;
import com.goods.cn.po.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskDao")
public class TaskDaoImpl extends BaseDao<Task> implements ITaskDao {
    public Task findUserTask(UserInfo userInfo,int appid) throws Exception {
        StringBuilder builder=new StringBuilder();
        int vid=userInfo.getcVid();
        builder.append("from Task task where task.cVip<="+vid+"  and task.cAppid="+appid+"   order by task.cVip desc");
        List<Task> tasks=find(builder.toString());
        if(tasks!=null&&tasks.size()>0){
            return tasks.get(0);
        }
        return null;
    }
}
