package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_randiscount", schema = "c_goods", catalog = "")
public class Randiscount {
    private int cId;
    private String cKey;
    private int cValue;
    private int cPoll;
    private int cAppid;

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
    @Column(name = "c_key", nullable = false, length = 45)
    public String getcKey() {
        return cKey;
    }

    public void setcKey(String cKey) {
        this.cKey = cKey;
    }

    @Basic
    @Column(name = "c_value", nullable = false)
    public int getcValue() {
        return cValue;
    }

    public void setcValue(int cValue) {
        this.cValue = cValue;
    }

    @Basic
    @Column(name = "c_poll", nullable = false)
    public int getcPoll() {
        return cPoll;
    }

    public void setcPoll(int cPoll) {
        this.cPoll = cPoll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Randiscount that = (Randiscount) o;

        if (cId != that.cId) return false;
        if (cValue != that.cValue) return false;
        if (cPoll != that.cPoll) return false;
        if (cKey != null ? !cKey.equals(that.cKey) : that.cKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cKey != null ? cKey.hashCode() : 0);
        result = 31 * result + cValue;
        result = 31 * result + cPoll;
        return result;
    }
}
