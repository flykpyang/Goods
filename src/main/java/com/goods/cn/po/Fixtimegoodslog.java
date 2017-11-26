package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_fixtimegoodslog", schema = "c_goods", catalog = "")
public class Fixtimegoodslog {
    private int cId;
    private int cCount;
    private String cFixtime;
    private String cGoodsid;
    private String cOpenid;
    private byte cStutas;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private String cOrderid;
    private int    cAppid;

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
    @Column(name = "c_count", nullable = false)
    public int getcCount() {
        return cCount;
    }

    public void setcCount(int cCount) {
        this.cCount = cCount;
    }

    @Basic
    @Column(name = "c_fixtime", nullable = false, length = 40)
    public String getcFixtime() {
        return cFixtime;
    }

    public void setcFixtime(String cFixtime) {
        this.cFixtime = cFixtime;
    }

    @Basic
    @Column(name = "c_goodsid", nullable = false, length = 200)
    public String getcGoodsid() {
        return cGoodsid;
    }

    public void setcGoodsid(String cGoodsid) {
        this.cGoodsid = cGoodsid;
    }

    @Basic
    @Column(name = "c_openid", nullable = true, length = 200)
    public String getcOpenid() {
        return cOpenid;
    }

    public void setcOpenid(String cOpenid) {
        this.cOpenid = cOpenid;
    }

    @Basic
    @Column(name = "c_stutas", nullable = false)
    public byte getcStutas() {
        return cStutas;
    }

    public void setcStutas(byte cStutas) {
        this.cStutas = cStutas;
    }

    @Basic
    @Column(name = "c_time", nullable = false)
    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    @Basic
    @Column(name = "c_orderid", nullable = false, length = 200)
    public String getcOrderid() {
        return cOrderid;
    }

    public void setcOrderid(String cOrderid) {
        this.cOrderid = cOrderid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fixtimegoodslog that = (Fixtimegoodslog) o;

        if (cId != that.cId) return false;
        if (cCount != that.cCount) return false;
        if (cStutas != that.cStutas) return false;
        if (cFixtime != null ? !cFixtime.equals(that.cFixtime) : that.cFixtime != null) return false;
        if (cGoodsid != null ? !cGoodsid.equals(that.cGoodsid) : that.cGoodsid != null) return false;
        if (cOpenid != null ? !cOpenid.equals(that.cOpenid) : that.cOpenid != null) return false;
        if (cTime != null ? !cTime.equals(that.cTime) : that.cTime != null) return false;
        if (cOrderid != null ? !cOrderid.equals(that.cOrderid) : that.cOrderid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + cCount;
        result = 31 * result + (cFixtime != null ? cFixtime.hashCode() : 0);
        result = 31 * result + (cGoodsid != null ? cGoodsid.hashCode() : 0);
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + (int) cStutas;
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + (cOrderid != null ? cOrderid.hashCode() : 0);
        return result;
    }
}
