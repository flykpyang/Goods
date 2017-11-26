package com.goods.cn.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.fly.cn.util.TimeUtil;
import com.goods.cn.action.Gift;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.po.*;
import com.goods.cn.util.BaseUtil;
import com.goods.cn.util.GiftCompare;
import com.goods.cn.util.GoodsUtil;
import com.goods.cn.util.WaterDateCompare;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("taskproservice")
public class TaskProService extends BaseService {

    /**
     * @param openid
     * @return
     * @throws Exception
     */
    public WaterTaskModel findTaskProForUser(String openid,int appid) throws Exception {
        //System.out.println("findTaskProForUser");
        UserInfo userInfo = userDao.checkUserByOpenid(openid,appid);
        if (userInfo == null) {
            userInfo = BaseUtil.creatNewUserInfo(openid,appid);
            userDao.save(userInfo);
        }
        WaterTaskModel waterTaskModel = null;
        TreeMap<String, WaterAction> treeMap = null;
        Taskpro taskpro = taskProDao.findTaskProByOpenidAndStutas(openid, 0,appid);
        Date currDay = TimeUtil.getCurrDay(0);
        Task task = taskDao.findUserTask(userInfo,appid);
        if (taskpro == null) {
            //没有当前任务
            if (task != null) {
                treeMap = createNewPro(task, openid);
            }
        } else {
            //找到没有完成的任务 判断任务是否有效
            //判断任务是否有效
            treeMap = judgeTaskIsUseful(taskpro, openid, String.valueOf(currDay.getTime()));
            if (treeMap == null) {
                //生成新任务
                if (task != null) {
                    treeMap = createNewPro(task, openid);
                }
            }
        }
        if (treeMap != null) {
            waterTaskModel = new WaterTaskModel();
            waterTaskModel.waterActionTreeMap = treeMap;
            waterTaskModel.currWaterAction = treeMap.get(String.valueOf(currDay.getTime()));
        }
        return waterTaskModel;
    }

    private TreeMap<String, WaterAction> createNewPro(Task task, String openid) throws Exception {
        int daynumber = task.getcTaskday();
        String giftlist = task.getcGiftlist();
        //生成pro
        Taskpro taskPro = new Taskpro();
        taskPro.setcTaskid(task.getcId());
        //生成计划
        TreeMap<String, WaterAction> map = creatTreeMap(TimeUtil.getCurrDay(0).getTime(), giftlist);
        String pro = GoodsUtil.coverMapToJson(map);
        taskPro.setcPro(pro);
        taskPro.setcAppid(task.getcAppid());
        taskPro.setcStatus((byte) 0);
        taskPro.setcOpenid(openid);
        taskProDao.save(taskPro);
        return map;

    }

    /**
     * @param begindate
     * @param giftlist
     * @return
     */
    private  TreeMap<String, WaterAction> creatTreeMap(long begindate, String giftlist) {
        TreeMap<String, WaterAction> map = new TreeMap<String, WaterAction>(new WaterDateCompare());
        TreeMap<Integer, Gift> giftmap = coverGiftToMap(giftlist);
        Iterator iterable = giftmap.entrySet().iterator();
        while (iterable.hasNext()) {
            Map.Entry entry = (Map.Entry) iterable.next();
            Integer day = (Integer) entry.getKey();
            Gift gift = (Gift) entry.getValue();
            //计算下一个时间
            long temdate = begindate + 3600 * 24 * 1000 * day;
            WaterAction waterAction = new WaterAction();
            waterAction.iswater = false;
            waterAction.gift = gift;
            if(day==0){
                waterAction.currday=1;
            }
            map.put(String.valueOf(temdate), waterAction);
        }
        return map;
    }


    /**
     * 把 gift 转化为json
     *
     * @param giftlist
     * @return
     */
    public  TreeMap<Integer, Gift> coverGiftToMap(String giftlist) {
        TreeMap<Integer, Gift> map = new TreeMap<Integer, Gift>(new GiftCompare());
        try {
            JSONArray jsonArray = JSONArray.parseArray(giftlist);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject giftobject = jsonArray.getJSONObject(i);
                Gift gift = new Gift();
                if(giftobject.containsKey("card")) {
                    //卡卷
                    JSONArray cards = giftobject.getJSONArray("card");
                    if (cards.size() > 0) {
                        List<Discard> disCards = new ArrayList<Discard>();
                        for (int j = 0; j < cards.size(); j++) {
                            JSONObject cardobject = cards.getJSONObject(j);
                            int id=cardobject.getInteger("id");
                            Discard disCard=disCardDao.findDisCardById(id);
                            if(disCard!=null) {
                                disCard.setcId(id);
                                disCards.add(disCard);
                            }
                        }
                        gift.userCards = disCards;
                    }
                }
                if(giftobject.containsKey("sore")) {
                    //咖啡豆
                    int sore = giftobject.getInteger("sore");
                    gift.coffeesore = sore;
                }
                //discount 打折
                if (giftobject.containsKey("discount")) {
                    String discount = giftobject.getString("discount");
                    gift.discount = discount;
                }
                map.put(i, gift);
            }
            return map;
        } catch (Exception e) {
            return null;
        }
    }



    private TreeMap<String, WaterAction> judgeTaskIsUseful(Taskpro taskPro, String openid, String today) throws Exception {

        if (taskPro.getcOpenid().equals(openid)) {
            String pro = taskPro.getcPro();
            //转化成map
            TreeMap<String, WaterAction> treeMap = coverJosnToMap(pro);
            //减去一天
            long beforeTime = Long.valueOf(today) - 3600 * 1000 * 24;
            if (treeMap.get(today) != null) {
                if (treeMap.get(String.valueOf(beforeTime)) == null || treeMap.get(String.valueOf(beforeTime)).iswater) {
                    //当前日期为第一天或者前一天已经完成任务 即为有效时期
                    return treeMap;
                }
            }
            taskPro.setcStatus((byte) 1);
            taskProDao.update(taskPro);
        }
        return null;
    }


    /**
     * 浇花service
     *
     * @param openid
     * @throws Exception
     */
    public Gift water(String openid,int appid) throws Exception {
        UserInfo userInfo = userDao.checkUserByOpenid(openid,appid);
        if (userInfo != null) {
            Task task = taskDao.findUserTask(userInfo,appid);
            if (task != null) {
                int taskid = task.getcId();
                //找到没有完成的任务
                Taskpro taskpro = taskProDao.findTaskProByOpenidAndStutas(openid, 0,appid);
                if (taskpro != null) {
                    String project = taskpro.getcPro();
                    Gift gift = findTodayGift(project, taskpro);
                    if (gift != null) {
                        //添加浇水记录 发放礼物
                        Water water = new Water();
                        water.setcOpenid(openid);
                        water.setcTaskid(taskid);
                        water.setcAppid(appid);
                        JSONObject waterGift = new JSONObject();
                        int sore = gift.coffeesore;
                        waterGift.put("sore", sore);
                        List<Discard> disCards = gift.userCards;
                        if(disCards!=null) {
                            JSONArray cardarray = new JSONArray();
                            for (Discard disCard : disCards) {
                                Usercard userCard = GoodsUtil.coverUserCardByDisCard(disCard, userInfo,
                                        BaseConfig.WATERFROM,null);
                                JSONObject diso = new JSONObject();
                                diso.put("iden", disCard.getcId());
                                cardarray.add(diso);
                                userCardDao.save(userCard);
                                waterGift.put("card", cardarray);
                            }
                            waterGift.put("card", cardarray);
                        }
                        water.setcGift(waterGift.toString());
                        //更新sore
                        int usore = userInfo.getcSore();
                        usore += sore;
                        userInfo.setcSore(usore);
                        userDao.update(userInfo);
                        waterDao.save(water);
                        return gift;
                    }

                }
            }
        }
        return null;
    }


    /**
     * 生成返回的礼物
     *
     * @param project
     * @return
     */
    private Gift findTodayGift(String project, Taskpro taskpro) throws Exception {

        JSONArray jsonArray = JSONArray.parseArray(project);
        int len = jsonArray.size();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String day = object.getString("date");
                //Date date=simpleDateFormat.parse(day);
                if (TimeUtil.isNow(Long.valueOf(day))) {
                    boolean iswater = object.getBoolean("iswater");
                    //没有浇水
                    if (!iswater) {
                        //浇水记录更改
                        object.put("iswater", true);
                        taskpro.setcPro(jsonArray.toString());
                        taskProDao.update(taskpro);
                        //浇水拿礼物
                        Gift gift = new Gift();
                        if (object.containsKey("sore")) {
                            //加虚拟货币
                            int sore = object.getInteger("sore");
                            gift.coffeesore = sore;
                        }
                        if (object.containsKey("card")) {
                            List<Discard> disCards = new ArrayList<Discard>();
                            gift.userCards = disCards;
                            //添加卡卷
                            JSONArray cardArray = object.getJSONArray("card");
                            for (int j = 0; j < cardArray.size(); j++) {
                                JSONObject cardobject = cardArray.getJSONObject(j);
                                int iden = cardobject.getInteger("iden");
                                Discard disCard = disCardDao.findDisCardById(iden);
                                if(disCard!=null) {
                                    gift.userCards.add(disCard);
                                }
                            }
                        }
                        return gift;
                    }
                    break;
                }
            }
        }
        return null;
    }


    /**
     * 把json 转化为treemap
     *
     * @param json
     * @return
     */
    private  TreeMap<String, WaterAction> coverJosnToMap(String json) {
        try {
            JSONArray array = JSONArray.parseArray(json);
            TreeMap<String, WaterAction> treeMap = new TreeMap<String, WaterAction>(new WaterDateCompare());
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = array.getJSONObject(i);
                String data = object.getString("date");
                WaterAction waterAction = new WaterAction();
                boolean water = object.getBoolean("iswater");
                waterAction.iswater = water;
                Gift gift = coverGiftByJson(object);
                waterAction.gift = gift;
                if(TimeUtil.isNow(Long.valueOf(data))){
                    waterAction.currday=1;
                }
                treeMap.put(data, waterAction);
            }
            return treeMap;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把json转化为gift
     *
     * @param giftobject
     * @return
     */
    private  Gift coverGiftByJson(JSONObject giftobject) {
        try {
            Gift gift = new Gift();
            if (giftobject.containsKey("card")) {
                //卡卷
                JSONArray cards = giftobject.getJSONArray("card");
                List<Discard> disCards = new ArrayList<Discard>();
                for (int i = 0; i < cards.size(); i++) {
                    JSONObject cardobject = cards.getJSONObject(i);
                    int id=cardobject.getInteger("iden");
                    Discard disCard = disCardDao.findDisCardById(id);
                    disCards.add(disCard);
                }
                gift.userCards = disCards;
            }
            if (giftobject.containsKey("sore")) {
                //咖啡豆
                int sore = giftobject.getInteger("sore");
                gift.coffeesore = sore;
            }
            if (giftobject.containsKey("discount")) {
                //discount 打折
                String discount = giftobject.getString("discount");
                gift.discount = discount;
            }
            return gift;
        } catch (Exception e) {
            return null;
        }
    }
}
