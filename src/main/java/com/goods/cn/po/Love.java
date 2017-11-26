package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_love", schema = "c_goods", catalog = "")
public class Love {
    private int cId;
    private String cOpenid;
    private String cGoodsid;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private int cAppid;

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
    @Column(name = "c_openid", nullable = false, length = 50)
    public String getcOpenid() {
        return cOpenid;
    }

    public void setcOpenid(String cOpenid) {
        this.cOpenid = cOpenid;
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
    @Column(name = "c_time", nullable = false)
    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    @Basic
    @Column(name = "c_appid", nullable = false)
    public int getcAppid() {
        return cAppid;
    }

    public void setcAppid(int cAppid) {
        this.cAppid = cAppid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Love love = (Love) o;

        if (cId != love.cId) return false;
        if (cAppid != love.cAppid) return false;
        if (cOpenid != null ? !cOpenid.equals(love.cOpenid) : love.cOpenid != null) return false;
        if (cGoodsid != null ? !cGoodsid.equals(love.cGoodsid) : love.cGoodsid != null) return false;
        if (cTime != null ? !cTime.equals(love.cTime) : love.cTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + (cGoodsid != null ? cGoodsid.hashCode() : 0);
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + cAppid;
        return result;
    }
}
