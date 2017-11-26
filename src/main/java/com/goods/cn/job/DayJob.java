package com.goods.cn.job;

import com.fly.cn.dao.PageQueryResult;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.action.*;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.fixtime.FixTimeGoodsManager;
import com.goods.cn.po.*;
import com.goods.cn.service.*;
import com.goods.cn.servlet.GoodsDataDicManager;
import com.goods.cn.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public class DayJob {

    @Autowired
    UserService userService;
    @Autowired
    ActionService actionService;

    @Autowired
    ActionProxy actionProxy;

    @Autowired
    FixTimeGoodsManager fixTimeGoodsManager;

    @Autowired
    GoodsDataDicManager goodsDataDicManager;

    @Autowired
    AppService  appService;

    @Autowired
    RandDisPriceService randDisPriceService;

    @Autowired
    OrderService  orderService;

    public void execute() {
        try {
            System.out.println("定时的节日和生日job启动");
            List<App> apps=appService.findAllApp();
            //重新初始化vip
            goodsDataDicManager.initAllVipMap(apps);
            //初始化限时抢购
            fixTimeGoodsManager.initAllFixMap(apps);
            for(App app:apps){
                int appid=app.getcId();
                List<Gift> gifts = new ArrayList<Gift>();
                //节日活动 找到当天匹配的活动
                List<Action> holidayactions = actionService.findUseAction(BaseConfig.HOLIDAYACTION,appid);
                HolidayAction holidayAction = new HolidayAction();
                holidayAction.balance = BaseConfig.HOLIDAYACTION;
                sendDayAction(holidayactions, gifts, holidayAction,appid);
                //生日活动
                List<Action> birthdayactions = actionService.findUseAction(BaseConfig.BIRTHDAYACTION,appid);
                BirthDayAction birthDayAction = new BirthDayAction();
                birthDayAction.balance = BaseConfig.BIRTHDAYACTION;
                sendDayAction(birthdayactions, gifts, birthDayAction,appid);
                //随机数重新生成
                List<Randiscount> randDisPrices = randDisPriceService.findAllRandPrice(app.getcId());
                BaseUtil.initLottyer(randDisPrices,app.getcId());
                //订单自动更改状态
                PageQueryResult<DestDTO> pageQueryResult = orderService.findOrderByTimeAndStutas(1, 100,-7,appid);
                int pagecount = pageQueryResult.getPageCount();
                modifyOrderStustas(pageQueryResult);
                if (pagecount > 1) {
                    for (int i = 2; i <= pagecount; i++) {
                        pageQueryResult = orderService.findOrderByTimeAndStutas(i, 100,7,appid);
                        modifyOrderStustas(pageQueryResult);
                    }
                }
            }

        } catch (Exception e) {

        }
    }

    private void modifyOrderStustas(PageQueryResult<DestDTO> pageQueryResult)throws Exception{
        List<DestDTO> destDTOS=pageQueryResult.getQueryResult();
        for(DestDTO destDTO:destDTOS){
            OrderInfo orderInfo=destDTO.getOrderinfo();
            orderInfo.setcStatus((byte)3);
            orderInfo.setcMtime(TimeUtil.getCurrTimeHm());
            orderService.update(orderInfo);
        }
    }



    private void sendDayAction(List<Action> actions, List<Gift> gifts, BaseAction baseAction,int appid) throws Exception {
        for (Action action : actions) {
            int vid = action.getcVip();
            List<UserInfo> userInfos = userService.findAllUserInfoByVid(vid,appid);
            //发送奖品
            for (UserInfo userInfo : userInfos) {
                baseAction.userInfo = userInfo;
                if(baseAction.filterAction(action,null)) {
                    Gift gift = baseAction.getActionGift(action);
                    gifts.clear();
                    gifts.add(gift);
                    baseAction.giveGift(gifts);
                }
            }
        }
    }
}
