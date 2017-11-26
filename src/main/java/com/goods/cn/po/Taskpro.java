package com.goods.cn.po;


import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_taskpro", schema = "c_goods", catalog = "")
public class Taskpro {
    private int cId;
    private String cPro;
    private int cTaskid;
    private byte cStatus;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private String cOpenid;
    private int   cAppid;

    //c_appid
    @Basic
    @Column(name = "c_appid", nullable = false)
    public int getcAppid() {
        return cAppid;
    }

    public void setcAppid(int cAppid) {
        this.cAppid = cAppid;
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
    @Column(name = "c_pro", nullable = false, length = 1000)
    public String getcPro() {
        return cPro;
    }

    public void setcPro(String cPro) {
        this.cPro = cPro;
    }

    @Basic
    @Column(name = "c_taskid", nullable = false)
    public int getcTaskid() {
        return cTaskid;
    }

    public void setcTaskid(int cTaskid) {
        this.cTaskid = cTaskid;
    }

    @Basic
    @Column(name = "c_status", nullable = false)
    public byte getcStatus() {
        return cStatus;
    }

    public void setcStatus(byte cStatus) {
        this.cStatus = cStatus;
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
    @Column(name = "c_openid", nullable = false, length = 225)
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

        Taskpro taskpro = (Taskpro) o;

        if (cId != taskpro.cId) return false;
        if (cTaskid != taskpro.cTaskid) return false;
        if (cStatus != taskpro.cStatus) return false;
        if (cPro != null ? !cPro.equals(taskpro.cPro) : taskpro.cPro != null) return false;
        if (cTime != null ? !cTime.equals(taskpro.cTime) : taskpro.cTime != null) return false;
        if (cOpenid != null ? !cOpenid.equals(taskpro.cOpenid) : taskpro.cOpenid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cPro != null ? cPro.hashCode() : 0);
        result = 31 * result + cTaskid;
        result = 31 * result + (int) cStatus;
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        return result;
    }
}
