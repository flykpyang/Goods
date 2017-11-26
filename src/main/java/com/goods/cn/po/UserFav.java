package com.goods.cn.po;

/**
 * Created by fly on 17/4/12.
 */
public class UserFav {

    //一定要LONG 类型 要不然Criteria 会报错
    private Long total;
    private String c_goodsid;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getC_goodsid() {
        return c_goodsid;
    }

    public void setC_goodsid(String c_goodsid) {
        this.c_goodsid = c_goodsid;
    }
}
