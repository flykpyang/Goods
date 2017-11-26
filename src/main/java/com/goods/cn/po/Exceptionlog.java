package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_exceptionlog", schema = "c_goods", catalog = "")
public class Exceptionlog {
    private int cId;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private String cLog;

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
    @Column(name = "c_time", nullable = false)
    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    @Basic
    @Column(name = "c_log", nullable = false, length = -1)
    public String getcLog() {
        return cLog;
    }

    public void setcLog(String cLog) {
        this.cLog = cLog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exceptionlog that = (Exceptionlog) o;

        if (cId != that.cId) return false;
        if (cTime != null ? !cTime.equals(that.cTime) : that.cTime != null) return false;
        if (cLog != null ? !cLog.equals(that.cLog) : that.cLog != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + (cLog != null ? cLog.hashCode() : 0);
        return result;
    }
}
