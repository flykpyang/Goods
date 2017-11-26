package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_faredes", schema = "c_goods", catalog = "")
public class Faredes {
    private int cId;
    private String cOneprice;
    private String cAddoneprice;
    private String cContain;
    private int cTempid;

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
    @Column(name = "c_oneprice", nullable = false, length = 10)
    public String getcOneprice() {
        return cOneprice;
    }

    public void setcOneprice(String cOneprice) {
        this.cOneprice = cOneprice;
    }

    @Basic
    @Column(name = "c_addoneprice", nullable = false, length = 10)
    public String getcAddoneprice() {
        return cAddoneprice;
    }

    public void setcAddoneprice(String cAddoneprice) {
        this.cAddoneprice = cAddoneprice;
    }

    @Basic
    @Column(name = "c_contain", nullable = false, length = 500)
    public String getcContain() {
        return cContain;
    }

    public void setcContain(String cContain) {
        this.cContain = cContain;
    }

    @Basic
    @Column(name = "c_tempid", nullable = false)
    public int getcTempid() {
        return cTempid;
    }

    public void setcTempid(int cTempid) {
        this.cTempid = cTempid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faredes faredes = (Faredes) o;

        if (cId != faredes.cId) return false;
        if (cTempid != faredes.cTempid) return false;
        if (cOneprice != null ? !cOneprice.equals(faredes.cOneprice) : faredes.cOneprice != null) return false;
        if (cAddoneprice != null ? !cAddoneprice.equals(faredes.cAddoneprice) : faredes.cAddoneprice != null)
            return false;
        if (cContain != null ? !cContain.equals(faredes.cContain) : faredes.cContain != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cOneprice != null ? cOneprice.hashCode() : 0);
        result = 31 * result + (cAddoneprice != null ? cAddoneprice.hashCode() : 0);
        result = 31 * result + (cContain != null ? cContain.hashCode() : 0);
        result = 31 * result + cTempid;
        return result;
    }
}
