package com.goods.cn.po;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_action", schema = "c_goods", catalog = "")
public class Action {
    private int cId;
    private String cGift;
    private Integer cBalance;
    private Date cBegintime;
    private Date cEndtime;
    private int cVip;
    private String cName;
    private String cCondition;
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
    @Column(name = "c_gift", nullable = false, length = 1000)
    public String getcGift() {
        return cGift;
    }

    public void setcGift(String cGift) {
        this.cGift = cGift;
    }

    @Basic
    @Column(name = "c_balance", nullable = true)
    public Integer getcBalance() {
        return cBalance;
    }

    public void setcBalance(Integer cBalance) {
        this.cBalance = cBalance;
    }

    @Basic
    @Column(name = "c_begintime", nullable = true)
    public Date getcBegintime() {
        return cBegintime;
    }

    public void setcBegintime(Date cBegintime) {
        this.cBegintime = cBegintime;
    }

    @Basic
    @Column(name = "c_endtime", nullable = true)
    public Date getcEndtime() {
        return cEndtime;
    }

    public void setcEndtime(Date cEndtime) {
        this.cEndtime = cEndtime;
    }

    @Basic
    @Column(name = "c_vip", nullable = false)
    public int getcVip() {
        return cVip;
    }

    public void setcVip(int cVip) {
        this.cVip = cVip;
    }

    @Basic
    @Column(name = "c_name", nullable = false, length = 45)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "c_condition", nullable = true, length = 1000)
    public String getcCondition() {
        return cCondition;
    }

    public void setcCondition(String cCondition) {
        this.cCondition = cCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        if (cId != action.cId) return false;
        if (cVip != action.cVip) return false;
        if (cGift != null ? !cGift.equals(action.cGift) : action.cGift != null) return false;
        if (cBalance != null ? !cBalance.equals(action.cBalance) : action.cBalance != null) return false;
        if (cBegintime != null ? !cBegintime.equals(action.cBegintime) : action.cBegintime != null) return false;
        if (cEndtime != null ? !cEndtime.equals(action.cEndtime) : action.cEndtime != null) return false;
        if (cName != null ? !cName.equals(action.cName) : action.cName != null) return false;
        if (cCondition != null ? !cCondition.equals(action.cCondition) : action.cCondition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cGift != null ? cGift.hashCode() : 0);
        result = 31 * result + (cBalance != null ? cBalance.hashCode() : 0);
        result = 31 * result + (cBegintime != null ? cBegintime.hashCode() : 0);
        result = 31 * result + (cEndtime != null ? cEndtime.hashCode() : 0);
        result = 31 * result + cVip;
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (cCondition != null ? cCondition.hashCode() : 0);
        return result;
    }
}
