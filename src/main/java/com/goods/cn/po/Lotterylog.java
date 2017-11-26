package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_lotterylog", schema = "c_goods", catalog = "")
public class Lotterylog {
    private int cId;
    private Date cTime = TimeUtil.getCurrTimeHm();
    private String cPrizekey;
    private String cOpenid;
    private int    appid;


    @Basic
    @Column(name = "c_appid", nullable = false)
    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "c_id", nullable = false)
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
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
    @Column(name = "c_prizekey", nullable = true)
    public String getcPrizekey() {
        return cPrizekey;
    }

    public void setcPrizekey(String cPrizekey) {
        this.cPrizekey = cPrizekey;
    }

    @Basic
    @Column(name = "c_openid", nullable = false, length = 100)
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

        Lotterylog that = (Lotterylog) o;

        if (cId != that.cId) return false;
        if (cTime != null ? !cTime.equals(that.cTime) : that.cTime != null) return false;
        if (cOpenid != null ? !cOpenid.equals(that.cOpenid) : that.cOpenid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        return result;
    }
}
