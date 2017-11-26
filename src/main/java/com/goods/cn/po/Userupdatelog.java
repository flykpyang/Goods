package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_userupdatelog", schema = "c_goods", catalog = "")
public class Userupdatelog {
    private int cId;
    private String cOld;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private String cNew;
    private byte cIsaction=(byte)0;
    private String cOpenid;
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
    @Column(name = "c_old", nullable = false, length = 500)
    public String getcOld() {
        return cOld;
    }

    public void setcOld(String cOld) {
        this.cOld = cOld;
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
    @Column(name = "c_new", nullable = false, length = 500)
    public String getcNew() {
        return cNew;
    }

    public void setcNew(String cNew) {
        this.cNew = cNew;
    }

    @Basic
    @Column(name = "c_isaction", nullable = false)
    public byte getcIsaction() {
        return cIsaction;
    }

    public void setcIsaction(byte cIsaction) {
        this.cIsaction = cIsaction;
    }

    @Basic
    @Column(name = "c_openid", nullable = false, length = 200)
    public String getcOpenid() {
        return cOpenid;
    }

    public void setcOpenid(String cOpenid) {
        this.cOpenid = cOpenid;
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

        Userupdatelog that = (Userupdatelog) o;

        if (cId != that.cId) return false;
        if (cIsaction != that.cIsaction) return false;
        if (cAppid != that.cAppid) return false;
        if (cOld != null ? !cOld.equals(that.cOld) : that.cOld != null) return false;
        if (cTime != null ? !cTime.equals(that.cTime) : that.cTime != null) return false;
        if (cNew != null ? !cNew.equals(that.cNew) : that.cNew != null) return false;
        if (cOpenid != null ? !cOpenid.equals(that.cOpenid) : that.cOpenid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cOld != null ? cOld.hashCode() : 0);
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + (cNew != null ? cNew.hashCode() : 0);
        result = 31 * result + (int) cIsaction;
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + cAppid;
        return result;
    }
}
