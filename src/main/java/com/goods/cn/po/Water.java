package com.goods.cn.po;



import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_water", schema = "c_goods", catalog = "")
public class Water {
    private int cId;
    private String cOpenid;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private String cGift;
    private int cTaskid;
    private int cAppid;

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
    @Column(name = "c_gift", nullable = false, length = 225)
    public String getcGift() {
        return cGift;
    }

    public void setcGift(String cGift) {
        this.cGift = cGift;
    }

    @Basic
    @Column(name = "c_taskid", nullable = false)
    public int getcTaskid() {
        return cTaskid;
    }

    public void setcTaskid(int cTaskid) {
        this.cTaskid = cTaskid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Water water = (Water) o;

        if (cId != water.cId) return false;
        if (cTaskid != water.cTaskid) return false;
        if (cOpenid != null ? !cOpenid.equals(water.cOpenid) : water.cOpenid != null) return false;
        if (cTime != null ? !cTime.equals(water.cTime) : water.cTime != null) return false;
        if (cGift != null ? !cGift.equals(water.cGift) : water.cGift != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + (cGift != null ? cGift.hashCode() : 0);
        result = 31 * result + cTaskid;
        return result;
    }
}
