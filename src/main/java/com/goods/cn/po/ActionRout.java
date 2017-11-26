package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_action_rout", schema = "c_goods", catalog = "")
public class ActionRout {
    private int id;
    private String cOpenid;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private String cRout;
    private int cAppid;

    @javax.persistence.Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "c_openid", nullable = false, length = 225)
    public String getcOpenid() {
        return cOpenid;
    }

    public void setcOpenid(String cOpenid) {
        this.cOpenid = cOpenid;
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
    @Column(name = "c_rout", nullable = false, length = 1000)
    public String getcRout() {
        return cRout;
    }

    public void setcRout(String cRout) {
        this.cRout = cRout;
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

        ActionRout that = (ActionRout) o;

        if (id != that.id) return false;
        if (cAppid != that.cAppid) return false;
        if (cOpenid != null ? !cOpenid.equals(that.cOpenid) : that.cOpenid != null) return false;
        if (cTime != null ? !cTime.equals(that.cTime) : that.cTime != null) return false;
        if (cRout != null ? !cRout.equals(that.cRout) : that.cRout != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + (cRout != null ? cRout.hashCode() : 0);
        result = 31 * result + cAppid;
        return result;
    }
}
