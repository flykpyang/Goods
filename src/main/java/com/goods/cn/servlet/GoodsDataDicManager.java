package com.goods.cn.servlet;

import com.alibaba.fastjson.JSONObject;
import com.goods.cn.po.App;
import com.goods.cn.po.Vip;
import com.goods.cn.po.VipTitle;
import com.goods.cn.service.AppService;
import com.goods.cn.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component("GoodsDataDicManager")
public class GoodsDataDicManager {


    @Autowired
    AppService appService;

    @Autowired
    VipService vipService;

    private HashMap<Integer, List<VipTitle>> vipTitleMap;

    @PostConstruct
    public void init() throws Exception {
        //初始化
        vipTitleMap = new HashMap<Integer, List<VipTitle>>();
        initAllVipMap(null);
    }


    public void initAllVipMap(List<App> apps) throws Exception {
        if(apps==null) {
            apps = appService.findAllApp();
        }
        if (apps != null && apps.size() > 0) {
            vipTitleMap.clear();
            for (App app : apps) {
                initVips(app.getcId());
            }
        }
    }

    private void initVips(int appid) throws Exception {
        List<Vip> vips = vipService.getAllVip(appid);
        if (vips != null&&vips.size()>0) {
            List<VipTitle> vipTitles = new ArrayList<VipTitle>();
            vipTitleMap.put(appid, vipTitles);
            for (Vip vip : vips) {
                String remark = vip.getcRemark();
                if (remark != null && remark.trim().length() > 0) {
                    VipTitle vipTitle = JSONObject.parseObject(remark, VipTitle.class);
                    if (vipTitle != null) {
                        vipTitle.setRank(vip.getcRank());
                        vipTitles.add(vipTitle);
                    }
                }
            }
        }
    }

    public List<VipTitle> getVips(int appid) {
        return vipTitleMap.get(appid);
    }

    @PreDestroy
    public void  destroy(){
        vipTitleMap.clear();
    }
}
