package com.goods.cn.discard;

import com.goods.cn.config.BeanDaoImplConfig;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;
import com.goods.cn.service.UserCardService;
import com.goods.cn.servlet.SpringConfigTool;

/**
 * Created by fly on 17/3/27.
 */
public abstract class BaseDisCard implements IDisCard {

    public abstract double computeEffectValue(Usercard userCard, OrderInfo orderInfo);

    UserCardService userCardService;

    public double getDisEffectValue(Usercard userCard, OrderInfo orderInfo) {
        userCardService = (UserCardService) SpringConfigTool.getBean(BeanDaoImplConfig.USERCARDSERVICE);
        try {
            Usercard realUsercard = userCardService.checkUserCard(userCard);
            if (realUsercard != null) {
                double value = computeEffectValue(realUsercard, orderInfo);
                return value;
            }
        } catch (Exception e) {

        }
        return -1;
    }
}
