package com.goods.cn.po;

import com.fly.cn.util.TimeUtil;
import com.goods.cn.action.Gift;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_user_info", schema = "c_goods", catalog = "")
public class UserInfo {
    private int cId;
    private int cAppid;
    private String cOpenid;
    private String cName;
    private String cPhone;
    private String cBirthday;
    private int cSore;
    private int cVid;
    private int cGrow;
    private Date cCtime= TimeUtil.getCurrTimeHm();
    private String cFav;
    private String cMonetary="0.00";
    private String cSex;
    private Date cVtime=TimeUtil.getCurrTimeHm();
    private String cLastVmonetary="0.00";
    private int    cLastCount=0;

    @Basic
    @Column(name = "c_lastvmonetary", nullable = false, length = 50)
    public String getcLastVmonetary() {
        return cLastVmonetary;
    }

    public void setcLastVmonetary(String cLastVmonetary) {
        this.cLastVmonetary = cLastVmonetary;
    }

    @Basic
    @Column(name = "c_lastvcount", nullable = false)
    public int getcLastCount() {
        return cLastCount;
    }

    public void setcLastCount(int cLastCount) {
        this.cLastCount = cLastCount;
    }

    public HashMap<String, List<Gift>> giftsMap=new HashMap<String, List<Gift>>();
    public int addsore;
    public long cousumeCount;


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
    @Column(name = "c_appid", nullable = false)
    public int getcAppid() {
        return cAppid;
    }

    public void setcAppid(int cAppid) {
        this.cAppid = cAppid;
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
    @Column(name = "c_name", nullable = false, length = 255)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "c_phone", nullable = false, length = 45)
    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    @Basic
    @Column(name = "c_birthday", nullable = true, length = 45)
    public String getcBirthday() {
        return cBirthday;
    }

    public void setcBirthday(String cBirthday) {
        this.cBirthday = cBirthday;
    }

    @Basic
    @Column(name = "c_sore", nullable = false)
    public int getcSore() {
        return cSore;
    }

    public void setcSore(int cSore) {
        this.cSore = cSore;
    }

    @Basic
    @Column(name = "c_vid", nullable = false)
    public int getcVid() {
        return cVid;
    }

    public void setcVid(int cVid) {
        this.cVid = cVid;
    }

    @Basic
    @Column(name = "c_grow", nullable = false)
    public int getcGrow() {
        return cGrow;
    }

    public void setcGrow(int cGrow) {
        this.cGrow = cGrow;
    }

    @Basic
    @Column(name = "c_ctime", nullable = false)
    public Date getcCtime() {
        return cCtime;
    }

    public void setcCtime(Date cCtime) {
        this.cCtime = cCtime;
    }

    @Basic
    @Column(name = "c_fav", nullable = true, length = -1)
    public String getcFav() {
        return cFav;
    }

    public void setcFav(String cFav) {
        this.cFav = cFav;
    }

    @Basic
    @Column(name = "c_monetary", nullable = false, length = 45)
    public String getcMonetary() {
        return cMonetary;
    }

    public void setcMonetary(String cMonetary) {
        this.cMonetary = cMonetary;
    }

    @Basic
    @Column(name = "c_sex", nullable = true, length = 2)
    public String getcSex() {
        return cSex;
    }

    public void setcSex(String cSex) {
        this.cSex = cSex;
    }

    @Basic
    @Column(name = "c_vtime", nullable = false)
    public Date getcVtime() {
        return cVtime;
    }

    public void setcVtime(Date cVtime) {
        this.cVtime = cVtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (cId != userInfo.cId) return false;
        if (cAppid != userInfo.cAppid) return false;
        if (cSore != userInfo.cSore) return false;
        if (cVid != userInfo.cVid) return false;
        if (cGrow != userInfo.cGrow) return false;
        if (cOpenid != null ? !cOpenid.equals(userInfo.cOpenid) : userInfo.cOpenid != null) return false;
        if (cName != null ? !cName.equals(userInfo.cName) : userInfo.cName != null) return false;
        if (cPhone != null ? !cPhone.equals(userInfo.cPhone) : userInfo.cPhone != null) return false;
        if (cBirthday != null ? !cBirthday.equals(userInfo.cBirthday) : userInfo.cBirthday != null) return false;
        if (cCtime != null ? !cCtime.equals(userInfo.cCtime) : userInfo.cCtime != null) return false;
        if (cFav != null ? !cFav.equals(userInfo.cFav) : userInfo.cFav != null) return false;
        if (cMonetary != null ? !cMonetary.equals(userInfo.cMonetary) : userInfo.cMonetary != null) return false;
        if (cSex != null ? !cSex.equals(userInfo.cSex) : userInfo.cSex != null) return false;
        if (cVtime != null ? !cVtime.equals(userInfo.cVtime) : userInfo.cVtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + cAppid;
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (cPhone != null ? cPhone.hashCode() : 0);
        result = 31 * result + (cBirthday != null ? cBirthday.hashCode() : 0);
        result = 31 * result + cSore;
        result = 31 * result + cVid;
        result = 31 * result + cGrow;
        result = 31 * result + (cCtime != null ? cCtime.hashCode() : 0);
        result = 31 * result + (cFav != null ? cFav.hashCode() : 0);
        result = 31 * result + (cMonetary != null ? cMonetary.hashCode() : 0);
        result = 31 * result + (cSex != null ? cSex.hashCode() : 0);
        result = 31 * result + (cVtime != null ? cVtime.hashCode() : 0);
        return result;
    }
}
