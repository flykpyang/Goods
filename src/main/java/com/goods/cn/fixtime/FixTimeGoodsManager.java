package com.goods.cn.fixtime;

import com.alibaba.fastjson.JSONArray;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.po.Action;
import com.goods.cn.po.App;
import com.goods.cn.po.FixTimeGoods;
import com.goods.cn.service.ActionService;
import com.goods.cn.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.text.SimpleDateFormat;
import java.util.*;

@Component("fixtimemanager")
public class FixTimeGoodsManager {

    @Autowired
    ActionService actionService;

    @Autowired
    AppService    appService;

    private volatile HashMap<Integer, HashMap<String, List<FixTimeGoods>>> map;

    @PostConstruct
    public void init() throws Exception {
        //初始化
        map = new HashMap<Integer, HashMap<String, List<FixTimeGoods>>>();
        initAllFixMap(null);
    }

    @PreDestroy
    public void destory(){
        map.clear();
    }

    public void initAllFixMap(List<App> apps) throws Exception{
        if(apps==null) {
            apps = appService.findAllApp();
        }
        if(apps!=null&&apps.size()>0){
            map.clear();
            for(App app:apps){
                initMap(app.getcId());
            }
        }
    }

    public  void initMap(int appid) throws Exception {
        HashMap<String, List<FixTimeGoods>> appMap = map.get(appid);
        if (appMap == null) {
            appMap = new HashMap<String, List<FixTimeGoods>>();
            map.put(appid, appMap);
        }
        appMap.clear();
        List<Action> actions = actionService.findUseAction(BaseConfig.FIXTIMECOFFEEACTION, appid);
        if (actions != null && actions.size() > 0) {
            for (Action action : actions) {
                String condition = action.getcCondition();
                //System.out.println("condition="+condition);
                List<FixTimeGoods> fixTimeCoffees = JSONArray.parseArray(condition, FixTimeGoods.class);
                for (FixTimeGoods fixTimeGoods : fixTimeCoffees) {
                    String bt = fixTimeGoods.getBeginTime();
                    String et = fixTimeGoods.getEndTime();
                    String timekey = bt + "-" + et;
                    if (!appMap.containsKey(timekey)) {
                        List<FixTimeGoods> list = new ArrayList<FixTimeGoods>();
                        list.add(fixTimeGoods);
                        appMap.put(timekey, list);
                    } else {
                        List<FixTimeGoods> list = appMap.get(timekey);
                        list.add(fixTimeGoods);
                    }
                }
            }
        }

    }


    public synchronized FixTimeGoods findFixCoffeeByTime(Date date, String coffeeId, int number, int appid) {
        HashMap<String, List<FixTimeGoods>> appMap = map.get(appid);
        if (appMap != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            final String time = sdf.format(date);
            Iterator iterator = appMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                List<FixTimeGoods> list = (List<FixTimeGoods>) entry.getValue();
                String[] strings = key.split("-");
                if (strings != null && strings.length == 2) {
                    long bc = TimeUtil.compareTimeByHM(time, strings[0]);
                    long ec = TimeUtil.compareTimeByHM(time, strings[1]);
                    if (bc > 0 && ec < 0) {
                        for (FixTimeGoods fixTimeGoods : list) {
                            String coffeeid = fixTimeGoods.getGoodsId();
                            int count = fixTimeGoods.getCount();
                            if (coffeeId.equals(coffeeid)) {
                                if (count > number) {
                                    return fixTimeGoods;
                                }
                                return null;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public synchronized void subCoffeeCont(String key, String coffeeid, int number, int appid) {
        HashMap<String, List<FixTimeGoods>> appMap = map.get(appid);
        if (appMap != null) {
            //System.out.println("timekey=" + key + "   coffeeid=" + coffeeid + "  number=" + number);
            List<FixTimeGoods> list = appMap.get(key);
            if (list != null) {
                for (FixTimeGoods fixTimeGoods : list) {
                    String coffeeId = fixTimeGoods.getGoodsId();
                    //System.out.println("coffeeid=" + coffeeId);
                    if (coffeeId.equals(coffeeid)) {
                        int count = fixTimeGoods.getCount();
                        fixTimeGoods.setCount(count - number);
                    }
                }
            }
        }
    }


    public synchronized int getCoffeeNumberCount(String timekey, String coffeeid, int appid) {
        HashMap<String, List<FixTimeGoods>> appMap = map.get(appid);
        if (appMap != null) {
            List<FixTimeGoods> coffees = appMap.get(timekey);
            if (coffees != null && coffees.size() > 0) {
                for (FixTimeGoods fixTimeGoods : coffees) {
                    String cfid = fixTimeGoods.getGoodsId();
                    if (cfid.equals(coffeeid)) {
                        return fixTimeGoods.getCount();
                    }
                }
            }
        }
        return 0;
    }
}
