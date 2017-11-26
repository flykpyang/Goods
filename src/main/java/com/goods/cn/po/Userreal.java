package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_userreal", schema = "c_goods", catalog = "")
public class Userreal {
    private int cId;
    private String cOpenid;
    private String cRealid;
    private String cRealname;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private int cIsuse;
    private String cImg;
    private int  cAppid;
    private int  cAddrid;


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
    @Column(name = "c_openid", nullable = false, length = 255)
    public String getcOpenid() {
        return cOpenid;
    }

    public void setcOpenid(String cOpenid) {
        this.cOpenid = cOpenid;
    }

    @Basic
    @Column(name = "c_realid", nullable = false, length = 255)
    public String getcRealid() {
        return cRealid;
    }

    public void setcRealid(String cRealid) {
        this.cRealid = cRealid;
    }

    @Basic
    @Column(name = "c_realname", nullable = false, length = 255)
    public String getcRealname() {
        return cRealname;
    }

    public void setcRealname(String cRealname) {
        this.cRealname = cRealname;
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
    @Column(name = "c_isuse", nullable = false)
    public int getcIsuse() {
        return cIsuse;
    }

    public void setcIsuse(int cIsuse) {
        this.cIsuse = cIsuse;
    }

    @Basic
    @Column(name = "c_img", nullable = true, length = 200)
    public String getcImg() {
        return cImg;
    }

    public void setcImg(String cImg) {
        this.cImg = cImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userreal userreal = (Userreal) o;

        if (cId != userreal.cId) return false;
        if (cIsuse != userreal.cIsuse) return false;
        if (cOpenid != null ? !cOpenid.equals(userreal.cOpenid) : userreal.cOpenid != null) return false;
        if (cRealid != null ? !cRealid.equals(userreal.cRealid) : userreal.cRealid != null) return false;
        if (cRealname != null ? !cRealname.equals(userreal.cRealname) : userreal.cRealname != null) return false;
        if (cTime != null ? !cTime.equals(userreal.cTime) : userreal.cTime != null) return false;
        if (cImg != null ? !cImg.equals(userreal.cImg) : userreal.cImg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + (cRealid != null ? cRealid.hashCode() : 0);
        result = 31 * result + (cRealname != null ? cRealname.hashCode() : 0);
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + cIsuse;
        result = 31 * result + (cImg != null ? cImg.hashCode() : 0);
        return result;
    }
}
