package com.goods.cn.po;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 附加商品策略
 *
 * @author fly
 * @create 2017-11-15 12:25
 **/

public class AttachStrategy {

    private List<String>  goodsids;//搭配的主菜
    private String ratio;//主配比例

    public List<String> getGoodsids() {
        return goodsids;
    }

    public void setGoodsids(List<String> goodsids) {
        this.goodsids = goodsids;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public static void main(String[] agrs){
        AttachStrategy attachStrategy=new AttachStrategy();
        List<String> list=new ArrayList<String>();
        list.add("0af0bdf27a72164a95478951ec894233");
        list.add("bc4da68b9b276f699f4d552e324a6d4c");
        attachStrategy.goodsids=list;
        attachStrategy.ratio="1";
        String json= JSONObject.toJSONString(attachStrategy);
        System.out.println(json);
    }
}
