package com.goods.cn.action;

import com.goods.cn.config.BeanDaoImplConfig;
import com.goods.cn.po.Action;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.UserInfo;
import com.goods.cn.po.Usercard;
import com.goods.cn.service.UserCardService;
import com.goods.cn.servlet.SpringConfigTool;

/**
 * Created by fly on 17/3/28.
 */
public class ComsumeAction extends BaseAction<OrderInfo> {


    UserCardService userCardService;

    public ComsumeAction(int balance, UserInfo userInfo, OrderInfo o) {

        super(balance, userInfo, o);
        userCardService= (UserCardService) SpringConfigTool.getBean(BeanDaoImplConfig.USERCARDSERVICE);

    }


    public boolean filterAction(Action action, Object O) {
        try {
            //System.out.println("ComsumeAction come in");
            OrderInfo orderInfo = (OrderInfo) O;
            String forderid = orderInfo.getcOrderid();
            Usercard usercard = userCardService.findCardByForderid(forderid);
            if (usercard == null) {
                //没有领取过
                String contition = action.getcCondition();
                double target = Double.valueOf(orderInfo.getcTotalPrice());
                long orderdate = orderInfo.getcCtime().getTime();
                double price = Double.valueOf(contition);
                //判断是否领取过奖品
                long baction = action.getcBegintime().getTime();
                long eacton = action.getcEndtime().getTime();
                //System.out.println("orderdate="+orderdate+"  baction="+baction+"  eacton="+eacton);
                if (orderdate >= baction && orderdate <= eacton) {
                    return target >= price;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
