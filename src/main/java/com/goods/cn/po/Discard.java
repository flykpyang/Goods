package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_discard", schema = "c_goods", catalog = "")
public class Discard {
    private int cId;
    private String cName;
    private String cDes;
    private int cValue=-1;
    private int cIschange;
    private int cIdentification;
    private int cEffecttime;
    private String cPic;
    private int cSore;
    private String cPrice="0.00";
    private String cRemark;
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
    @Column(name = "c_name", nullable = false, length = 45)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "c_des", nullable = false, length = 225)
    public String getcDes() {
        return cDes;
    }

    public void setcDes(String cDes) {
        this.cDes = cDes;
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
    @Column(name = "c_ischange", nullable = false)
    public int getcIschange() {
        return cIschange;
    }

    public void setcIschange(int cIschange) {
        this.cIschange = cIschange;
    }

    @Basic
    @Column(name = "c_identification", nullable = false)
    public int getcIdentification() {
        return cIdentification;
    }

    public void setcIdentification(int cIdentification) {
        this.cIdentification = cIdentification;
    }

    @Basic
    @Column(name = "c_effecttime", nullable = false)
    public int getcEffecttime() {
        return cEffecttime;
    }

    public void setcEffecttime(int cEffecttime) {
        this.cEffecttime = cEffecttime;
    }

    @Basic
    @Column(name = "c_pic", nullable = false, length = 225)
    public String getcPic() {
        return cPic;
    }

    public void setcPic(String cPic) {
        this.cPic = cPic;
    }

    @Basic
    @Column(name = "c_sore", nullable = false)
    public int getcSore() {
        return cSore;
    }

    public void setcSore(int cSore) {
        this.cSore = cSore;
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
    @Column(name = "c_remark", nullable = true, length = 225)
    public String getcRemark() {
        return cRemark;
    }

    public void setcRemark(String cRemark) {
        this.cRemark = cRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discard discard = (Discard) o;

        if (cId != discard.cId) return false;
        if (cValue != discard.cValue) return false;
        if (cIschange != discard.cIschange) return false;
        if (cIdentification != discard.cIdentification) return false;
        if (cEffecttime != discard.cEffecttime) return false;
        if (cSore != discard.cSore) return false;
        if (cName != null ? !cName.equals(discard.cName) : discard.cName != null) return false;
        if (cDes != null ? !cDes.equals(discard.cDes) : discard.cDes != null) return false;
        if (cPic != null ? !cPic.equals(discard.cPic) : discard.cPic != null) return false;
        if (cPrice != null ? !cPrice.equals(discard.cPrice) : discard.cPrice != null) return false;
        if (cRemark != null ? !cRemark.equals(discard.cRemark) : discard.cRemark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (cDes != null ? cDes.hashCode() : 0);
        result = 31 * result + cValue;
        result = 31 * result + cIschange;
        result = 31 * result + cIdentification;
        result = 31 * result + cEffecttime;
        result = 31 * result + (cPic != null ? cPic.hashCode() : 0);
        result = 31 * result + cSore;
        result = 31 * result + (cPrice != null ? cPrice.hashCode() : 0);
        result = 31 * result + (cRemark != null ? cRemark.hashCode() : 0);
        return result;
    }
}
