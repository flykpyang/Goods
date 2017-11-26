package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_order_info", schema = "c_goods", catalog = "")
public class OrderInfo {
    private int cId;
    private String cOrderid;
    private String cCoffeelist;
    private byte cStatus=(byte)0;
    private String cOpenid;
    private String cTotalPrice="0.00";
    private Date cCtime= TimeUtil.getCurrTimeHm();
    private String weixinOrderid;
    private byte cPayway=(byte)0;
    private int  cAppid;
    private int  cAddrid;
    private String cLogisticsid;
    private Date   cMtime=TimeUtil.getCurrTimeHm();
    private String cAttch;
    private String cRemark;
    public  String farePrice;

    //c_remark
    @Basic
    @Column(name = "c_remark", nullable = true, length = 200)
    public String getcRemark() {
        return cRemark;
    }

    public void setcRemark(String cRemark) {
        this.cRemark = cRemark;
    }


    //c_attch

    @Basic
    @Column(name = "c_attch", nullable = true, length = 500)
    public String getcAttch() {
        return cAttch;
    }

    public void setcAttch(String cAttch) {
        this.cAttch = cAttch;
    }

    public List<SimpleOrderInfo> simpleOrderInfos;
    public List<String> priceList;
    public List<Usercard> disCards;

    //c_mtime
    @Basic
    @Column(name = "c_mtime", nullable = false)
    public Date getcMtime() {
        return cMtime;
    }

    public void setcMtime(Date cMtime) {
        this.cMtime = cMtime;
    }

    //c_logisticsid
    @Basic
    @Column(name = "c_logisticsid", nullable = true, length = 100)
    public String getcLogisticsid() {
        return cLogisticsid;
    }

    public void setcLogisticsid(String cLogisticsid) {
        this.cLogisticsid = cLogisticsid;
    }

    @Basic
    @Column(name = "c_addrid", nullable = false)
    public int getcAddrid() {
        return cAddrid;
    }

    public void setcAddrid(int cAddrid) {
        this.cAddrid = cAddrid;
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
    @Column(name = "c_orderid", nullable = false, length = 225)
    public String getcOrderid() {
        return cOrderid;
    }

    public void setcOrderid(String cOrderid) {
        this.cOrderid = cOrderid;
    }

    @Basic
    @Column(name = "c_coffeelist", nullable = false, length = -1)
    public String getcCoffeelist() {
        return cCoffeelist;
    }

    public void setcCoffeelist(String cCoffeelist) {
        this.cCoffeelist = cCoffeelist;
    }

    @Basic
    @Column(name = "c_status", nullable = false)
    public byte getcStatus() {
        return cStatus;
    }

    public void setcStatus(byte cStatus) {
        this.cStatus = cStatus;
    }

    @Basic
    @Column(name = "c_openid", nullable = true, length = 45)
    public String getcOpenid() {
        return cOpenid;
    }

    public void setcOpenid(String cOpenid) {
        this.cOpenid = cOpenid;
    }

    @Basic
    @Column(name = "c_total_price", nullable = false, length = 45)
    public String getcTotalPrice() {
        return cTotalPrice;
    }

    public void setcTotalPrice(String cTotalPrice) {
        this.cTotalPrice = cTotalPrice;
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
    @Column(name = "weixin_orderid", nullable = true, length = 225)
    public String getWeixinOrderid() {
        return weixinOrderid;
    }

    public void setWeixinOrderid(String weixinOrderid) {
        this.weixinOrderid = weixinOrderid;
    }

    @Basic
    @Column(name = "c_payway", nullable = false)
    public byte getcPayway() {
        return cPayway;
    }

    public void setcPayway(byte cPayway) {
        this.cPayway = cPayway;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderInfo orderInfo = (OrderInfo) o;

        if (cId != orderInfo.cId) return false;
        if (cStatus != orderInfo.cStatus) return false;
        if (cPayway != orderInfo.cPayway) return false;
        if (cOrderid != null ? !cOrderid.equals(orderInfo.cOrderid) : orderInfo.cOrderid != null) return false;
        if (cCoffeelist != null ? !cCoffeelist.equals(orderInfo.cCoffeelist) : orderInfo.cCoffeelist != null)
            return false;
        if (cOpenid != null ? !cOpenid.equals(orderInfo.cOpenid) : orderInfo.cOpenid != null) return false;
        if (cTotalPrice != null ? !cTotalPrice.equals(orderInfo.cTotalPrice) : orderInfo.cTotalPrice != null)
            return false;
        if (cCtime != null ? !cCtime.equals(orderInfo.cCtime) : orderInfo.cCtime != null) return false;
        if (weixinOrderid != null ? !weixinOrderid.equals(orderInfo.weixinOrderid) : orderInfo.weixinOrderid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cOrderid != null ? cOrderid.hashCode() : 0);
        result = 31 * result + (cCoffeelist != null ? cCoffeelist.hashCode() : 0);
        result = 31 * result + (int) cStatus;
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + (cTotalPrice != null ? cTotalPrice.hashCode() : 0);
        result = 31 * result + (cCtime != null ? cCtime.hashCode() : 0);
        result = 31 * result + (weixinOrderid != null ? weixinOrderid.hashCode() : 0);
        result = 31 * result + (int) cPayway;
        return result;
    }
}
