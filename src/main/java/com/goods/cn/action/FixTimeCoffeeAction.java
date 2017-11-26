package com.goods.cn.action;

import com.alibaba.fastjson.JSONArray;
import com.fly.cn.util.MoneyUtil;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.config.BeanDaoImplConfig;
import com.goods.cn.po.*;
import com.goods.cn.service.FixTimeGoodsLogService;
import com.goods.cn.servlet.SpringConfigTool;
import java.text.SimpleDateFormat;
import java.util.*;

public class FixTimeCoffeeAction extends BaseAction {

    //打折的list
    List<GoodsInfo> coffeeInfos;
    Type disType;
    FixTimeGoodsLogService fixTimeCoffeeLogService;

    public FixTimeCoffeeAction(int balance, UserInfo userInfo, Object o) {
        super(balance, userInfo, o);
        coffeeInfos = new ArrayList<GoodsInfo>();
        disType = new Type();
        disType.setcId(0);
        disType.setcName("限时减免");
        disType.setcDes("限时减免");
        disType.setcOrder(-1);
        fixTimeCoffeeLogService = (FixTimeGoodsLogService) SpringConfigTool.getBean(BeanDaoImplConfig.FIXTIMECOFFEELOGSERVICE);
    }


    public boolean filterAction(Action action, Object O) {
        try {
            String condition = action.getcCondition();
            int appid=action.getcAppid();
            List<FixTimeGoods> list = JSONArray.parseArray(condition, FixTimeGoods.class);
            TreeMap<Type, List<GoodsInfo>> map = (TreeMap<Type, List<GoodsInfo>>) O;
            for (FixTimeGoods fixTimeGoods : list) {
                coverMap(map, fixTimeGoods,appid);
            }
            if (coffeeInfos.size() > 0) {
                map.put(disType, coffeeInfos);
            }
        } catch (Exception e) {

        }
        return false;
    }

    private void coverMap(TreeMap<Type, List<GoodsInfo>> map, FixTimeGoods fixTimeGoods,int appid) throws Exception {
        String goodsId = fixTimeGoods.getGoodsId();
        String bt = fixTimeGoods.getBeginTime();
        String et = fixTimeGoods.getEndTime();
        String price = fixTimeGoods.getDisPrice();
        int onlyNumber = fixTimeGoods.getOnlyCupNumber();
        if (userInfo != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            final String time = sdf.format(new Date());
            long bc = TimeUtil.compareTimeByHM(time, bt);
            long ec = TimeUtil.compareTimeByHM(time, et);
            if (bc > 0 && ec < 0) {
                //有效 则修改
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    Type type = (Type) entry.getKey();
                    if (type.getcId() != 0) {
                        //不为限时减免本身
                        List<GoodsInfo> list = (List<GoodsInfo>) entry.getValue();
                        for (GoodsInfo goodsInfo : list) {
                            if (goodsInfo.getcGoodsid().equals(goodsId)) {
                                double realp = MoneyUtil.coverTwoMoney(Double.valueOf(goodsInfo.getcPrice())
                                        - Double.valueOf(price));
                                String timekey = bt + "-" + et;
                                goodsInfo.realPrice = String.valueOf(realp);

                                goodsInfo.fixtimecount = fixTimeCoffeeLogService.getCurrTimeCupNumber(timekey, goodsId,appid);
                                goodsInfo.fixtimeonlyCupNumber = fixTimeGoods.getOnlyCupNumber();
                                String openid = userInfo.getcOpenid();
                                goodsInfo.fixtimekey = timekey;
                                //判断是否已经享受过这个活动了 已经购买的杯数
                                int count = fixTimeCoffeeLogService.getUserFixTimeCoffeeCount(openid,
                                        timekey,goodsId,appid);
                                System.out.println("已经使用杯数==" + count);
                                goodsInfo.isLeftCount = onlyNumber - count;
                                if (!coffeeInfos.contains(goodsInfo)&&goodsInfo.isLeftCount>0) {
                                    coffeeInfos.add(goodsInfo);
                                }
                                break;
                            }
                        }
                        list.removeAll(coffeeInfos);
                    }
                }
            }
        }
    }
}
