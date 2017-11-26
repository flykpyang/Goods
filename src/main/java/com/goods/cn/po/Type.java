package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_type", schema = "c_goods", catalog = "")
public class Type {
    private int cId;
    private String cName;
    private String cDes;
    private String cPic;
    private int    cAppid;
    private int    cOrder;

    @Basic
    @Column(name = "c_order", nullable = false)
    public int getcOrder() {
        return cOrder;
    }

    public void setcOrder(int cOrder) {
        this.cOrder = cOrder;
    }

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
    @Column(name = "c_name", nullable = false, length = 45)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "c_des", nullable = false, length = 45)
    public String getcDes() {
        return cDes;
    }

    public void setcDes(String cDes) {
        this.cDes = cDes;
    }

    @Basic
    @Column(name = "c_pic", nullable = false, length = 225)
    public String getcPic() {
        return cPic;
    }

    public void setcPic(String cPic) {
        this.cPic = cPic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        if (cId != type.cId) return false;
        if (cName != null ? !cName.equals(type.cName) : type.cName != null) return false;
        if (cDes != null ? !cDes.equals(type.cDes) : type.cDes != null) return false;
        if (cPic != null ? !cPic.equals(type.cPic) : type.cPic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (cDes != null ? cDes.hashCode() : 0);
        result = 31 * result + (cPic != null ? cPic.hashCode() : 0);
        return result;
    }
}
