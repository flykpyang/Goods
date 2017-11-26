package com.goods.cn.config;

import java.util.HashMap;

/**
 * Created by fly on 17/3/27.
 */
public class BaseConfig
{

    public static int lotteryPeople=100;

    //活动的结算点
    public final static int COMSUMEACTION=1;
    public final static int REGSTERACTION=2;
    public final static int BIRTHDAYACTION=3;
    public final static int LOGINACTION=4;
    public final static int HOLIDAYACTION=5;
    public final static int UPDATEUSERINFOACTION=6;
    public final static int FEEDBACKACTION=7;
    public final static int FIXTIMECOFFEEACTION=8;

    //定义产品的类型
    public final  static  int RECOMMENTTYPE=1;

    //定义卡卷的唯一id
    public final  static  int FREECARDID=1001;//免费卡
    public final  static  int RANDOMCARD=1000;//富贵在天卡
    public final  static  int PROSALECARD=1002;//指定商品减免卡
    public final  static  int FIXEDCARD=1003;//规定减免卡
    public final  static  int PAYFULLCARD=1004;//支付减免卡
    public final  static  int HOLIDAYCARD=1005;//节日减免卡
    public final  static  int ONEPRICECARD=1006;//一口价卡卷

    //卡卷的来源
    public final static  int  COMSUMEFROM=1;
    public final static  int  REGISTERFROM=2;
    public final static  int  BIRTHDAYFROM=3;
    public final static  int  LOGINFROM=4;
    public final static  int  HOLIDAYFROM=5;
    public final static  int  UPDATEUSERINFOFROM=6;
    public final static  int  WATERFROM=7;
    public final static  int  LOTTERYFROM=8;
    public final static  int  CHANGEFROM=9;

    //支付方式
    public final  static int WEIXINPAYWAY=0;
    public final  static int COFFEEBEANSWAY=1;

    //微信支付
    public final static String WXORDERURL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    public final static String WXCALLBACKURL="http://www.zhilanmama.com/goods/wx/pay";
    public final static String WXSENDTEMPURL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    //微信分享地址
    public final static String WXSKIP="https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=http://www.zhilanmama.com/goods/wx/call&response_type=code&scope=snsapi_base&state=%s_%s#wechat_redirect";

    //微信获取用户是否关注了公众号
    public final static String WXSUBSCRIBEURL="https://api.weixin.qq.com/cgi-bin/user/info";

    //
    public final static String baseGoodsurl = "http://www.zhilanmama.com/fm/";
    public final static String ORDERTEMPURL="";
    public final static String FEEDBACKURL="";


    //aceessthoken
    public static HashMap<String,String> aceessthokenMap=new HashMap<String, String>();
    public static HashMap<String,String> ticketmap=new HashMap<String, String>();
    //模版
    //订单模版
    public static final int TEMPLATEORDER=0;
    public static final int TEMPLATEORDERUPDATE=1;
    public static final int TEMPLATECHANGE=2;
}
