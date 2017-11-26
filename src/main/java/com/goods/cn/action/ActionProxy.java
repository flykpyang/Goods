package com.goods.cn.action;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.po.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by fly on 2017/7/20.
 * 活动代理类
 */
@Component("actionproxy")
public class ActionProxy {

    public BaseAction createRealAction(int balance, UserInfo userInfo, Object o) {
        BaseAction action = null;
        switch (balance) {
            case BaseConfig.LOGINACTION:
                action = new LoginAction(balance, userInfo, o);
                break;
            case BaseConfig.COMSUMEACTION:
                action= new ComsumeAction(balance,userInfo,(OrderInfo) o);
                break;
            case BaseConfig.REGSTERACTION:
                action= new RegisterAction(balance,userInfo,o);
                break;
            case BaseConfig.HOLIDAYACTION:
                action=new HolidayAction(balance,userInfo,o);
                break;
            case BaseConfig.UPDATEUSERINFOACTION:
                action=new UpdateUserInfoAction(balance,userInfo,o);
                break;
            case BaseConfig.FEEDBACKACTION:
                action=new FeedbackAction(balance,userInfo,(Feedback) o);
                break;
            case BaseConfig.FIXTIMECOFFEEACTION:
                action=new FixTimeCoffeeAction(balance,userInfo,(TreeMap<Type, List<GoodsInfo>>)o);
                break;
        }
        return action;
    }

    public List<Gift> findActionGift(BaseAction action){
        List<Gift> gifts=action.computeGift();
        return gifts;
    }

    public void giveGift(BaseAction action,List<Gift> gifts) throws Exception{
        action.giveGift(gifts);
    }

}
