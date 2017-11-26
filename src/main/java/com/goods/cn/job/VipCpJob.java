package com.goods.cn.job;

import com.fly.cn.dao.PageQueryResult;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.po.App;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.UserInfo;
import com.goods.cn.po.VipTitle;
import com.goods.cn.service.AppService;
import com.goods.cn.service.OrderService;
import com.goods.cn.service.UserBuyLogService;
import com.goods.cn.service.UserService;
import com.goods.cn.servlet.GoodsDataDicManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class VipCpJob {

    @Autowired
    UserService userService;
    @Autowired
    UserBuyLogService userBuyLogService;
    @Autowired
    OrderService orderService;

    @Autowired
    AppService   appService;

    @Autowired
    GoodsDataDicManager goodsDataDicManager;



    /**
     * 定时执行vip的计算job
     */
    public void execute() {
        try {
            List<App> apps = appService.findAllApp();
            for (App app : apps) {
                int appid=app.getcId();
                PageQueryResult<DestDTO> pageQueryResult = userService.findAllUserInfo(1, 100,appid);
                int pagecount = pageQueryResult.getPageCount();
                List<VipTitle> vipTitles = goodsDataDicManager.getVips(appid);
                modifyUserVip(pageQueryResult,vipTitles);
                if (pagecount > 1) {
                    for (int i = 2; i <= pagecount; i++) {
                        pageQueryResult = userService.findAllUserInfo(i, 100,appid);
                        modifyUserVip(pageQueryResult,vipTitles);
                    }
                }
            }
        }catch(Exception e){

        }
    }

    private void modifyUserVip(PageQueryResult<DestDTO> pageQueryResult,List<VipTitle> vipTitles) throws Exception {
        List<DestDTO> destDTOS = pageQueryResult.getQueryResult();
        for (DestDTO destDTO : destDTOS) {
            UserInfo userInfo = destDTO.getUserInfo();
            cpUserInfoVip(userInfo,vipTitles);
        }
    }

    private VipTitle findVipTitile(int rank,List<VipTitle> vipTitles) {
        for (VipTitle vipTitle : vipTitles) {
            int vrank = vipTitle.getRank();
            if (vrank == rank) {
                return vipTitle;
            }
        }
        return null;
    }


    private void cpUserInfoVip(UserInfo userInfo,List<VipTitle> vipTitles) throws Exception {
        //最后更新vip的时间
        int rank = userInfo.getcVid();
        int appid=userInfo.getcAppid();
        if (rank > 1&&rank<1000) {
            Date date = userInfo.getcVtime();
            VipTitle vipTitle = findVipTitile(rank,vipTitles);
            int bm = vipTitle.getBalanceMonth();
            Date bgtime = TimeUtil.getCurrDay(-30 * bm);
            Date egtime = TimeUtil.getCurrDay(0);
            String openid = userInfo.getcOpenid();

            if (bgtime.after(date)) {
                //如果结算时间比成为会员时间大于bm个月 则判断时候满足vip的持有资格
                String price = vipTitle.getBalancePrice();
                int count = vipTitle.getBalanceBuyCount();
                //计算这指定时间的消费卡卷和消费额
                long buycount = userBuyLogService.findUserBuyCountByTime(openid, bgtime, egtime,appid);
                List<OrderInfo> orderInfos = orderService.findUserfulOrderBytime(openid, bgtime, egtime,appid);
                double totalprice = 0;
                if (orderInfos != null) {
                    for (OrderInfo orderInfo : orderInfos) {
                        String oprice = orderInfo.getcTotalPrice();
                        totalprice += Double.valueOf(oprice);
                    }
                }
                if (totalprice < Double.valueOf(price) || buycount < count) {
                    //不达标
                    long curcount=userBuyLogService.findBuyCount(openid,appid);
                    rank--;
                    userInfo.setcVid(rank);
                    userInfo.setcVtime(TimeUtil.getCurrTimeHm());
                    userInfo.setcLastVmonetary(userInfo.getcMonetary());
                    userInfo.setcLastCount((int)curcount);
                    userService.update(userInfo);
                }
            }
        }
    }
}
