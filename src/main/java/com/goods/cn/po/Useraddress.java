package com.goods.cn.po;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.util.TimeUtil;
import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_useraddress", schema = "c_goods", catalog = "")
public class Useraddress {
    private int cId;
    private String cOpenid;
    private int cAppid;
    private String cAddressname;
    private String cMakeing;
    private Byte cEnable=(byte)1;
    private Date cTime= TimeUtil.getCurrTimeHm();
    private String  cProvince;
    private Date cLastUse=TimeUtil.getCurrTimeHm();
    private String cLinkName;
    private String cLinkPhone;
    private String cCode;
    private String cOther;

    @Basic
    @Column(name = "c_other", nullable = false, length = 10)
    public String getcOther() {
        return cOther;
    }

    public void setcOther(String cOther) {
        this.cOther = cOther;
    }

    @Basic
    @Column(name = "c_linkname", nullable = false, length = 10)
    public String getcLinkName() {
        return cLinkName;
    }

    public void setcLinkName(String cLinkName) {
        this.cLinkName = cLinkName;
    }

    @Basic
    @Column(name = "c_linkphone", nullable = false, length = 15)
    public String getcLinkPhone() {
        return cLinkPhone;
    }

    public void setcLinkPhone(String cLinkPhone) {
        this.cLinkPhone = cLinkPhone;
    }

    @Basic
    @Column(name = "c_code", nullable = true, length = 15)
    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    @Basic
    @Column(name = "c_province", nullable = false)
    public String getcProvince() {
        return cProvince;
    }

    public void setcProvince(String cProvince) {
        this.cProvince = cProvince;
    }

    @Basic
    @Column(name = "c_lastuse", nullable = false)
    public Date getcLastUse() {
        return cLastUse;
    }

    public void setcLastUse(Date cLastUse) {
        this.cLastUse = cLastUse;
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
    @Column(name = "c_openid", nullable = false, length = 100)
    public String getcOpenid() {
        return cOpenid;
    }

    public void setcOpenid(String cOpenid) {
        this.cOpenid = cOpenid;
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
    @Column(name = "c_addressname", nullable = false, length = 1000)
    public String getcAddressname() {
        return cAddressname;
    }

    public void setcAddressname(String cAddressname) {
        this.cAddressname = cAddressname;
    }

    @Basic
    @Column(name = "c_makeing", nullable = true, length = 10)
    public String getcMakeing() {
        return cMakeing;
    }

    public void setcMakeing(String cMakeing) {
        this.cMakeing = cMakeing;
    }

    @Basic
    @Column(name = "c_enable", nullable = true)
    public Byte getcEnable() {
        return cEnable;
    }

    public void setcEnable(Byte cEnable) {
        this.cEnable = cEnable;
    }

    @Basic
    @Column(name = "c_time", nullable = false)
    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Useraddress that = (Useraddress) o;

        if (cId != that.cId) return false;
        if (cAppid != that.cAppid) return false;
        if (cOpenid != null ? !cOpenid.equals(that.cOpenid) : that.cOpenid != null) return false;
        if (cAddressname != null ? !cAddressname.equals(that.cAddressname) : that.cAddressname != null) return false;
        if (cMakeing != null ? !cMakeing.equals(that.cMakeing) : that.cMakeing != null) return false;
        if (cEnable != null ? !cEnable.equals(that.cEnable) : that.cEnable != null) return false;
        if (cTime != null ? !cTime.equals(that.cTime) : that.cTime != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cOpenid != null ? cOpenid.hashCode() : 0);
        result = 31 * result + cAppid;
        result = 31 * result + (cAddressname != null ? cAddressname.hashCode() : 0);
        result = 31 * result + (cMakeing != null ? cMakeing.hashCode() : 0);
        result = 31 * result + (cEnable != null ? cEnable.hashCode() : 0);
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        return result;
    }

    public static void main(String[] agrs){
        Useraddress useraddress=new Useraddress();
        useraddress.setcAppid(1);
        useraddress.setcProvince("1");
        useraddress.setcCode("11111111");
        useraddress.setcLinkName("杨开鹏");
        useraddress.setcEnable((byte)1);
        useraddress.setcLinkPhone("18682089885");
        useraddress.setcAddressname("湖南省汉寿县人民医院");
        useraddress.setcOpenid("jwjewkeweweweweweweweds");
        useraddress.setcMakeing("老家");
        String s= JSONObject.toJSONString(useraddress);
        System.out.println(s);
    }
}
