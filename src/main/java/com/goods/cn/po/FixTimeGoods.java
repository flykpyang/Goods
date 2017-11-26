package com.goods.cn.po;

public class FixTimeGoods {
    private String goodsId;
    private String beginTime;
    private String endTime;
    private int    count;//数量
    private String    disPrice;//减免价格
    private int    onlyCupNumber;//每个人能购买的数量

    public int getOnlyCupNumber() {
        return onlyCupNumber;
    }

    public void setOnlyCupNumber(int onlyCupNumber) {
        this.onlyCupNumber = onlyCupNumber;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDisPrice() {
        return disPrice;
    }

    public void setDisPrice(String disPrice) {
        this.disPrice = disPrice;
    }

}
