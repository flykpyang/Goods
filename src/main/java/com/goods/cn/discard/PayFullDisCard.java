package com.goods.cn.discard;

import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;

public class PayFullDisCard extends BaseDisCard {
    @Override
    public double computeEffectValue(Usercard userCard, OrderInfo orderInfo) {
        String totalPrice = orderInfo.getcTotalPrice();
        String remark = userCard.getcCardremark();
        String value = userCard.getcCardvalue();
        //满赠 的 标准
        double fullprice = Double.valueOf(remark);
        double payprice = Double.valueOf(totalPrice);
        if (payprice >= fullprice) {
            //符合标准
            return Double.valueOf(value);
        }
        return 0;
    }
}
