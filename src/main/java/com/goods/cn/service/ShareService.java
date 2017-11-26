package com.goods.cn.service;

import com.goods.cn.po.Share;
import org.springframework.stereotype.Service;

@Service("ShareService")
public class ShareService extends BaseService{

    public Share getShare(String action,int appid)throws Exception{
        return shareDao.getShareByAppidAndAction(action,appid);
    }
}
