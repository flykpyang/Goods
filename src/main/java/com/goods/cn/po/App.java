package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_app", schema = "c_goods", catalog = "")
public class App {
    private int cId;
    private String cAppid;
    private String cAppname;
    private String cApppass;
    private String cPic;
    private String cAddress;
    private String cPhone;
    private String cSecretkey;
    private String cPaykey;
    private byte   cIsEnable=(byte)1;
    private String cMchid;
    private String cSoreName;
    private String cSoreImg;
    private String cNotifyOpenid;
    private String cOrcode;

    //c_orcode

    @Basic
    @Column(name = "c_orcode", nullable = true, length = 100)
    public String getcOrcode() {
        return cOrcode;
    }

    public void setcOrcode(String cOrcode) {
        this.cOrcode = cOrcode;
    }

    @Basic
    @Column(name = "c_notifyopenid", nullable = true, length = 50)
    public String getcNotifyOpenid() {
        return cNotifyOpenid;
    }

    public void setcNotifyOpenid(String cNotifyOpenid) {
        this.cNotifyOpenid = cNotifyOpenid;
    }

    //c_soreimg c_notifyopenid
    @Basic
    @Column(name = "c_soreimg", nullable = false, length = 150)
    public String getcSoreImg() {
        return cSoreImg;
    }

    public void setcSoreImg(String cSoreImg) {
        this.cSoreImg = cSoreImg;
    }


    @Basic
    @Column(name = "c_sorename", nullable = false, length = 20)
    public String getcSoreName() {
        return cSoreName;
    }

    public void setcSoreName(String cSoreName) {
        this.cSoreName = cSoreName;
    }

    @Basic
    @Column(name = "c_mchid", nullable = false, length = 10)
    public String getcMchid() {
        return cMchid;
    }

    public void setcMchid(String cMchid) {
        this.cMchid = cMchid;
    }

    @Basic
    @Column(name = "c_enable", nullable = false)
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
    @Column(name = "c_appid", nullable = false,length = 100)
    public String getcAppid() {
        return cAppid;
    }

    public void setcAppid(String cAppid) {
        this.cAppid = cAppid;
    }

    @Basic
    @Column(name = "c_appname", nullable = false, length = 45)
    public String getcAppname() {
        return cAppname;
    }

    public void setcAppname(String cAppname) {
        this.cAppname = cAppname;
    }

    @Basic
    @Column(name = "c_apppass", nullable = false, length = 45)
    public String getcApppass() {
        return cApppass;
    }

    public void setcApppass(String cApppass) {
        this.cApppass = cApppass;
    }

    @Basic
    @Column(name = "c_pic", nullable = true, length = 225)
    public String getcPic() {
        return cPic;
    }

    public void setcPic(String cPic) {
        this.cPic = cPic;
    }

    @Basic
    @Column(name = "c_address", nullable = true, length = 225)
    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    @Basic
    @Column(name = "c_phone", nullable = true, length = 45)
    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    @Basic
    @Column(name = "c_secretkey", nullable = false, length = 100)
    public String getcSecretkey() {
        return cSecretkey;
    }

    public void setcSecretkey(String cSecretkey) {
        this.cSecretkey = cSecretkey;
    }

    @Basic
    @Column(name = "c_paykey", nullable = false, length = 100)
    public String getcPaykey() {
        return cPaykey;
    }

    public void setcPaykey(String cPaykey) {
        this.cPaykey = cPaykey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        App app = (App) o;

        if (cId != app.cId) return false;
        if (cAppid != app.cAppid) return false;
        if (cAppname != null ? !cAppname.equals(app.cAppname) : app.cAppname != null) return false;
        if (cApppass != null ? !cApppass.equals(app.cApppass) : app.cApppass != null) return false;
        if (cPic != null ? !cPic.equals(app.cPic) : app.cPic != null) return false;
        if (cAddress != null ? !cAddress.equals(app.cAddress) : app.cAddress != null) return false;
        if (cPhone != null ? !cPhone.equals(app.cPhone) : app.cPhone != null) return false;
        if (cSecretkey != null ? !cSecretkey.equals(app.cSecretkey) : app.cSecretkey != null) return false;
        if (cPaykey != null ? !cPaykey.equals(app.cPaykey) : app.cPaykey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cAppname != null ? cAppname.hashCode() : 0);
        result = 31 * result + (cApppass != null ? cApppass.hashCode() : 0);
        result = 31 * result + (cPic != null ? cPic.hashCode() : 0);
        result = 31 * result + (cAddress != null ? cAddress.hashCode() : 0);
        result = 31 * result + (cPhone != null ? cPhone.hashCode() : 0);
        result = 31 * result + (cSecretkey != null ? cSecretkey.hashCode() : 0);
        result = 31 * result + (cPaykey != null ? cPaykey.hashCode() : 0);
        return result;
    }
}
