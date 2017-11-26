package com.goods.cn.po;

import com.alibaba.fastjson.JSONObject;

public class HolidayRemark {
    private String  time;
    private String  price;
    private Integer count;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public static void main(String[] agrs){
        HolidayRemark remark=new HolidayRemark();
        remark.time="10-01";
        remark.count=0;
        remark.price="10.1";
        String json= JSONObject.toJSONString(remark);
        System.out.println(json);
    }
}
