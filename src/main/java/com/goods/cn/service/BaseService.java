package com.goods.cn.service;

import com.goods.cn.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BaseService")
public class BaseService {
    @Autowired
    ILockDao lockDao;

    @Autowired
    IUserDao userDao;

    @Autowired
    IGoodsInfoDao goodsInfoDao;

    @Autowired
    IUserCardDao  userCardDao;

    @Autowired
    IActionDao  actionDao;

    @Autowired
    IVipDao  vipDao;

    @Autowired
    IDisCardDao  disCardDao;

    @Autowired
    IFixTimeGoodsLogDao fixTimeGoodsLogDao;

    @Autowired
    IAppDao  appDao;

    @Autowired
    IActionRoutDao actionRoutDao;

    @Autowired
    IOrderDao orderDao;

    @Autowired
    IUserBuyLogDao userBuyLogDao;

    @Autowired
    IFeedbackDao  feedbackDao;

    @Autowired
    IRealGoodsDao  realGoodsDao;

    @Autowired
    IUserRealDao  userRealDao;

    @Autowired
    ILotteryDao  lotteryDao;

    @Autowired
    ILotteryLogDao  lotteryLogDao;

    @Autowired
    IRealPrizeDao  realPrizeDao;

    @Autowired
    ITaskDao  taskDao;

    @Autowired
    ITaskProDao  taskProDao;

    @Autowired
    IWaterDao waterDao;

    @Autowired
    IRandDisPriceDao randDisPriceDao;

    @Autowired
    IUserAddressDao  userAddressDao;

    @Autowired
    ITemplateDao templateDao;

    @Autowired
    ITypeDao typeDao;

    @Autowired
    IAdddicDao adddicDao;

    @Autowired
    IFareTemplateDao fareTemplateDao;

    @Autowired
    IFaredesDao  faredesDao;

    @Autowired
    IExceptionlogDao exceptionlogDao;

    @Autowired
    IShareDao  shareDao;

    @Autowired
    ILoveDao loveDao;

    @Autowired
    IAttachGoodsDao attachGoodsDao;
}
