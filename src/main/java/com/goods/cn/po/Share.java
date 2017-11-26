package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "t_share", schema = "c_goods", catalog = "")
public class Share {
    private int cId;
    private int cAppid;
    private String cDes;
    private String cName;
    private String cImg;
    private String cAction;
    private Date cTime= TimeUtil.getCurrTimeHm();

    @Id
    @Column(name = "c_id", nullable = false)
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "c_appid", nullable = false)
    public int getcAppid() {
        return cAppid;
    }

    public void setcAppid(int cAppid) {
        this.cAppid = cAppid;
    }

    @Basic
    @Column(name = "c_des", nullable = true, length = 100)
    public String getcDes() {
        return cDes;
    }

    public void setcDes(String cDes) {
        this.cDes = cDes;
    }

    @Basic
    @Column(name = "c_name", nullable = false, length = 20)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "c_img", nullable = false, length = 100)
    public String getcImg() {
        return cImg;
    }

    public void setcImg(String cImg) {
        this.cImg = cImg;
    }

    @Basic
    @Column(name = "c_action", nullable = false, length = 10)
    public String getcAction() {
        return cAction;
    }

    public void setcAction(String cAction) {
        this.cAction = cAction;
    }

    @Basic
    @Column(name = "c_time", nullable = false)
    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Share share = (Share) o;

        if (cId != share.cId) return false;
        if (cAppid != share.cAppid) return false;
        if (cDes != null ? !cDes.equals(share.cDes) : share.cDes != null) return false;
        if (cName != null ? !cName.equals(share.cName) : share.cName != null) return false;
        if (cImg != null ? !cImg.equals(share.cImg) : share.cImg != null) return false;
        if (cAction != null ? !cAction.equals(share.cAction) : share.cAction != null) return false;
        if (cTime != null ? !cTime.equals(share.cTime) : share.cTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + cAppid;
        result = 31 * result + (cDes != null ? cDes.hashCode() : 0);
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (cImg != null ? cImg.hashCode() : 0);
        result = 31 * result + (cAction != null ? cAction.hashCode() : 0);
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        return result;
    }
}
