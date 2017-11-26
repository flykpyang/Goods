package com.goods.cn.requestinfo;


import com.fly.cn.netframe.BaseHttpNetWork;
import com.fly.cn.netframe.RequestInfo;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by fly on 17/4/5.
 */
public class WeiXinPayRequest extends BaseHttpNetWork {
    public WeiXinPayRequest(RequestInfo info) {
        super(info);
        method="POST";
    }

    @Override
    public void setHeader() {

    }

    @Override
    public byte[] setBody(RequestInfo info) {
        WeiXinPayRequestInfo weiXinPayRequestInfo=(WeiXinPayRequestInfo)info;
        Document document = DocumentHelper.createDocument();
        try {
            Element xmlElement=document.addElement("xml");
            TreeMap<String, String> treeMap=weiXinPayRequestInfo.signMap;
            Iterator iterator=treeMap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry entry=(Map.Entry)iterator.next();
                String str=(String)entry.getKey();
                String value=(String)entry.getValue();
                Element element=xmlElement.addElement(str);
                element.setText(value);
            }
            System.out.println("post ="+document.asXML());
            return document.asXML().getBytes();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void modifyUrl(RequestInfo info) {

    }
}
