package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_template", schema = "c_goods", catalog = "")
public class Template {
    private int cId;
    private String cTemplateId;
    private byte cStatus=(byte)0;
    private String cAppid;
    private byte cTemptype=(byte)0;

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
    @Column(name = "c_template_id", nullable = false, length = 225)
    public String getcTemplateId() {
        return cTemplateId;
    }

    public void setcTemplateId(String cTemplateId) {
        this.cTemplateId = cTemplateId;
    }


    @Basic
    @Column(name = "c_status", nullable = false)
    public byte getcStatus() {
        return cStatus;
    }

    public void setcStatus(byte cStatus) {
        this.cStatus = cStatus;
    }

    @Basic
    @Column(name = "c_appid", nullable = false)
    public String getcAppid() {
        return cAppid;
    }

    public void setcAppid(String cAppid) {
        this.cAppid = cAppid;
    }

    @Basic
    @Column(name = "c_temptype", nullable = false)
    public byte getcTemptype() {
        return cTemptype;
    }

    public void setcTemptype(byte cTemptype) {
        this.cTemptype = cTemptype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Template template = (Template) o;

        if (cId != template.cId) return false;
        if (cStatus != template.cStatus) return false;
        if (cAppid != template.cAppid) return false;
        if (cTemptype != template.cTemptype) return false;
        if (cTemplateId != null ? !cTemplateId.equals(template.cTemplateId) : template.cTemplateId != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cTemplateId != null ? cTemplateId.hashCode() : 0);
        result = 31 * result + (int) cStatus;
        result = 31 * result + (int) cTemptype;
        return result;
    }
}
