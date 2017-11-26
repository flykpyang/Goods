package com.goods.cn.service;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.observes.MessageManager;
import com.goods.cn.discard.CardFactory;
import com.goods.cn.observer.message.DayTipsMessage;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.SimpleOrderInfo;
import com.goods.cn.po.UseCard;
import com.goods.cn.po.Usercard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by fly on 17/7/18.
 */
@Service("usercardservice")
public class UserCardService extends BaseService {

    /**
     * @param usercard
     * @return
     * @throws Exception
     */
    public Usercard checkUserCard(Usercard usercard) throws Exception {
        Usercard card = userCardDao.checkUserCard(usercard);
        return card;
    }

    /**
     * 更新
     *
     * @param usercard
     * @throws Exception
     */
    public void updateUserCard(Usercard usercard) throws Exception {
        userCardDao.update(usercard);
    }

    public void save(Usercard usercard) throws Exception {
        userCardDao.save(usercard);
    }

    public Usercard useCardComputeValue(UseCard useCard) throws Exception {
        String openid = useCard.openid;
        String cardid = useCard.cardid;
        int    appid=useCard.appid;
        List<SimpleOrderInfo> simpleOrderInfos = useCard.goodslist;
        double totalprice=0;
        //总价格
        List<String> priceList = new ArrayList<String>();
        for (SimpleOrderInfo simpleOrderInfo : simpleOrderInfos) {
            String price = simpleOrderInfo.price;
            Integer count = simpleOrderInfo.count;
            for (int k = 0; k < count; k++) {
                priceList.add(price);
                totalprice+=Double.valueOf(price);
            }
        }
        //排序
        Collections.sort(priceList, new Comparator<String>() {

            public int compare(String o1, String o2) {
                // TODO Auto-generated method stub
                Double d1 = Double.valueOf(o1);
                Double d2 = Double.valueOf(o2);
                return d2.compareTo(d1);
            }

        });
        Usercard userCard = userCardDao.findCardByCardid(cardid,appid);
        if (userCard != null) {
            int id = userCard.getcIdentification();
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.priceList = priceList;
            orderInfo.setcTotalPrice(String.valueOf(totalprice));
            orderInfo.setcCoffeelist(JSONObject.toJSONString(simpleOrderInfos));
            double value = CardFactory.getInstansCardFactory().creatCard(id).getDisEffectValue(userCard, orderInfo);
            if (value >= 0) {
                userCard.setcCardvalue(String.valueOf(value));
                return userCard;
            }
        }
        return null;
    }

    public List<Usercard> findAllTipCards(int appid) throws Exception{
        List<Usercard> usercards= userCardDao.findAllCardInfoPastTime(appid);
        DayTipsMessage message=new DayTipsMessage(usercards);
        MessageManager.getInstanceMessage().putMessage(message);
        return usercards;
    }

    public Usercard findCardByForderid(String orderid)throws Exception{
        return userCardDao.findCardByForderid(orderid);
    }

    public List<Usercard> findBirthDayUserCardByYear(String openid,int appid)throws Exception{
        return userCardDao.findUserBirthCardByYear(openid,appid);
    }
}
