package com.goods.cn.discard;


import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;

/**
 * Created by fly on 17/3/27.
 */
public class FixedDisCard extends BaseDisCard{

    @Override
    public double computeEffectValue(Usercard userCard, OrderInfo orderInfo) {
        double value= Double.valueOf(userCard.getcCardvalue());
        return  value;
    }
}
