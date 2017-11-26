package com.goods.cn.po;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.util.CipherUtil;
import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_goods_info", schema = "c_goods", catalog = "")
public class GoodsInfo {
    private int cId;
    private String cGoodsid;
    private String cGoodsname;
    private String cPrice;
    private String cPic;
    private byte cEnable;
    private String cDesc;
    private byte cThick;
    private String cRemark;
    private Date cCtime= TimeUtil.getCurrTimeHm();
    private int cLikecount;
    private byte cType;
    private String cThumbnail;
    private int  cAppid;
    private int  cleftNumber=100;
    private int  cFareTemplateId=0;
    private int  cOrder;

    @Basic
    @Column(name = "c_order", nullable = false)
    public int getcOrder() {
        return cOrder;
    }

    public void setcOrder(int cOrder) {
        this.cOrder = cOrder;
    }



    public  String realPrice="-1";

    //每个人能购买的数量
    public  int    fixtimeonlyCupNumber=0;
    //这个用户还能抢购咖啡
    public  int  isLeftCount;
    //设置的限时抢购的总数量
    public  int    fixtimecount=0;
    //活动的时间
    public  String fixtimekey;

    public boolean islove;


    @Basic
    @Column(name = "c_faretemplateid", nullable = false)
    public int getcFareTemplateId() {
        return cFareTemplateId;
    }

    public void setcFareTemplateId(int cFareTemplateId) {
        this.cFareTemplateId = cFareTemplateId;
    }

    @Basic
    @Column(name = "c_leftnumber", nullable = false)
    public int getCleftNumber() {
        return cleftNumber;
    }

    public void setCleftNumber(int cleftNumber) {
        this.cleftNumber = cleftNumber;
    }

    @Basic
    @Column(name = "c_appid", nullable = false)
    public int getcAppid() {
        return cAppid;
    }

    public void setcAppid(int cAppid) {
        this.cAppid = cAppid;
    }

    @javax.persistence.Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "c_id", nullable = false)
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "c_goodsid", nullable = false, length = 255)
    public String getcGoodsid() {
        return cGoodsid;
    }

    public void setcGoodsid(String cGoodsid) {
        this.cGoodsid = cGoodsid;
    }

    @Basic
    @Column(name = "c_goodsname", nullable = false, length = 255)
    public String getcGoodsname() {
        return cGoodsname;
    }

    public void setcGoodsname(String cGoodsname) {
        this.cGoodsname = cGoodsname;
    }

    @Basic
    @Column(name = "c_price", nullable = false, length = 45)
    public String getcPrice() {
        return cPrice;
    }

    public void setcPrice(String cPrice) {
        this.cPrice = cPrice;
    }

    @Basic
    @Column(name = "c_pic", nullable = false, length = 1000)
    public String getcPic() {
        return cPic;
    }

    public void setcPic(String cPic) {
        this.cPic = cPic;
    }

    @Basic
    @Column(name = "c_enable", nullable = false)
    public byte getcEnable() {
        return cEnable;
    }

    public void setcEnable(byte cEnable) {
        this.cEnable = cEnable;
    }

    @Basic
    @Column(name = "c_desc", nullable = true, length = 45)
    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    @Basic
    @Column(name = "c_thick", nullable = false)
    public byte getcThick() {
        return cThick;
    }

    public void setcThick(byte cThick) {
        this.cThick = cThick;
    }

    @Basic
    @Column(name = "c_remark", nullable = true, length = -1)
    public String getcRemark() {
        return cRemark;
    }

    public void setcRemark(String cRemark) {
        this.cRemark = cRemark;
    }

    @Basic
    @Column(name = "c_ctime", nullable = false)
    public Date getcCtime() {
        return cCtime;
    }

    public void setcCtime(Date cCtime) {
        this.cCtime = cCtime;
    }

    @Basic
    @Column(name = "c_likecount", nullable = false)
    public int getcLikecount() {
        return cLikecount;
    }

    public void setcLikecount(int cLikecount) {
        this.cLikecount = cLikecount;
    }

    @Basic
    @Column(name = "c_type", nullable = false)
    public byte getcType() {
        return cType;
    }

    public void setcType(byte cType) {
        this.cType = cType;
    }

    @Basic
    @Column(name = "c_thumbnail", nullable = false, length = 225)
    public String getcThumbnail() {
        return cThumbnail;
    }

    public void setcThumbnail(String cThumbnail) {
        this.cThumbnail = cThumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsInfo goodsInfo = (GoodsInfo) o;

        if (cId != goodsInfo.cId) return false;
        if (cEnable != goodsInfo.cEnable) return false;
        if (cThick != goodsInfo.cThick) return false;
        if (cLikecount != goodsInfo.cLikecount) return false;
        if (cType != goodsInfo.cType) return false;
        if (cGoodsid != null ? !cGoodsid.equals(goodsInfo.cGoodsid) : goodsInfo.cGoodsid != null) return false;
        if (cGoodsname != null ? !cGoodsname.equals(goodsInfo.cGoodsname) : goodsInfo.cGoodsname != null) return false;
        if (cPrice != null ? !cPrice.equals(goodsInfo.cPrice) : goodsInfo.cPrice != null) return false;
        if (cPic != null ? !cPic.equals(goodsInfo.cPic) : goodsInfo.cPic != null) return false;
        if (cDesc != null ? !cDesc.equals(goodsInfo.cDesc) : goodsInfo.cDesc != null) return false;
        if (cRemark != null ? !cRemark.equals(goodsInfo.cRemark) : goodsInfo.cRemark != null) return false;
        if (cCtime != null ? !cCtime.equals(goodsInfo.cCtime) : goodsInfo.cCtime != null) return false;
        if (cThumbnail != null ? !cThumbnail.equals(goodsInfo.cThumbnail) : goodsInfo.cThumbnail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cGoodsid != null ? cGoodsid.hashCode() : 0);
        result = 31 * result + (cGoodsname != null ? cGoodsname.hashCode() : 0);
        result = 31 * result + (cPrice != null ? cPrice.hashCode() : 0);
        result = 31 * result + (cPic != null ? cPic.hashCode() : 0);
        result = 31 * result + (int) cEnable;
        result = 31 * result + (cDesc != null ? cDesc.hashCode() : 0);
        result = 31 * result + (int) cThick;
        result = 31 * result + (cRemark != null ? cRemark.hashCode() : 0);
        result = 31 * result + (cCtime != null ? cCtime.hashCode() : 0);
        result = 31 * result + cLikecount;
        result = 31 * result + (int) cType;
        result = 31 * result + (cThumbnail != null ? cThumbnail.hashCode() : 0);
        return result;
    }

    public static void main(String[] agrs){
        GoodsInfo goodsInfo=new GoodsInfo();
        goodsInfo.cAppid=1;
        goodsInfo.cDesc="妈妈的味道";
        goodsInfo.cEnable=(byte)1;
        goodsInfo.cGoodsname="红烧鱼块";
        goodsInfo.cGoodsid= CipherUtil.generatePassword(goodsInfo.cAppid+"_"+goodsInfo.cGoodsname);
        goodsInfo.cPrice="10.00";
        goodsInfo.cPic="http://1111.11.132.1112";
        goodsInfo.cType=(byte)1;
        String json= JSONObject.toJSONString(goodsInfo);
        System.out.println(json);
    }
}
