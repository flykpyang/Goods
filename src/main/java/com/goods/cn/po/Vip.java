package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_vip", schema = "c_goods", catalog = "")
public class Vip {
    private int cId;
    private String cDiscount;
    private String cName;
    private String cDes;
    private String cPic;
    private String cRemark;
    private int cRank;
    private int cAppid;

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
    @Column(name = "c_discount", nullable = false, length = 45)
    public String getcDiscount() {
        return cDiscount;
    }

    public void setcDiscount(String cDiscount) {
        this.cDiscount = cDiscount;
    }

    @Basic
    @Column(name = "c_name", nullable = false, length = 45)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "c_des", nullable = true, length = 45)
    public String getcDes() {
        return cDes;
    }

    public void setcDes(String cDes) {
        this.cDes = cDes;
    }

    @Basic
    @Column(name = "c_pic", nullable = true, length = 200)
    public String getcPic() {
        return cPic;
    }

    public void setcPic(String cPic) {
        this.cPic = cPic;
    }

    @Basic
    @Column(name = "c_remark", nullable = true, length = 200)
    public String getcRemark() {
        return cRemark;
    }

    public void setcRemark(String cRemark) {
        this.cRemark = cRemark;
    }

    @Basic
    @Column(name = "c_rank", nullable = false)
    public int getcRank() {
        return cRank;
    }

    public void setcRank(int cRank) {
        this.cRank = cRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vip vip = (Vip) o;

        if (cId != vip.cId) return false;
        if (cRank != vip.cRank) return false;
        if (cDiscount != null ? !cDiscount.equals(vip.cDiscount) : vip.cDiscount != null) return false;
        if (cName != null ? !cName.equals(vip.cName) : vip.cName != null) return false;
        if (cDes != null ? !cDes.equals(vip.cDes) : vip.cDes != null) return false;
        if (cPic != null ? !cPic.equals(vip.cPic) : vip.cPic != null) return false;
        if (cRemark != null ? !cRemark.equals(vip.cRemark) : vip.cRemark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cDiscount != null ? cDiscount.hashCode() : 0);
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (cDes != null ? cDes.hashCode() : 0);
        result = 31 * result + (cPic != null ? cPic.hashCode() : 0);
        result = 31 * result + (cRemark != null ? cRemark.hashCode() : 0);
        result = 31 * result + cRank;
        return result;
    }
}
