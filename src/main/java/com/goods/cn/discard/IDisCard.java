package com.goods.cn.discard;


import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;

/**
 * Created by fly on 17/3/27.
 */
public interface IDisCard {
    double getDisEffectValue(Usercard userCard, OrderInfo orderInfo);
}
