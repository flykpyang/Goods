package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_usercard", schema = "c_goods", catalog = "")
public class Usercard {
    private int cId;
    private String cOpenid;
    private String cPhone;
    private String cCardid;
    private Date cBegintime= TimeUtil.getCurrTimeHm();
    private Date cExpiretime;
    private int cIsuse;
    private int cIdentification;
    private String cCardname;
    private String cCarddes;
    private String cCardpic;
    private String cCardvalue;
    private String cOrderid;
    private Date cUsetime;
    private String cCardremark;
    private int cFrom;
    private String cFromorderid;
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
    @Column(name = "c_openid", nullable = false, length = 45)
    public String getcOpenid() {
        return cOpenid;
    }

    public void setcOpenid(String cOpenid) {
        this.cOpenid = cOpenid;
    }

    @Basic
    @Column(name = "c_phone", nullable = false, length = 45)
    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    @Basic
    @Column(name = "c_cardid", nullable = false, length = 45)
    public String getcCardid() {
        return cCardid;
    }

    public void setcCardid(String cCardid) {
        this.cCardid = cCardid;
    }

    @Basic
    @Column(name = "c_begintime", nullable = false)
    public Date getcBegintime() {
        return cBegintime;
    }

    public void setcBegintime(Date cBegintime) {
        this.cBegintime = cBegintime;
    }

    @Basic
    @Column(name = "c_expiretime", nullable = true)
    public Date getcExpiretime() {
        return cExpiretime;
    }

    public void setcExpiretime(Date cExpiretime) {
        this.cExpiretime = cExpiretime;
    }

    @Basic
    @Column(name = "c_isuse", nullable = false)
    public int getcIsuse() {
        return cIsuse;
    }

    public void setcIsuse(int cIsuse) {
        this.cIsuse = cIsuse;
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
    @Column(name = "c_cardname", nullable = false, length = 45)
    public String getcCardname() {
        return cCardname;
    }

    public void setcCardname(String cCardname) {
        this.cCardname = cCardname;
    }

    @Basic
    @Column(name = "c_carddes", nullable = false, length = 225)
    public String getcCarddes() {
        return cCarddes;
    }

    public void setcCarddes(String cCarddes) {
        this.cCarddes = cCarddes;
    }

    @Basic
    @Column(name = "c_cardpic", nullable = false, length = 225)
    public String getcCardpic() {
        return cCardpic;
    }

    public void setcCardpic(String cCardpic) {
        this.cCardpic = cCardpic;
    }

    @Basic
    @Column(name = "c_cardvalue", nullable = false, length = 11)
    public String getcCardvalue() {
        return cCardvalue;
    }

    public void setcCardvalue(String cCardvalue) {
        this.cCardvalue = cCardvalue;
    }

    @Basic
    @Column(name = "c_orderid", nullable = true, length = 225)
    public String getcOrderid() {
        return cOrderid;
    }

    public void setcOrderid(String cOrderid) {
        this.cOrderid = cOrderid;
    }

    @Basic
    @Column(name = "c_usetime", nullable = true)
    public Date getcUsetime() {
        return cUsetime;
    }

    public void setcUsetime(Date cUsetime) {
        this.cUsetime = cUsetime;
    }

    @Basic
    @Column(name = "c_cardremark", nullable = true, length = 225)
    public String getcCardremark() {
        return cCardremark;
    }

    public void setcCardremark(String cCardremark) {
        this.cCardremark = cCardremark;
    }

    @Basic
    @Column(name = "c_from", nullable = false)
    public int getcFrom() {
        return cFrom;
    }

    public void setcFrom(int cFrom) {
        this.cFrom = cFrom;
    }

    @Basic
    @Column(name = "c_fromorderid", nullable = true, length = 200)
    public String getcFromorderid() {
        return cFromorderid;
    }

    public void setcFromorderid(String cFromorderid) {
        this.cFromorderid = cFromorderid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usercard usercard = (Usercard) o;

        if (cId != usercard.cId) return false;
        if (cIsuse != usercard.cIsuse) return false;
        if (cIdentification != usercard.cIdentification) return false;
        if (cFrom != usercard.cFrom) return false;
        if (cOpenid != null ? !cOpenid.equals(usercard.cOpenid) : usercard.cOpenid != null) return false;
        if (cPhone != null ? !cPhone.equals(usercard.cPhone) : usercard.cPhone != null) return false;
        if (cCardid != null ? !cCardid.equals(usercard.cCardid) : usercard.cCardid != null) return false;
        if (cBegintime != null ? !cBegintime.equals(usercard.cBegintime) : usercard.cBegintime != null) return false;
        if (cExpiretime != null ? !cExpiretime.equals(usercard.cExpiretime) : usercard.cExpiretime != null)
            return false;
        if (cCardname != null ? !cCardname.equals(usercard.cCardname) : usercard.cCardname != null) return false;
        if (cCarddes != null ? !cCarddes.equals(usercard.cCarddes) : usercard.cCarddes != null) return false;
        if (cCardpic != null ? !cCardpic.equals(usercard.cCardpic) : usercard.cCardpic != null) return false;
        if (cCardvalue != null ? !cCardvalue.equals(usercard.cCardvalue) : usercard.cCardvalue != null) return false;
        if (cOrderid != null ? !cOrderid.equals(usercard.cOrderid) : usercard.cOrderid != null) return false;
        if (cUsetime != null ? !cUsetime.equals(usercard.cUsetime) : usercard.cUsetime != null) return false;
        if (cCardremark != null ? !cCardremark.equals(usercard.cCardremark) : usercard.cCardremark != null)
            return false;
        if (cFromorderid != null ? !cFromorderid.equals(usercard.cFromorderid) : usercard.cFromorderid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + (cPhone != null ? cPhone.hashCode() : 0);
        result = 31 * result + (cCardid != null ? cCardid.hashCode() : 0);
        result = 31 * result + (cBegintime != null ? cBegintime.hashCode() : 0);
        result = 31 * result + (cExpiretime != null ? cExpiretime.hashCode() : 0);
        result = 31 * result + cIsuse;
        result = 31 * result + cIdentification;
        result = 31 * result + (cCardname != null ? cCardname.hashCode() : 0);
        result = 31 * result + (cCarddes != null ? cCarddes.hashCode() : 0);
        result = 31 * result + (cCardpic != null ? cCardpic.hashCode() : 0);
        result = 31 * result + (cCardvalue != null ? cCardvalue.hashCode() : 0);
        result = 31 * result + (cOrderid != null ? cOrderid.hashCode() : 0);
        result = 31 * result + (cUsetime != null ? cUsetime.hashCode() : 0);
        result = 31 * result + (cCardremark != null ? cCardremark.hashCode() : 0);
        result = 31 * result + cFrom;
        result = 31 * result + (cFromorderid != null ? cFromorderid.hashCode() : 0);
        return result;
    }
}
