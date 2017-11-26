package com.goods.cn.service;

import com.fly.cn.dao.PageQueryResult;
import com.fly.cn.util.CipherUtil;
import com.fly.cn.util.TimeUtil;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.po.GoodsInfo;
import com.goods.cn.po.OrderInfo;
import com.goods.cn.po.Usercard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
//
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class GoodsInfoServiceTest {

   @Autowired
   GoodsInfoService goodsInfoService;

   @Autowired
   UserCardService  userCardService;

   @Autowired
   OrderService  orderService;


    @Test
    public void save() throws Exception {
        //GoodsInfoService goodsInfoService=(GoodsInfoService) SpringConfigTool.getBean("GoodsInfoService");
//        GoodsInfo goodsInfo=new GoodsInfo();
//        goodsInfo.setcAppid(2);
//        goodsInfo.setcDesc("我是小蜜蜂");
//        goodsInfo.setcEnable((byte)1);
//        goodsInfo.setcGoodsname("马道悠然蜂蜜");
//        goodsInfo.setcGoodsid(CipherUtil.generatePassword(goodsInfo.getcAppid()+"_"+goodsInfo.getcGoodsname()));
//        goodsInfo.setcPrice("10.00");
//        goodsInfo.setcPic("http://1111.11.132.1112");
//        goodsInfo.setcThumbnail("http://1111.11.132.1112");
//        goodsInfo.setcType((byte)1);
//        goodsInfoService.save(goodsInfo);
//         Usercard usercard= userCardService.findCardByForderid("201711101212427");
//        PageQueryResult<DestDTO> pageQueryResult= orderService.findOrderByTimeAndStutas(1,10,-6,2);
//        System.out.println("ssss="+pageQueryResult.getPageCount());
//      List<OrderInfo>  orderinfos=orderService.findUserfulOrderBytime("o5k511mJ2KNg02dQY1gAgGBQfdyY", TimeUtil.getCurrDay(-30),TimeUtil.getCurrDay(0),2);
//        System.out.println(orderinfos);
        String str=orderService.creatUserFav("o5k511mJ2KNg02dQY1gAgGBQfdyY",3,2);
        System.out.println(str);
    }

}
