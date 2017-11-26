package com.goods.cn.observer.message;

import com.fly.cn.observes.Message;
import com.goods.cn.po.Userreal;

import java.util.List;

public class ChangeRealGoodsMessage extends Message{

   public List<Userreal> userreals;
   public int appid;

   public ChangeRealGoodsMessage(int messageid,List<Userreal> userreals,int appid){
       this.messageid=messageid;
       this.userreals=userreals;
       this.appid=appid;
   }
}
