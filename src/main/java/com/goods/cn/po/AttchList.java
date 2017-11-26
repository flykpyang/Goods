package com.goods.cn.po;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 附属商品
 *
 * @author fly
 * @create 2017-11-16 17:17
 **/

public class AttchList {

    private int appid;
    private int attchid;
    private int count;


    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public int getAttchid() {
        return attchid;
    }

    public void setAttchid(int attchid) {
        this.attchid = attchid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] agrs){
        List<AttchList> lists=new ArrayList<AttchList>();
        AttchList attchList=new AttchList();
        attchList.appid=2;
        attchList.attchid=1;
        attchList.count=1;
        lists.add(attchList);
        String json= JSONObject.toJSONString(lists);
        System.out.println(json);
    }
}
