package com.goods.cn.controller;
import com.fly.cn.lottery.Prize;
import com.fly.cn.util.ErrorMsg;
import com.goods.cn.po.Lottery;
import com.goods.cn.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class LotteryController {

    @Autowired
    LotteryService lotteryService;

    @RequestMapping(value = "/lottery", method = RequestMethod.GET, params = {"openid","appid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap lottery(String openid,int appid){
        SupplyMap supplyMap=new SupplyMap();
        try{
            Prize prize= lotteryService.lottery(openid,appid);
            if(prize==null){
                supplyMap.put("lottery","长得这么好下次一定中奖");
            }else{
                System.out.println("openid="+openid+"  中奖了");
                supplyMap.put("lottery",prize);
            }

        }catch (Exception e){
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR,ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }

    @RequestMapping(value = "/findlottery", method = RequestMethod.GET, params = {"openid","appid"})
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public SupplyMap findlottery(String openid,int appid){
        SupplyMap supplyMap=new SupplyMap();
        try{
            Lottery lottery=lotteryService.findCurrUserfulLottery(openid,appid);
            if(lottery!=null){
                long bc=lottery.bc;
                if(bc!=1l) {
                    //可以抢购
                    supplyMap.put("lottery", lottery);
                }else{
                    //已经抢购过一次了
                    supplyMap.setEorrInfo(ErrorMsg.STATUS_LOTTERYNOPERMIT_ERROR,ErrorMsg.MSG_LOTTERYNOPERMIT_ERROR);
                }
            }else{
                //抢购时间已经过了
                supplyMap.setEorrInfo(ErrorMsg.STATUS_LOTTERYTIMEOVER_ERROR,ErrorMsg.MSG_LOTTERYTIMEOVER_ERROR);
            }
        }catch (Exception e){
            supplyMap.setEorrInfo(ErrorMsg.STATUS_DATABASE_ERROR,ErrorMsg.MSG_DATABASE_ERROR);
        }
        return supplyMap;
    }

}
