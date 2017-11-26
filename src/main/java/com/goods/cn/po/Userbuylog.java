package com.goods.cn.po;


import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_userbuylog", schema = "c_goods", catalog = "")
public class Userbuylog {
    private int cId;
    private int cAppid;
    private String cGoodsid;
    private Date cCtime= TimeUtil.getCurrTimeHm();
    private String cOpenid;

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
    @Column(name = "c_appid", nullable = false)
    public int getcAppid() {
        return cAppid;
    }

    public void setcAppid(int cAppid) {
        this.cAppid = cAppid;
    }

    @Basic
    @Column(name = "c_goodsid", nullable = false, length = 50)
    public String getcGoodsid() {
        return cGoodsid;
    }

    public void setcGoodsid(String cGoodsid) {
        this.cGoodsid = cGoodsid;
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
    @Column(name = "c_openid", nullable = false, length = 50)
    public String getcOpenid() {
        return cOpenid;
    }

    public void setcOpenid(String cOpenid) {
        this.cOpenid = cOpenid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userbuylog that = (Userbuylog) o;

        if (cId != that.cId) return false;
        if (cAppid != that.cAppid) return false;
        if (cGoodsid != null ? !cGoodsid.equals(that.cGoodsid) : that.cGoodsid != null) return false;
        if (cCtime != null ? !cCtime.equals(that.cCtime) : that.cCtime != null) return false;
        if (cOpenid != null ? !cOpenid.equals(that.cOpenid) : that.cOpenid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + cAppid;
        result = 31 * result + (cGoodsid != null ? cGoodsid.hashCode() : 0);
        result = 31 * result + (cCtime != null ? cCtime.hashCode() : 0);
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        return result;
    }
}
