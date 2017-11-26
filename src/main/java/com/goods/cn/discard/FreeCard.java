package com.goods.cn.discard;



import com.goods.cn.config.BaseConfig;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;

import java.util.List;

/**
 * Created by fly on 17/3/28.
 */
public class FreeCard extends BaseDisCard {
    @Override
    public double computeEffectValue(Usercard userCard, OrderInfo orderInfo) {
        try {
            if (userCard.getcIdentification() == BaseConfig.FREECARDID) {
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
                userCard.setcCardvalue(tempprice);
                userCardService.updateUserCard(userCard);
                return Double.valueOf(tempprice);
            }
        } catch (Exception e) {

        }
        return -1;
    }
}
