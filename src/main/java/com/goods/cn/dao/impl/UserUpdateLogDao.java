package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IUpdateUserDao;
import com.goods.cn.po.Userupdatelog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userUpdateLogDao")
public class UserUpdateLogDao extends BaseDao<Userupdatelog> implements IUpdateUserDao {

    public Userupdatelog findUserUpdateLog(String openid,int appid) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("from Userupdatelog u where u.cOpenid='" + openid + "'");
        builder.append("  and u.cAppid="+appid);
        builder.append("  and u.cIsaction=1");
        List<Userupdatelog> list = find(builder.toString());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
