package com.goods.cn.po;

import javax.persistence.*;

@Entity
@Table(name = "t_realgoods", schema = "c_goods", catalog = "")
public class Realgoods {
    private int cId;
    private String cName;
    private String cDes;
    private Integer cSore;
    private Byte cChange;
    private String cPic;
    private int    cAppid;

    @Basic
    @Column(name = "c_appid", nullable = false)
    public int getcAppid() {
        return cAppid;
    }

    public void setcAppid(int cAppid) {
        this.cAppid = cAppid;
    }

    @Id
    @Column(name = "c_id", nullable = false)
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "c_name", nullable = true, length = 45)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "c_des", nullable = false, length = 225)
    public String getcDes() {
        return cDes;
    }

    public void setcDes(String cDes) {
        this.cDes = cDes;
    }

    @Basic
    @Column(name = "c_sore", nullable = true)
    public Integer getcSore() {
        return cSore;
    }

    public void setcSore(Integer cSore) {
        this.cSore = cSore;
    }

    @Basic
    @Column(name = "c_change", nullable = true)
    public Byte getcChange() {
        return cChange;
    }

    public void setcChange(Byte cChange) {
        this.cChange = cChange;
    }

    @Basic
    @Column(name = "c_pic", nullable = false, length = 300)
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

        Realgoods realgoods = (Realgoods) o;

        if (cId != realgoods.cId) return false;
        if (cName != null ? !cName.equals(realgoods.cName) : realgoods.cName != null) return false;
        if (cDes != null ? !cDes.equals(realgoods.cDes) : realgoods.cDes != null) return false;
        if (cSore != null ? !cSore.equals(realgoods.cSore) : realgoods.cSore != null) return false;
        if (cChange != null ? !cChange.equals(realgoods.cChange) : realgoods.cChange != null) return false;
        if (cPic != null ? !cPic.equals(realgoods.cPic) : realgoods.cPic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        result = 31 * result + (cDes != null ? cDes.hashCode() : 0);
        result = 31 * result + (cSore != null ? cSore.hashCode() : 0);
        result = 31 * result + (cChange != null ? cChange.hashCode() : 0);
        result = 31 * result + (cPic != null ? cPic.hashCode() : 0);
        return result;
    }
}
