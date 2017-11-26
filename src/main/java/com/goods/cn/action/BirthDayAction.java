package com.goods.cn.action;

import com.goods.cn.config.BeanDaoImplConfig;
import com.goods.cn.po.Action;
import com.goods.cn.po.UserInfo;
import com.goods.cn.po.Usercard;
import com.goods.cn.service.UserCardService;
import com.goods.cn.servlet.SpringConfigTool;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fly on 17/3/28.
 */
public class BirthDayAction extends BaseAction<String> {

    UserCardService userCardService;
    public BirthDayAction(int balance, UserInfo userInfo, String o) {

        super(balance, userInfo, o);
        userCardService = (UserCardService) SpringConfigTool.getBean(BeanDaoImplConfig.USERCARDSERVICE);
    }

    public BirthDayAction() {
    }


    public boolean filterAction(Action action, Object O) {
        try {
            //先判断今年是否派过一次
            List<Usercard> usercards=userCardService.findBirthDayUserCardByYear(userInfo.getcOpenid(),userInfo.getcAppid());
            if(usercards==null||usercards.size()==0) {
                //yyyy-MM-dd
                SimpleDateFormat simpleFormatter = new SimpleDateFormat("MM-dd");
                String day = simpleFormatter.format(new Date());
                String birthDay = userInfo.getcBirthday();
                if (birthDay != null) {
                    int index = birthDay.indexOf("-");
                    String birth = birthDay.substring(index + 1, index + 6);
                    return day.equals(birth);
                }
            }
        } catch (Exception e) {

        }
        return false;
    }


    public static void main(String[] args) {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("MM-dd");
        String day = simpleFormatter.format(new Date());
        System.out.println(day);
    }

}
