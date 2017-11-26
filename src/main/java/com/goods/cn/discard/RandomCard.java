package com.goods.cn.discard;


import com.fly.cn.lottery.LotteryManager;
import com.fly.cn.lottery.Prize;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;

/**
 * Created by fly on 17/3/29.
 */
public class RandomCard extends BaseDisCard {
    @Override
    public double computeEffectValue(Usercard userCard, OrderInfo orderInfo) {
        double disprice = 0;
        try {
            String tempvalue = userCard.getcCardvalue();
            double value = Double.valueOf(tempvalue);
            System.out.println("RandomCard value=" + value);
            if (value < 0) {
                Prize prize = LotteryManager.getIntanceLotteryManager().turnLottery(userCard.getcAppid());
                //更新卡卷的状态
                userCard.setcCardvalue(prize.value);
                userCardService.updateUserCard(userCard);
                disprice = Integer.valueOf(prize.value);
            } else {
                disprice = value;
            }
        }catch (Exception e){

        }
        return disprice;
    }
}
