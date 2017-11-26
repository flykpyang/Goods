package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_realprize", schema = "c_goods", catalog = "")
public class Realprize {
    private int cId;
    private String cKey;
    private String cValue;
    private int cPoll;
    private byte cType;
    private String cPic;
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
    @Column(name = "c_key", nullable = false, length = 45)
    public String getcKey() {
        return cKey;
    }

    public void setcKey(String cKey) {
        this.cKey = cKey;
    }

    @Basic
    @Column(name = "c_value", nullable = false, length = 45)
    public String getcValue() {
        return cValue;
    }

    public void setcValue(String cValue) {
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

    @Basic
    @Column(name = "c_type", nullable = false)
    public byte getcType() {
        return cType;
    }

    public void setcType(byte cType) {
        this.cType = cType;
    }

    @Basic
    @Column(name = "c_pic", nullable = false, length = 200)
    public String getcPic() {
        return cPic;
    }

    public void setcPic(String cPic) {
        this.cPic = cPic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Realprize realprize = (Realprize) o;

        if (cId != realprize.cId) return false;
        if (cPoll != realprize.cPoll) return false;
        if (cType != realprize.cType) return false;
        if (cKey != null ? !cKey.equals(realprize.cKey) : realprize.cKey != null) return false;
        if (cValue != null ? !cValue.equals(realprize.cValue) : realprize.cValue != null) return false;
        if (cPic != null ? !cPic.equals(realprize.cPic) : realprize.cPic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cKey != null ? cKey.hashCode() : 0);
        result = 31 * result + (cValue != null ? cValue.hashCode() : 0);
        result = 31 * result + cPoll;
        result = 31 * result + (int) cType;
        result = 31 * result + (cPic != null ? cPic.hashCode() : 0);
        return result;
    }
}
