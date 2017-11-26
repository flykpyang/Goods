package com.goods.cn.controller;

import com.fly.cn.netframe.RequestInfo;
import com.fly.cn.util.WeiXinCompare;
import com.goods.cn.config.CgiConfig;
import com.goods.cn.factory.WxOpenidNetAns;
import com.goods.cn.po.App;
import com.goods.cn.service.AppService;
import com.goods.cn.service.OrderService;
import com.goods.cn.service.WxService;
import com.goods.cn.util.GoodsUtil;
import com.goods.cn.util.WxPayUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by fly on 17/7/10.
 * 微信回调的处理controller
 */
@Controller
public class WxPayController {

    @Autowired
    OrderService orderService;

    @Autowired
    AppService appService;

    @Autowired
    WxService  wxService;

    /**
     * 验证mvc
     *
     * @return
     */
    @RequestMapping(value = "/wx/validate")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public String wxHandle(HttpServletRequest request) {
        String method = request.getMethod();
        if (method.equalsIgnoreCase("GET")) {
            //验证
            System.out.println("str=" + request.getQueryString());
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String echostr = request.getParameter("echostr");
            String nonce = request.getParameter("nonce");
            if (GoodsUtil.fristValidate(signature, timestamp, echostr, nonce)) {
                return echostr;
            }
        } else if (method.equalsIgnoreCase("POST")) {
            //收到的消息
            try {
                ServletInputStream in = request.getInputStream();
                SAXReader reader = new SAXReader();
                Document document = reader.read(in);
                System.out.println(document.toString());
            } catch (Exception e) {

            }
        }
        return "";
    }

    /**
     * 验证mvc
     *
     * @return
     */
    @RequestMapping(value = "/wx/call", method = RequestMethod.GET)
    public void call(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        try {
            //获取对应的app
            System.out.println("stat=" + state);
            List<String> list = getAppIdAndStatByStat(state);
            if (list != null && list.size() > 1) {
                String appId = list.get(0);
                //System.out.println("appid=" + appId);
                String logicStat = list.get(1);
                App app = appService.findAppByAppId(appId);
                //这里可以优化TODO
                //request.getRequestDispatcher()
                WxOpenidNetAns openidNetAns = getUserOpenidRequest(logicStat, code, app);
                String redirectUrl = openidNetAns.realUrl;
                if (redirectUrl != null && !redirectUrl.equals("")) {
                    System.out.println("wx redirectu url=" + redirectUrl);
                    response.sendRedirect(redirectUrl);
                }
            }
        } catch (Exception e) {


        } finally {
            //关掉输流
            try {
                if (response != null) {
                    response.getWriter().close();
                }
            } catch (Exception e) {

            }
        }
    }


    private List<String> getAppIdAndStatByStat(String stat) {
        if (stat != null) {
            String[] strings = stat.split("_");
            if (strings.length > 1) {
                List<String> list = new ArrayList<String>();
                list.add(strings[0]);
                list.add(strings[1]);
                return list;
            }
        }
        return null;
    }

    private WxOpenidNetAns getUserOpenidRequest(String logicstate, String code, App app) {

        // 换acess 和 openid
        RequestInfo info = new RequestInfo();
        info.requestKey = CgiConfig.WXOPENID;
        info.state = logicstate;
        info.appId = String.valueOf(app.getcId());
        info.url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + app.getcAppid()
                + "&secret="
                + app.getcSecretkey()
                + "&code="
                + code
                + "&grant_type=authorization_code";
        //System.out.println("url ==" + info.url);
        WxOpenidNetAns openidNetAns = (WxOpenidNetAns) WxPayUtil.startSendWeiXin(info);
        return openidNetAns;
    }


    @RequestMapping(value = "/wx/pay", method = RequestMethod.POST, produces = "application/xml;charset=utf-8")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public String wxPlayCallBack(HttpServletRequest request) {
        String result = "FAIL";
        String success = "SUCCESS";
        ServletInputStream in = null;
        try {
            in = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            Element root = document.getRootElement();
            if (validate(root)) {
                Element return_codeElement = root.element("return_code");
                String return_code = return_codeElement.getText();
                Element result_codeElement = root.element("result_code");
                String result_code = result_codeElement.getText();
                Element orderidElement = root.element("out_trade_no");
                String out_trade_no = orderidElement.getText();
                Element openidElement = root.element("openid");
                String openid = openidElement.getText();
                Element attchElement = root.element("attach");
                String attch = "";
                if (attchElement != null) {
                    attch = attchElement.getText();
                }
                Element transactionElement = root.element("transaction_id");
                String transaction_id = transactionElement.getText();
                //System.out.println("transaction_id=" + transaction_id);
                if (return_code != null && result_code != null && out_trade_no != null && openid != null) {
                    if (result_code.equals("SUCCESS") && return_code.equals("SUCCESS")) {
                        //成功
                        orderService.payCallBackService(out_trade_no, openid, attch, transaction_id);
                        result = success;
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {

            }
        }
        String content = "<xml><return_code><![CDATA[" + result + "]]></return_code></xml>";
        return content;
    }

    private boolean validate(Element root) throws Exception {
        TreeMap<String, String> weixinMap = new TreeMap<String, String>(
                new WeiXinCompare());
        String sign = "";
        String appid = "";
        List<Element> elements = root.elements();
        for (Element element : elements) {
            String key = element.getName();
            //System.out.println("key======" + key + "  value=" + element.getText());
            if (!key.equals("sign")) {
                weixinMap.put(key, element.getText());
                if (key.equals("appid")) {
                    appid = element.getText();
                }
            } else {
                sign = element.getText();
            }
        }
        App app = appService.findAppByAppId(appid);
        if (app != null) {
            String computeSign = WxPayUtil.validateSignByMap(weixinMap, app.getcPaykey());
            //System.out.println("computeSign==" + computeSign + "  sign=" + sign);
            return sign.equals(computeSign);
        }
        return false;
    }

}
