package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_faretemplate", schema = "c_goods", catalog = "")
public class Faretemplate {
    private int cId;
    private String cFareName;
    private String cDes;
    private byte cEnable = (byte) 1;
    private String cDefaultprice="0.00";
    private String cDefaultaddprice="0.00";
    private int    cAppid;

    @Basic
    @Column(name = "c_appid", nullable = false)
    public int getcAppid() {
        return cAppid;
    }

    public void setcAppid(int cAppid) {
        this.cAppid = cAppid;
    }

    @Basic
    @Column(name = "c_defaultprice", nullable = true, length = 20)
    public String getcDefaultprice() {
        return cDefaultprice;
    }

    public void setcDefaultprice(String cDefaultprice) {
        this.cDefaultprice = cDefaultprice;
    }

    @Basic
    @Column(name = "c_defaultaddprice", nullable = true, length = 20)
    public String getcDefaultaddprice() {
        return cDefaultaddprice;
    }

    public void setcDefaultaddprice(String cDefaultaddprice) {
        this.cDefaultaddprice = cDefaultaddprice;
    }

    @Basic
    @Column(name = "c_des", nullable = true, length = 20)
    public String getcDes() {
        return cDes;
    }

    public void setcDes(String cDes) {
        this.cDes = cDes;
    }


    @Basic
    @Column(name = "c_enable", nullable = false)
    public byte getcEnable() {
        return cEnable;
    }

    public void setcEnable(byte cEnable) {
        this.cEnable = cEnable;
    }

    @Basic
    @Column(name = "c_farename", nullable = false, length = 10)
    public String getcFareName() {
        return cFareName;
    }

    public void setcFareName(String cFareName) {
        this.cFareName = cFareName;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faretemplate that = (Faretemplate) o;

        if (cId != that.cId) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        return result;
    }
}
