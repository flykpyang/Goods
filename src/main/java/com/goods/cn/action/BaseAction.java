package com.goods.cn.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goods.cn.config.BeanDaoImplConfig;
import com.goods.cn.po.Action;
import com.goods.cn.po.Discard;
import com.goods.cn.po.UserInfo;
import com.goods.cn.service.ActionService;
import com.goods.cn.service.DisCardService;
import com.goods.cn.service.GiftService;
import com.goods.cn.servlet.SpringConfigTool;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fly on 17/3/28.
 */
public abstract class BaseAction<E> implements IAction {
    public int balance;
    public UserInfo userInfo;
    public String remark;
    //实际数目 也就是用户当前的数据
    E c;
    GiftService giftService;

    public BaseAction(int balance, UserInfo userInfo, E o) {
        this.balance = balance;
        this.userInfo = userInfo;
        this.c = o;
        giftService = (GiftService) SpringConfigTool.getBean(BeanDaoImplConfig.GIFTSERIVE);
    }

    public BaseAction() {
        giftService = (GiftService) SpringConfigTool.getBean(BeanDaoImplConfig.GIFTSERIVE);
    }

    public List<Action> getAllAction(int balance, UserInfo userInfo) {
        ActionService actionService = (ActionService) SpringConfigTool.getBean(BeanDaoImplConfig.ACTIONSERVICE);
        try {
            List<Action> actions = actionService.findAllActionBy(balance, userInfo);
            if (actions.size() > 0) {
                Action action = actions.get(0);
                remark = action.getcName();
            }
            return actions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Gift getActionGift(Action action) {
        String giftstr = action.getcGift();
        Gift gift = new Gift();
        List<Discard> userCards = new ArrayList<Discard>();
        DisCardService disCardService = (DisCardService) SpringConfigTool.getBean(BeanDaoImplConfig.DISCARDSERVICE);
        try {
            JSONObject object = JSONObject.parseObject(giftstr);
            if (object.containsKey("card")) {
                JSONArray cardArray = object.getJSONArray("card");
                for (int i = 0; i < cardArray.size(); i++) {
                    JSONObject cardObject = cardArray.getJSONObject(i);
                    int id = cardObject.getInteger("id");
                    Discard disCard = disCardService.findDisCardById(id);
                    if (disCard != null) {
                        userCards.add(disCard);
                    }
                }
                gift.userCards = userCards;
            }
            if (object.containsKey("sore")) {
                int sore = object.getInteger("sore");
                gift.coffeesore = sore;
            }
            if (object.containsKey("discount")) {
                String discount = object.getString("discount");
                gift.discount = discount;
            }
            return gift;
        } catch (Exception e) {

        }
        return null;
    }


    public List<Gift> computeGift() {
        List<Action> actions = getAllAction(balance, userInfo);
        List<Gift> gifts = new ArrayList<Gift>();
        if(actions!=null) {
            for (int i = 0; i < actions.size(); i++) {
                Action action = actions.get(i);
                boolean flag = filterAction(action, c);
                if (flag) {
                    Gift gift = getActionGift(action);
                    if (gift != null) {
                        gifts.add(gift);
                    }
                }
            }
        }
        return gifts;
    }

    public void giveGift(List<Gift> gifts) throws Exception {
        if (gifts == null) {
            gifts = computeGift();
        }
        giftService.giveGift(gifts, userInfo, balance, c);
    }
}
