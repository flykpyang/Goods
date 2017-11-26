package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author fly
 * @create 2017-11-15 19:26
 **/

@Entity
@Table(name = "t_attachgoods", schema = "c_goods", catalog = "")
public class Attachgoods {
    private int cId;
    private String cName;
    private String cDes;
    private String cPrice="0.00";
    private String cStrategy;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private int cAppid;
    private byte cIsEnable=(byte)1;
    private String cPic;

    //c_pic
    @Basic
    @Column(name = "c_pic", nullable = false, length = 200)
    public String getcPic() {
        return cPic;
    }

    public void setcPic(String cPic) {
        this.cPic = cPic;
    }


    //c_isenable

    @Basic
    @Column(name = "c_isenable", nullable = false)
    public byte getcIsEnable() {
        return cIsEnable;
    }

    public void setcIsEnable(byte cIsEnable) {
        this.cIsEnable = cIsEnable;
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
    @Column(name = "c_name", nullable = false, length = 20)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "c_des", nullable = false, length = 100)
    public String getcDes() {
        return cDes;
    }

    public void setcDes(String cDes) {
        this.cDes = cDes;
    }

    @Basic
    @Column(name = "c_price", nullable = false, length = 20)
    public String getcPrice() {
        return cPrice;
    }

    public void setcPrice(String cPrice) {
        this.cPrice = cPrice;
    }

    @Basic
    @Column(name = "c_strategy", nullable = false, length = 500)
    public String getcStrategy() {
        return cStrategy;
    }

    public void setcStrategy(String cStrategy) {
        this.cStrategy = cStrategy;
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

        Attachgoods that = (Attachgoods) o;

        if(that.cId==cId) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = cId;
        return result;
    }
}
