package com.goods.cn.service;

import com.goods.cn.action.Gift;
import com.goods.cn.po.Discard;
import com.goods.cn.po.UserInfo;
import com.goods.cn.po.Usercard;
import com.goods.cn.util.GoodsUtil;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by fly on 2017/7/19.
 */
@Service("giftservice")
public class GiftService extends BaseService {


    public void giveGift(List<Gift> gifts, UserInfo userInfo, int from, Object c) throws Exception {

        if (gifts != null) {
            for (int i = 0; i < gifts.size(); i++) {
                Gift gift = gifts.get(i);
                List<Discard> disCards = gift.userCards;
                if (disCards != null) {
                    for (int j = 0; j < disCards.size(); j++) {
                        Discard disCard = disCards.get(j);
                        Usercard userCard = GoodsUtil.coverUserCardByDisCard(disCard, userInfo, from, c);
                        userCardDao.save(userCard);
                    }
                }
                //更新sore
                int sore = gift.coffeesore;
                int usore = userInfo.getcSore();
                usore += sore;
                userInfo.setcSore(usore);
                System.out.println("gift userinfo=" + userInfo.getcId());
                userDao.update(userInfo);
            }
        }
    }

}
