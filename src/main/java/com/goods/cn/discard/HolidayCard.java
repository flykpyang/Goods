package com.goods.cn.discard;

import com.alibaba.fastjson.JSONObject;
import com.goods.cn.po.HolidayRemark;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 节日卡
 */
public class HolidayCard extends BaseDisCard{
    @Override
    public double computeEffectValue(Usercard userCard, OrderInfo orderInfo) {
        String remark=userCard.getcCardremark();
        HolidayRemark holidayRemark= JSONObject.parseObject(remark,HolidayRemark.class);
        //使用时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-dd");
        String day=simpleDateFormat.format(new Date());
        if(day.equals(holidayRemark.getTime())){
            String totalPrice=orderInfo.getcTotalPrice();
            double p=Double.valueOf(totalPrice);
            if(holidayRemark.getPrice()!=null){
                //大于
                if(p<Double.valueOf(holidayRemark.getPrice())){
                    //不满足条件
                    return 0;
                }
            }
            if(holidayRemark.getCount()!=null){
                if(orderInfo.priceList.size()!=holidayRemark.getCount()){
                    return 0;
                }
            }
            String value=userCard.getcCardvalue();
            return Double.valueOf(value);
        }
        return -1;
    }
}
