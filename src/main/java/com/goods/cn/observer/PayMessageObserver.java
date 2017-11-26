package com.goods.cn.observer;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.cn.config.MessageConfig;
import com.fly.cn.observes.IObservesr;
import com.fly.cn.observes.Message;
import com.fly.cn.observes.MessageManager;
import com.fly.cn.observes.TheadPool;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.action.ActionProxy;
import com.goods.cn.action.BaseAction;
import com.goods.cn.action.Gift;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.observer.message.PayMessage;
import com.goods.cn.po.*;
import com.goods.cn.service.*;
import com.goods.cn.servlet.GoodsDataDicManager;
import com.goods.cn.util.SendTempUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


/**
 * Created by fly on 17/7/10.
 */
@Component("payob")
public class PayMessageObserver implements IObservesr {

    @Autowired
    ActionProxy actionProxy;

    @Autowired
    UserService userService;

    @Autowired
    TemplateService templateService;

    @Autowired
    AppService appService;

    @Autowired
    UserAddressService userAddressService;

    @Autowired
    GoodsDataDicManager goodsDataDicManager;

    @Autowired
    VipService vipService;

    @Autowired
    UserBuyLogService userBuyLogService;


    @PostConstruct
    public void init() {
        MessageManager.getInstanceMessage().registerMessage(this, MessageConfig.PAYSUCESS);
        System.out.println("init payMessageObserver");
    }

    //  how  validate the  destory method is  a question
    @PreDestroy
    public void cleanUp() {
        MessageManager.getInstanceMessage().unRegisterMessage(this, MessageConfig.PAYSUCESS);
        System.out.println("cleanUp");
    }


    public void onNoticeMessage(Message message) {
        System.out.println("PayMessageObserver onNoticeMessage come in");
        final PayMessage payMessage = (PayMessage) message;
        final OrderInfo orderInfo = payMessage.order;
        final UserInfo userInfo = payMessage.userInfo;
        //发放奖励
        TheadPool.sendGiftThreadPoolExecutor.submit(new Runnable() {

            public void run() {
                try {
                    BaseAction action = actionProxy.createRealAction(BaseConfig.COMSUMEACTION, userInfo, orderInfo);
                    List<Gift> gifts = actionProxy.findActionGift(action);
                    if (gifts != null && gifts.size() > 0) {
                        System.out.println("gift="+gifts);
                        actionProxy.giveGift(action, gifts);
                    }
                } catch (Exception e) {

                }
            }
        });

        //用户vip等级计算 生成采购信息入库
        TheadPool.messageObserverWorkhreadPoolExecutor.submit(new Runnable() {
            public void run() {
                //总消费金额
                try {
                    //用户采购信息入库
                    String goodslist = orderInfo.getcCoffeelist();
                    JSONArray goodsArray = JSONArray.parseArray(goodslist);
                    for (int i = 0; i < goodsArray.size(); i++) {
                        JSONObject goodsObject = goodsArray.getJSONObject(i);
                        String goodid = goodsObject.getString("goodsid");
                        Integer count = goodsObject.getInteger("count");
                        for (int j = 0; j < count; j++) {
                            Userbuylog userbuylog = new Userbuylog();
                            userbuylog.setcAppid(orderInfo.getcAppid());
                            userbuylog.setcGoodsid(goodid);
                            userbuylog.setcOpenid(orderInfo.getcOpenid());
                            userBuyLogService.save(userbuylog);
                        }
                    }
                    //用户附件商品入库

                    int appid = orderInfo.getcAppid();
                    //用户总消费和购买商品数
                    String totalprice = userInfo.getcMonetary();
                    long   totalcount=userBuyLogService.findBuyCount(userInfo.getcOpenid(),appid);
                    //当前等级
                    int vid = userInfo.getcVid();
                    String  lastPrice=userInfo.getcLastVmonetary();
                    int     lastcount=userInfo.getcLastCount();
                    //当前等级
                    Vip vip = vipService.getVip(appid, vid);
                    double cumprice=Double.valueOf(totalprice)-Double.valueOf(lastPrice);
                    long   cumcount=totalcount-lastcount;
                    if (vip != null && vip.getcRemark() != null) {
                        //当前等级title
                        VipTitle currvipTitle = JSONObject.parseObject(vip.getcRemark(), VipTitle.class);
                        String currprice = currvipTitle.getPrice();
                        int curbuycount = currvipTitle.getBuycount();
                        List<VipTitle> vipTitles = goodsDataDicManager.getVips(appid);
                        if (vipTitles != null && vipTitles.size() > 0) {
                            for (VipTitle vipTitle : vipTitles) {
                                int rank = vipTitle.getRank();
                                if (rank > vid) {
                                    //判断用户是否升级
                                    String price = vipTitle.getPrice();
                                    if (price == null || price.equals("")) {
                                        price = "0";
                                    }
                                    int paycount = vipTitle.getBuycount();
                                    //得到升级条件
                                    double gleverprice = Double.valueOf(price) - Double.valueOf(currprice);
                                    int glevercount = paycount - curbuycount;
                                    if (cumprice >= gleverprice && cumcount >= glevercount) {
                                        //更新用户vip等级
                                        //更新用户信息
                                        userInfo.setcVid(rank);
                                        userInfo.setcVtime(TimeUtil.getCurrDay(0));
                                        userInfo.setcLastCount((int)totalcount);
                                        userInfo.setcLastVmonetary(totalprice);
                                        userService.update(userInfo);
                                        break;

                                    }
                                }
                            }
                        }
                    }

                    //发送信息模版
                    App app = appService.findAppById(orderInfo.getcAppid());
                    Useraddress useraddress = userAddressService.findAddressById(userInfo.getcOpenid(), userInfo.getcAppid(), orderInfo.getcAddrid());
                    if (app != null && useraddress != null) {
                        Template template = templateService.findTemplateByappidAndType(app.getcAppid(), BaseConfig.TEMPLATEORDER);
                        SendTempUtil.sendOrderTemp(orderInfo, template, useraddress, app.getcNotifyOpenid());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
