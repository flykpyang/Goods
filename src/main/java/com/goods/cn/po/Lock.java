package com.goods.cn.po;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_lock", schema = "c_goods", catalog = "")
public class Lock {
    private int cId;
    private String cLockname;
    private int cCount;

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
    @Column(name = "c_lockname", nullable = false, length = 45)
    public String getcLockname() {
        return cLockname;
    }

    public void setcLockname(String cLockname) {
        this.cLockname = cLockname;
    }

    @Basic
    @Column(name = "c_count", nullable = false)
    public int getcCount() {
        return cCount;
    }

    public void setcCount(int cCount) {
        this.cCount = cCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lock lock = (Lock) o;

        if (cId != lock.cId) return false;
        if (cCount != lock.cCount) return false;
        if (cLockname != null ? !cLockname.equals(lock.cLockname) : lock.cLockname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cId;
        result = 31 * result + (cLockname != null ? cLockname.hashCode() : 0);
        result = 31 * result + cCount;
        return result;
    }
}
