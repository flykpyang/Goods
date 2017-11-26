package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IShareDao;
import com.goods.cn.po.Share;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ShareDaoImpl")
public class ShareDaoImpl extends BaseDao<Share> implements IShareDao{
    @Override
    public Share getShareByAppidAndAction(String action, int appid) throws Exception {
        StringBuilder builder=new StringBuilder();
        builder.append("from Share s where s.cAppid="+appid);
        builder.append("  and s.cAction='"+action+"'");
        List<Share> shares=find(builder.toString());
        if(shares!=null&&shares.size()>0){
            return shares.get(0);
        }
        return null;
    }
}
