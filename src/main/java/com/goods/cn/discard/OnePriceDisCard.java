package com.goods.cn.discard;



import com.fly.cn.util.MoneyUtil;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;

import java.util.List;

public class OnePriceDisCard extends BaseDisCard {
    @Override
    public double computeEffectValue(Usercard userCard, OrderInfo orderInfo) {
        try {
            String tempprice = "0.00";
            List<String> allprice = orderInfo.priceList;
            if (allprice == null) {
                return -1;
            }
            if (allprice.size() == 0) {
                //使用卡片的数量 大于餐数
                return -1;
            }
            tempprice = allprice.get(0);
            allprice.remove(0);
            //一口价
            String price = userCard.getcCardremark();
            double onePrice = Double.valueOf(price);
            double highPrice = Double.valueOf(tempprice);
            double disPrice = 0.00;
            userCard.setcCardvalue("0");
            if (onePrice < highPrice) {
                //设置减免金额
                disPrice = MoneyUtil.coverTwoMoney(highPrice - onePrice);
                userCard.setcCardvalue(String.valueOf(disPrice));
            }
            userCardService.updateUserCard(userCard);
            return disPrice;
        } catch (Exception e) {

        }
        return -1;
    }
}
