package com.goods.cn.po;

import javax.persistence.*;

@Entity
@Table(name = "t_sharedic", schema = "c_goods", catalog = "")
public class Sharedic {
    private int cId;
    private String cAction;
    private String cDes;
    private String cName;

    @Id
    @Column(name = "c_id", nullable = false)
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
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
    @Column(name = "c_des", nullable = false, length = 20)
    public String getcDes() {
        return cDes;
    }

    public void setcDes(String cDes) {
        this.cDes = cDes;
    }

    @Basic
    @Column(name = "c_name", nullable = false, length = 10)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sharedic tSharedic = (Sharedic) o;

        if (cId != tSharedic.cId) return false;
        if (cAction != null ? !cAction.equals(tSharedic.cAction) : tSharedic.cAction != null) return false;
        if (cDes != null ? !cDes.equals(tSharedic.cDes) : tSharedic.cDes != null) return false;
        if (cName != null ? !cName.equals(tSharedic.cName) : tSharedic.cName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cAction != null ? cAction.hashCode() : 0);
        result = 31 * result + (cDes != null ? cDes.hashCode() : 0);
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        return result;
    }
}
