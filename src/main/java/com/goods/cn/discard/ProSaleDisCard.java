package com.goods.cn.discard;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;


/**
 * Created by fly on 17/4/19.
 */
public class ProSaleDisCard extends BaseDisCard{
    @Override
    public double computeEffectValue(Usercard userCard, OrderInfo orderInfo) {
        String remark=userCard.getcCardremark();
        String value=userCard.getcCardvalue();
        String coffeelist=orderInfo.getcCoffeelist();
        if(judgeIsuse(coffeelist,remark)){
            return  Double.valueOf(value);
        }
        return -1;
    }

    private boolean judgeIsuse(String coffeelist, String remark){
        try {
            JSONArray coffeeArray = JSONArray.parseArray(coffeelist);
            int len = coffeeArray.size();
            for (int i = 0; i < len; i++) {
                JSONObject coffeeCard = coffeeArray.getJSONObject(i);
                String coffeeid = coffeeCard.getString("goodsid");
                if(coffeeid.equals(remark)){
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
}
