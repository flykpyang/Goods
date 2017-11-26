package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_task", schema = "c_goods", catalog = "")
public class Task {
    private int cId;
    private int cTaskday;
    private String cGiftlist;
    private int cVip;
    private int cAppid;

    @Basic
    @Column(name = "c_appid", nullable = false)
    public int getcAppid() {
        return cAppid;
    }

    public void setcAppid(int cAppid) {
        this.cAppid = cAppid;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "c_id", nullable = false)
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "c_taskday", nullable = false)
    public int getcTaskday() {
        return cTaskday;
    }

    public void setcTaskday(int cTaskday) {
        this.cTaskday = cTaskday;
    }

    @Basic
    @Column(name = "c_giftlist", nullable = false, length = 1000)
    public String getcGiftlist() {
        return cGiftlist;
    }

    public void setcGiftlist(String cGiftlist) {
        this.cGiftlist = cGiftlist;
    }

    @Basic
    @Column(name = "c_vip", nullable = false)
    public int getcVip() {
        return cVip;
    }

    public void setcVip(int cVip) {
        this.cVip = cVip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (cId != task.cId) return false;
        if (cTaskday != task.cTaskday) return false;
        if (cVip != task.cVip) return false;
        if (cGiftlist != null ? !cGiftlist.equals(task.cGiftlist) : task.cGiftlist != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + cTaskday;
        result = 31 * result + (cGiftlist != null ? cGiftlist.hashCode() : 0);
        result = 31 * result + cVip;
        return result;
    }
}
