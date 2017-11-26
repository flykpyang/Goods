package com.goods.cn.service;


import com.alibaba.fastjson.JSONObject;
import com.fly.cn.util.MoneyUtil;
import com.goods.cn.po.NextVip;
import com.goods.cn.po.UserInfo;
import com.goods.cn.po.Vip;
import com.goods.cn.po.VipTitle;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("vipService")
public class VipService extends BaseService {

    public List<Vip> getAllVip(int appid) throws Exception {
        return vipDao.getAllVip(appid);
    }

    public Vip getVip(int appid, int vid) throws Exception {
        return vipDao.getVipByvid(vid, appid);
    }

    public NextVip findNextVip(String openid, int appid) throws Exception {
        UserInfo userInfo = userDao.checkUserByOpenid(openid, appid);
        NextVip nVip = new NextVip();
        if (userInfo != null) {
            //用户总消费和购买商品数
            String totalprice = userInfo.getcMonetary();
            long totalcount = userBuyLogDao.findUserAllBuyCount(userInfo.getcOpenid(), appid);
            //当前等级
            int vid = userInfo.getcVid();
            String lastPrice = userInfo.getcLastVmonetary();
            int lastcount = userInfo.getcLastCount();
            //当前等级
            Vip vip = vipDao.getVipByvid(vid, appid);
            double cumprice = Double.valueOf(totalprice) - Double.valueOf(lastPrice);
            long cumcount = totalcount - lastcount;
            if (vip != null && vip.getcRemark() != null) {
                //当前等级title
                VipTitle currvipTitle = JSONObject.parseObject(vip.getcRemark(), VipTitle.class);
                String currprice = currvipTitle.getPrice();
                int curbuycount = currvipTitle.getBuycount();
                int nextvid = ++vid;
                Vip nextvip = vipDao.getVipByvid(nextvid, appid);
                if (nextvip != null && nextvip.getcRemark() != null) {
                    VipTitle nextvipTitle = JSONObject.parseObject(nextvip.getcRemark(), VipTitle.class);
                    String nextprice = nextvipTitle.getPrice();
                    int nextbuycount = nextvipTitle.getBuycount();
                    double gleverprice = Double.valueOf(nextprice) - Double.valueOf(currprice);
                    int glevercount = nextbuycount - curbuycount;
                    double nprice = gleverprice - cumprice;
                    int ncount = glevercount - (int)cumcount;
                    double needprice=MoneyUtil.coverTwoMoney(nprice);
                    nVip.count = ncount>0?ncount:0;
                    nVip.price = needprice>0?needprice:0;
                }
            }
        }
        return nVip;
    }

}
