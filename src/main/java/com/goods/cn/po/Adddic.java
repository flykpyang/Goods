package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_adddic", schema = "c_goods", catalog = "")
public class Adddic {
    private int cId;
    private String cCode;
    private String cName;

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
    @Column(name = "c_code", nullable = false, length = 10)
    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
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

        Adddic adddic = (Adddic) o;

        if (cId != adddic.cId) return false;
        if (cName != null ? !cName.equals(adddic.cName) : adddic.cName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cName != null ? cName.hashCode() : 0);
        return result;
    }
}
