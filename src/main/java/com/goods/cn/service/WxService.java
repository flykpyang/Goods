package com.goods.cn.service;

import com.goods.cn.po.App;
import com.goods.cn.po.WxModel;
import com.goods.cn.util.WxPayUtil;
import org.springframework.stereotype.Service;

@Service("WxService")
public class WxService extends BaseService{

    public WxModel getWxMode(String openid,int appid,String url)throws Exception{
        App app=appDao.findAppById(appid);
        if(app!=null) {
            WxModel wxModel = WxPayUtil.getWxTicket(openid,app.getcAppid(),appid,url);
            return wxModel;
        }
        return null;
    }
}
