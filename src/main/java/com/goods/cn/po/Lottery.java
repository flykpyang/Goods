package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_lottery", schema = "c_goods", catalog = "")
public class
Lottery {
    private int cId;
    private Date cBeginday= TimeUtil.getCurrDay(0);
    private Date cEndday= TimeUtil.getCurrDay(0);
    private String cBegintime="00:00";
    private String cEndtime="00:00";
    private byte cStatus=(byte)0;
    private String cPrizeid;
    private int    cAppid;
    public long bc=1l;
    public List<Realprize> realprizes;
    public int leftPrizenumber;

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
    @Column(name = "c_beginday", nullable = false)
    public Date getcBeginday() {
        return cBeginday;
    }

    public void setcBeginday(Date cBeginday) {
        this.cBeginday = cBeginday;
    }

    @Basic
    @Column(name = "c_endday", nullable = false)
    public Date getcEndday() {
        return cEndday;
    }

    public void setcEndday(Date cEndday) {
        this.cEndday = cEndday;
    }

    @Basic
    @Column(name = "c_begintime", nullable = false, length = 20)
    public String getcBegintime() {
        return cBegintime;
    }

    public void setcBegintime(String cBegintime) {
        this.cBegintime = cBegintime;
    }

    @Basic
    @Column(name = "c_endtime", nullable = false, length = 20)
    public String getcEndtime() {
        return cEndtime;
    }

    public void setcEndtime(String cEndtime) {
        this.cEndtime = cEndtime;
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
    @Column(name = "c_prizeid", nullable = false, length = 100)
    public String getcPrizeid() {
        return cPrizeid;
    }

    public void setcPrizeid(String cPrizeid) {
        this.cPrizeid = cPrizeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lottery lottery = (Lottery) o;

        if (cId != lottery.cId) return false;
        if (cStatus != lottery.cStatus) return false;
        if (cBeginday != null ? !cBeginday.equals(lottery.cBeginday) : lottery.cBeginday != null) return false;
        if (cEndday != null ? !cEndday.equals(lottery.cEndday) : lottery.cEndday != null) return false;
        if (cBegintime != null ? !cBegintime.equals(lottery.cBegintime) : lottery.cBegintime != null) return false;
        if (cEndtime != null ? !cEndtime.equals(lottery.cEndtime) : lottery.cEndtime != null) return false;
        if (cPrizeid != null ? !cPrizeid.equals(lottery.cPrizeid) : lottery.cPrizeid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cBeginday != null ? cBeginday.hashCode() : 0);
        result = 31 * result + (cEndday != null ? cEndday.hashCode() : 0);
        result = 31 * result + (cBegintime != null ? cBegintime.hashCode() : 0);
        result = 31 * result + (cEndtime != null ? cEndtime.hashCode() : 0);
        result = 31 * result + (int) cStatus;
        result = 31 * result + (cPrizeid != null ? cPrizeid.hashCode() : 0);
        return result;
    }
}
