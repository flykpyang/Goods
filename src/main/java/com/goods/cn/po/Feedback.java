package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;
import com.goods.cn.action.Gift;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_feedback", schema = "c_goods", catalog = "")
public class Feedback {
    private int cId;
    private String cOpenid;
    private String cFeedback;
    private String cName;
    private String cPhone;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private int  cAppid;
    public  int  addsore;
    public HashMap<String, List<Gift>> giftsMap=new HashMap<String, List<Gift>>();

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
    @Column(name = "c_openid", nullable = false, length = 200)
    public String getcOpenid() {
        return cOpenid;
    }

    public void setcOpenid(String cOpenid) {
        this.cOpenid = cOpenid;
    }

    @Basic
    @Column(name = "c_feedback", nullable = false, length = 1000)
    public String getcFeedback() {
        return cFeedback;
    }

    public void setcFeedback(String cFeedback) {
        this.cFeedback = cFeedback;
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
    @Column(name = "c_phone", nullable = false, length = 20)
    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
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

        Feedback feedback = (Feedback) o;

        if (cId != feedback.cId) return false;
        if (cOpenid != null ? !cOpenid.equals(feedback.cOpenid) : feedback.cOpenid != null) return false;
        if (cFeedback != null ? !cFeedback.equals(feedback.cFeedback) : feedback.cFeedback != null) return false;
        if (cName != null ? !cName.equals(feedback.cName) : feedback.cName != null) return false;
        if (cPhone != null ? !cPhone.equals(feedback.cPhone) : feedback.cPhone != null) return false;
        if (cTime != null ? !cTime.equals(feedback.cTime) : feedback.cTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + (cFeedback != null ? cFeedback.hashCode() : 0);
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (cPhone != null ? cPhone.hashCode() : 0);
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        return result;
    }
}
