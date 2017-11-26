package com.goods.cn.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.cn.dao.PageQueryResult;
import com.fly.cn.observes.MessageManager;
import com.fly.cn.util.MoneyUtil;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.discard.CardFactory;
import com.goods.cn.discard.IDisCard;
import com.goods.cn.factory.WeiXinOrderNetAns;
import com.goods.cn.fixtime.FixTimeGoodsManager;
import com.goods.cn.observer.message.PayMessage;
import com.goods.cn.po.*;
import com.goods.cn.requestinfo.WeiXinPayRequestInfo;
import com.goods.cn.util.BaseUtil;
import com.goods.cn.util.LockUtil;
import com.goods.cn.util.WxPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by fly on 17/7/18.
 */
@Service("orderservice")
public class OrderService extends BaseService {

    @Autowired
    FixTimeGoodsManager fixTimeGoodsManager;


    /**
     * 下单
     *
     * @param order
     * @return
     * @throws Exception
     */
    public OrderModel order(OrderInfo order) throws Exception {
        //生成orderid
        String orderid = LockUtil.creatOrderCount();
        if (orderid != null) {
            OrderModel orderModel = new OrderModel();
            order.setcOrderid(orderid);
            orderModel.orderid = orderid;
            String openid = order.getcOpenid();
            int addrid = order.getcAddrid();
            int appid = order.getcAppid();
            //根据id找到地址
            Useraddress useraddress = userAddressDao.findUserAddressByOpenidAndId(openid, appid, addrid);
            if (useraddress != null) {
                useraddress.setcLastUse(TimeUtil.getCurrTimeHm());
                userAddressDao.update(useraddress);
                App app = appDao.findAppById(appid);
                UserInfo userInfo = userDao.checkUserByOpenid(openid, appid);
                if (userInfo == null) {
                    userInfo = BaseUtil.coverOrderToUser(order);
                    userDao.save(userInfo);
                }
                //计算最后的价格
                Vip vip = vipDao.getVipByvid(userInfo.getcVid(), userInfo.getcAppid());
                double price = computPrice(order, userInfo, app, vip);
                orderModel.addsore = computeSendSore(String.valueOf(price), vip);
                if (price >= 0) {
                    order.setcTotalPrice(String.valueOf(price));
                    if (price == 0) {
                        //免费
                        order.setcStatus((byte) 1);
                        //更新卡卷
                        updateCardSFree(order);
                        orderModel.isfree = 1;
                        //更新限时减免数据库
                        //更新抢购信息
                        List<Fixtimegoodslog> logs = fixTimeGoodsLogDao.findFixTimeLogByOrderId(orderid, appid);
                        if (logs != null && logs.size() > 0) {
                            for (Fixtimegoodslog log : logs) {
                                if (log.getcStutas() == (byte) 0) {
                                    //减去数量
                                    fixTimeGoodsManager.subCoffeeCont(log.getcFixtime(), log.getcGoodsid(), log.getcCount(), appid);
                                    //更新信息
                                    log.setcStutas((byte) 1);
                                    log.setcTime(TimeUtil.getCurrTimeHm());
                                    System.out.println("免费的更新限时减免数据");
                                    fixTimeGoodsLogDao.update(log);
                                }
                            }
                        }
                        //发送支付成功的消息
                        PayMessage payMessage = new PayMessage(order, userInfo);
                        MessageManager.getInstanceMessage().putMessage(payMessage);

                    } else {
                        //如果支付方式是微信支付去微信支付
                        WeiXinPayRequestInfo info = WxPayUtil.creatWeiXinPayInfo(order, app);
                        WeiXinOrderNetAns ans = (WeiXinOrderNetAns) WxPayUtil.startSendWeiXin(info);
                        if (!ans.isError) {
                            String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
                            String peid = ans.prepay_id;
                            System.out.println("orderModel.preid==" + peid);
                            order.setWeixinOrderid(peid);
                            orderModel.preid = "prepay_id=" + peid;
                            String noc = ans.nonce_str;
                            orderModel.nonceStr = noc;
                            orderModel.appId = info.appid;
                            orderModel.timeStamp = timeStamp;
                            String sign = WxPayUtil.computeSign(info.appid, timeStamp, noc, orderModel.preid, app.getcPaykey());
                            orderModel.paySign = sign;
                        }
                    }
                    //订单入库
                    orderDao.save(order);
                    return orderModel;
                }
            }
        }
        return null;
    }


    /**
     * 计算附加商品
     *
     * @param attchList
     * @return
     * @throws Exception
     */
    private double computeAttchGoods(String attchList) throws Exception {
        Double totalPrice = new Double("0.00");
        if (attchList != null) {
            JSONArray attchArray = JSONArray.parseArray(attchList);
            //总价格
            for (int i = 0; i < attchArray.size(); i++) {
                JSONObject attchObjcet = attchArray.getJSONObject(i);
                String price = attchObjcet.getString("price");
                Integer count = attchObjcet.getInteger("count");
                Double temprice = Double.valueOf(price) * count;
                totalPrice += temprice;
            }
        }
        return totalPrice;
    }


    private double computeGoodsInfo(String goodslist, App app, UserInfo userInfo, OrderInfo orderInfo, List<String> priceList) throws Exception {
        Double totalPrice = new Double("0.00");
        JSONArray coffeeArray = JSONArray.parseArray(goodslist);
        //总价格
        for (int i = 0; i < coffeeArray.size(); i++) {
            JSONObject coffeeObjcet = coffeeArray.getJSONObject(i);
            String price = coffeeObjcet.getString("price");
            Integer count = coffeeObjcet.getInteger("count");
            String coffeeid = coffeeObjcet.getString("goodsid");
            if (coffeeObjcet.containsKey("fixtimekey")) {
                //这个产品包含限时减免
                String timekey = coffeeObjcet.getString("fixtimekey");
                if (timekey != null && !timekey.equals("")) {
                    //判断
                    FixTimeGoods fixTimeCoffee = fixTimeGoodsManager.findFixCoffeeByTime(new Date(), coffeeid, count, app.getcId());
                    if (fixTimeCoffee == null) {
                        //订单无效
                        return -1;
                    } else {
                        //判断个人限额
                        int number = 0;
                        List<Fixtimegoodslog> logs = fixTimeGoodsLogDao.findAllFixTimeLogByTimeAndOpenId(userInfo.getcOpenid(), timekey, coffeeid, app.getcId());
                        if (logs != null && logs.size() > 0) {

                            for (Fixtimegoodslog fixtimegoodslog : logs) {
                                int c = fixtimegoodslog.getcCount();
                                number += c;
                            }
                        }
                        if (number + count <= fixTimeCoffee.getOnlyCupNumber()) {
                            //限时抢购入库
                            Fixtimegoodslog fixtimegoodslog = new Fixtimegoodslog();
                            fixtimegoodslog.setcGoodsid(coffeeid);
                            fixtimegoodslog.setcCount(count);
                            fixtimegoodslog.setcFixtime(timekey);
                            fixtimegoodslog.setcOrderid(orderInfo.getcOrderid());
                            fixtimegoodslog.setcOpenid(userInfo.getcOpenid());
                            fixtimegoodslog.setcAppid(orderInfo.getcAppid());
                            System.out.println("coffeeid=" + coffeeid + "  count=" + count + "  timekey=" + timekey
                                    + "  orderid=" + orderInfo.getcOrderid() + "  openid=" + userInfo.getcOpenid());
                            fixTimeGoodsLogDao.save(fixtimegoodslog);
                        } else {
                            return -1;
                        }
                    }
                }
            } else {
                //如果没有则看整体库存
                GoodsInfo goodsInfo = goodsInfoDao.findGoodsInfoByidLock(coffeeid);
                int leftNumber = goodsInfo.getCleftNumber();
                //如果库存不够
                if (leftNumber < count) {
                    return -1;
                }
            }
            for (int k = 0; k < count; k++) {
                priceList.add(price);
            }
            Double temprice = Double.valueOf(price) * count;
            totalPrice += temprice;
        }
        return totalPrice;
    }


    /**
     * 计算价格
     *
     * @param orderInfo
     * @param userInfo
     * @return
     */
    private double computPrice(OrderInfo orderInfo, UserInfo userInfo, App app, Vip vip) throws Exception {
        List<String> priceList = new ArrayList<String>();
        Double totalPrice = new Double("0.00");
        String goodslist = orderInfo.getcCoffeelist();
        String attchList = orderInfo.getcAttch();
        //计算商品
        double goodsprice = computeGoodsInfo(goodslist, app, userInfo, orderInfo, priceList);
        if (goodsprice < 0) {
            return -1;
        }
        totalPrice += goodsprice;
        //计算附加商品
        double attchGoodsPrice = computeAttchGoods(attchList);
        totalPrice += attchGoodsPrice;
        //排序
        Collections.sort(priceList, new Comparator<String>() {

            public int compare(String o1, String o2) {
                // TODO Auto-generated method stub
                Double d1 = Double.valueOf(o1);
                Double d2 = Double.valueOf(o2);
                return d2.compareTo(d1);
            }

        });
        orderInfo.priceList = priceList;
        //会员
        if (vip != null) {
            String dis = vip.getcDiscount();
            totalPrice *= Double.valueOf(dis);
        }
        //计算运费
        double fare = computeFare(orderInfo);
        orderInfo.farePrice=String.valueOf(fare);
        totalPrice += fare;
        if (totalPrice < 0) {
            totalPrice = 0.00;
        } else {
            totalPrice = MoneyUtil.coverTwoMoney(totalPrice);
        }
        orderInfo.setcTotalPrice(String.valueOf(totalPrice));
        //使用卡卷
        double carddprice = useCards(orderInfo, userInfo);
        if (carddprice < 0) {
            //使用卡卷出错
            return -1;
        }
        totalPrice -= carddprice;
        return totalPrice;
    }

    /**
     * 计算运费
     *
     * @param orderInfo
     * @return
     */
    public double computeFare(OrderInfo orderInfo) throws Exception {
        //计算省份的运费
        int addrid = orderInfo.getcAddrid();
        String openid = orderInfo.getcOpenid();
        int appid = orderInfo.getcAppid();
        //获得用户的收货地址
        Useraddress useraddress = userAddressDao.findUserAddressByOpenidAndId(openid, appid, addrid);
        //拿到收货地址的省份
        String code = useraddress.getcProvince();
        double free = 0;
        //计算货品的运费
        String goodslist = orderInfo.getcCoffeelist();
        HashMap<Integer, Integer> fareMap = new HashMap<Integer, Integer>();
        JSONArray goodsArray = JSONArray.parseArray(goodslist);
        for (int i = 0; i < goodsArray.size(); i++) {
            JSONObject goodsObjcet = goodsArray.getJSONObject(i);
            String goodsid = goodsObjcet.getString("goodsid");
            int count = goodsObjcet.getInteger("count");
            GoodsInfo goodsInfo = goodsInfoDao.findGoodsInfoByid(goodsid);
            int fareId = goodsInfo.getcFareTemplateId();
            Integer number = fareMap.get(fareId);
            if (number == null) {
                fareMap.put(fareId, count);
            } else {
                fareMap.put(fareId, count + number);
            }
        }
        //计算运费模版
        Iterator iterator = fareMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            int fareId = (Integer) entry.getKey();
            int countnumber = (Integer) entry.getValue();
            Faredes faredes = faredesDao.findFaredesByProCode(code, fareId);
            Faretemplate faretemplate = fareTemplateDao.findFareTempLateById(fareId, appid);
            if (faretemplate != null) {
                String onePrice = faretemplate.getcDefaultprice();
                String addPrice = faretemplate.getcDefaultaddprice();
                if (faredes != null) {
                    //这个省的额外加的运费
                    onePrice = faredes.getcOneprice();
                    addPrice = faredes.getcAddoneprice();
                }
                //计算运费
                double price = Double.valueOf(onePrice) + Double.valueOf(addPrice) * (countnumber - 1);
                free += price;
            }
        }
        return MoneyUtil.coverTwoMoney(free);
    }


    /**
     * 使用卡卷
     * 下单的时候计算卡卷省掉的金额
     *
     * @param orderInfo
     * @param userInfo
     */
    private double useCards(OrderInfo orderInfo, UserInfo userInfo) {
        List<Usercard> disCards = orderInfo.disCards;
        double disprice = 0;
        if (disCards != null) {
            for (int i = 0; i < disCards.size(); i++) {
                Usercard userCard = disCards.get(i);
                int id = userCard.getcIdentification();
                IDisCard iCard = CardFactory.getInstansCardFactory().creatCard(id);
                double tempprice = iCard.getDisEffectValue(userCard, orderInfo);
                if (tempprice < 0) {
                    return -1;
                }
                disprice += tempprice;
            }
        }
        return disprice;
    }

    /**
     * 免费的时候调用 可以直接从order里面拿到卡卷信息
     *
     * @param orderInfo
     * @throws Exception
     */
    private void updateCardSFree(OrderInfo orderInfo) throws Exception {
        List<Usercard> disCards = orderInfo.disCards;
        for (int h = 0; h < disCards.size(); h++) {
            // 更新卡卷
            Usercard userCard = disCards.get(h);
            Usercard realCard = userCardDao.findCardByCardid(userCard.getcCardid(), orderInfo.getcAppid());
            realCard.setcUsetime(TimeUtil.getCurrTimeHm());
            realCard.setcOrderid(orderInfo.getcOrderid());
            realCard.setcIsuse(1);
            System.out.println("carid=" + userCard.getcCardid() + "  isuse=" + userCard.getcIsuse() + "  orderid=" + userCard.getcOrderid() + "  value=" + userCard.getcCardvalue());
            userCardDao.update(realCard);
        }
    }

    /**
     * 处理微信支付回调的callback
     *
     * @param orderid
     * @param openid
     * @throws Exception
     */
    public void payCallBackService(String orderid, String openid, String attch, String transaction_id) throws Exception {
        List<OrderInfo> orders = orderDao.findOrderByOrderId(orderid, openid, null);
        OrderInfo orderInfo = orders.get(0);
        int appid = orderInfo.getcAppid();
        UserInfo userInfo = userDao.checkUserByOpenid(openid, appid);
        if (orderInfo != null) {
            int status = orderInfo.getcStatus();
            System.out.println(orderInfo.getWeixinOrderid() + "     tid=" + transaction_id);
            if (status == 0 && orderInfo.getWeixinOrderid() != null) {
                System.out.println("微信回调通知成功发货");
                orderInfo.setcStatus((byte) 1);
                orderInfo.setcMtime(TimeUtil.getCurrTimeHm());
                //更新订单消费信息
                orderDao.update(orderInfo);
                //更新用户消费总额度
                String cummoney = orderInfo.getcTotalPrice();
                String totalpricze = userInfo.getcMonetary();
                //计算赠送咖啡豆
                Vip vip = vipDao.getVipByvid(userInfo.getcVid(), appid);
                int sore = computeSendSore(cummoney, vip);
                System.out.println("sore=" + sore + "  userInfo.getcSore()+" + userInfo.getcSore());
                sore += userInfo.getcSore();
                userInfo.setcSore(sore);
                //更新总金额
                double totalmoney = Double.valueOf(cummoney) + Double.valueOf(totalpricze);
                double f1 = MoneyUtil.coverTwoMoney(totalmoney);
                userInfo.setcMonetary(String.valueOf(f1));
                //更新用户喜好信息 消费习惯
                String fav = creatUserFav(openid, 3, appid);
                if (fav.trim().length() > 0) {
                    userInfo.setcFav(fav);
                }
                userDao.update(userInfo);
                //更新打折卡卷内容
                System.out.println("acttcn=" + attch);
                if (attch != null && attch.trim().length() > 0 && !attch.equals("-1")) {
                    updateCardInfo(attch, orderid, appid);
                }
                //更新抢购信息
                List<Fixtimegoodslog> logs = fixTimeGoodsLogDao.findFixTimeLogByOrderId(orderid, appid);
                if (logs != null && logs.size() > 0) {
                    for (Fixtimegoodslog log : logs) {
                        if (log.getcStutas() == (byte) 0) {
                            //减去数量
                            fixTimeGoodsManager.subCoffeeCont(log.getcFixtime(), log.getcGoodsid(), log.getcCount(), appid);
                            //更新信息
                            log.setcStutas((byte) 1);
                            log.setcTime(TimeUtil.getCurrTimeHm());
                            fixTimeGoodsLogDao.update(log);
                        }
                    }
                }
                //更新库存
                String goodslist = orderInfo.getcCoffeelist();
                System.out.println("goodslist=" + goodslist);
                JSONArray goodsArray = JSONArray.parseArray(goodslist);
                for (int i = 0; i < goodsArray.size(); i++) {
                    JSONObject goodsObject = goodsArray.getJSONObject(i);
                    String goodid = goodsObject.getString("goodsid");
                    Integer count = goodsObject.getInteger("count");
                    GoodsInfo goodsInfo = goodsInfoDao.findGoodsInfoByid(goodid);
                    int left = goodsInfo.getCleftNumber() - count;
                    goodsInfo.setCleftNumber(left);
                    goodsInfoDao.update(goodsInfo);
                }
                //发送支付成功的消息
                PayMessage payMessage = new PayMessage(orderInfo, userInfo);
                MessageManager.getInstanceMessage().putMessage(payMessage);
            }
        }
    }


    /**
     * 不免费的时候 调用 通过微信的callback 回传卡卷信息
     *
     * @param attchCards
     * @param out_trade_no
     * @throws Exception
     */
    private void updateCardInfo(String attchCards, String out_trade_no, int appid) throws Exception {
        // 更新卡牌状态
        String[] cards = attchCards.split(",");
        if (cards != null) {
            for (int i = 0; i < cards.length; i++) {
                String cardid = cards[i];
                // 更新卡卷
                Usercard userCard = userCardDao.findCardByCardid(cardid, appid);
                userCard.setcIsuse(1);
                userCard.setcUsetime(TimeUtil.getCurrTimeHm());
                userCard.setcOrderid(out_trade_no);
                userCardDao.update(userCard);
            }

        }
    }

    /**
     * 生成用户习惯的fav
     */
    public String creatUserFav(String openid, int size, int appid) throws Exception {
        StringBuilder favbuff = new StringBuilder();
        List<UserFav> userFavs = userBuyLogDao.findFavByOpenid(openid, size, appid);
        System.out.println("fav size=" + userFavs.size());
        if (userFavs != null) {
            for (int i = 0; i < userFavs.size(); i++) {
                UserFav userFav = userFavs.get(i);
                String coffeeid = userFav.getC_goodsid();
                System.out.println("coffeeid=" + coffeeid+" count="+userFav.getTotal());
                if (coffeeid != null && coffeeid.trim().length() > 6 && !coffeeid.equals("")) {
                    if (favbuff.length() == 0) {
                        favbuff.append(coffeeid);
                    } else {
                        favbuff.append("," + coffeeid);
                    }
                }
            }
        }
        System.out.println(" fav ==" + favbuff.toString());
        return favbuff.toString();
    }

    /**
     * 分页查找订单
     *
     * @param openid
     * @param pageIndex
     * @param pagesize
     * @return
     * @throws Exception
     */
    public PageQueryResult<DestDTO> getAllOrderInfoByOpenid(String openid, int pageIndex, int pagesize, int appid) throws Exception {
        return orderDao.getOrderInfoPage(openid, pageIndex, pagesize, appid);
    }

    public List<OrderInfo> findUserfulOrderBytime(String openid, Date btime, Date etime, int appid) throws Exception {
        return orderDao.findUserOrderByTime(openid, btime, etime, appid);
    }

    /**
     * 计算赠送的虚拟货币
     *
     * @param cummoney
     * @param vip
     * @return
     */
    private int computeSendSore(String cummoney, Vip vip) {
        try {
            if (vip != null) {
                String remark = vip.getcRemark();
                if (remark != null) {
                    VipTitle vipTitle = JSONObject.parseObject(remark, VipTitle.class);
                    String sendRatio = vipTitle.getSendRatio();
                    if (sendRatio != null) {
                        double cPrice = Double.valueOf(cummoney);
                        double sRatio = Double.valueOf(sendRatio);
                        return (int) (cPrice * sRatio);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询状态为配送中且已经到期的订单
     *
     * @param pageindex
     * @param pagesize
     * @param daynumber
     * @param appid
     * @return
     * @throws Exception
     */
    public PageQueryResult<DestDTO> findOrderByTimeAndStutas(int pageindex, int pagesize, int daynumber, int appid) throws Exception {
        return orderDao.findOrderByTimeAndStutas(pageindex, pagesize, daynumber, appid);
    }

    /**
     * 更新订单
     *
     * @param orderInfo
     * @throws Exception
     */
    public void update(OrderInfo orderInfo) throws Exception {
        orderDao.update(orderInfo);
    }

    /**
     * 获取推荐的推荐商品
     *
     * @param orderInfo
     * @return
     */
    public HashMap<Attachgoods, Integer> findAttchGoods(OrderInfo orderInfo) throws Exception {
        String goodslist = orderInfo.getcCoffeelist();
        JSONArray goodsArray = JSONArray.parseArray(goodslist);
        int appid = orderInfo.getcAppid();
        HashMap<Attachgoods, Integer> attachgoods = new HashMap<Attachgoods, Integer>();
        for (int i = 0; i < goodsArray.size(); i++) {
            JSONObject goodsObjcet = goodsArray.getJSONObject(i);
            String goodsid = goodsObjcet.getString("goodsid");
            int count = goodsObjcet.getInteger("count");
            List<Attachgoods> tempList = attachGoodsDao.findAttchGoodsByGoodsId(appid, goodsid);
            if (tempList != null && tempList.size() > 0) {
                for (Attachgoods attch : tempList) {
                    String stagr = attch.getcStrategy();
                    AttachStrategy attachStrategy = JSONObject.parseObject(stagr, AttachStrategy.class);
                    if (attachStrategy.getRatio() != null) {
                        //算出比例
                        int number = count / Integer.valueOf(attachStrategy.getRatio());
                        if (number == 0) {
                            number = 1;
                        }
                        if (attachgoods.get(attch) != null) {
                            int oldNumber = attachgoods.get(attch);
                            oldNumber += number;
                            attachgoods.put(attch, oldNumber);
                        } else {
                            attachgoods.put(attch, number);
                        }

                    }
                }
            }
        }
        return attachgoods;
    }

}
