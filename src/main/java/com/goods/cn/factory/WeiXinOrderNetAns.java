package com.goods.cn.factory;


import com.fly.cn.netframe.BaseNetAns;
import com.fly.cn.netframe.BaseNetWork;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;

/**
 * Created by fly on 17/7/7.
 */
public class WeiXinOrderNetAns extends BaseNetAns {

    public String prepay_id;
    public String nonce_str;


    @Override
    public void onReadyAns(BaseNetWork baseNetWork) {

    }

    @Override
    public void onStartAns(BaseNetWork baseNetWork) {

    }

    @Override
    public void onDowningAns(byte[] buf, int len, long downsize, BaseNetWork baseNetWork) {

    }

    @Override
    public void onReceiveAns(BaseNetWork baseNetWork) {
        String cmd = baseNetWork.requestInfo.requestKey;
        // 微信支付进来了
        byte[] content = baseNetWork.content;
        //System.out.println("content=" + new String(content));
        String xml = new String(content);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new StringReader(xml));
            Element root = document.getRootElement();
            Element code = root.element("return_code");
            String text = code.getText();
            // 请求成功
            if (text.equals("SUCCESS")) {
                Element preidAttribute = root.element("prepay_id");
                prepay_id = preidAttribute.getText();
                Element nostrAttribute = root.element("nonce_str");
                nonce_str = nostrAttribute.getText();
            } else {
                isError = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isError = true;
        }
    }

    @Override
    public void onStopAns(BaseNetWork baseNetWork) {

    }

    @Override
    public void onErrorAns(BaseNetWork baseNetWork) {
        isError=true;
    }

}
