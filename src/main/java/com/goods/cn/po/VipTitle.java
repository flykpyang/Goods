package com.goods.cn.po;

public class VipTitle {
    private String price;
    private int    buycount;
    private int    rank;
    private int    balanceMonth;
    private String balancePrice;
    private int    balanceBuyCount;
    private String sendRatio;

    public String getSendRatio() {
        return sendRatio;
    }

    public void setSendRatio(String sendRatio) {
        this.sendRatio = sendRatio;
    }

    public int getBalanceMonth() {
        return balanceMonth;
    }

    public void setBalanceMonth(int balanceMonth) {
        this.balanceMonth = balanceMonth;
    }

    public String getBalancePrice() {
        return balancePrice;
    }

    public void setBalancePrice(String balancePrice) {
        this.balancePrice = balancePrice;
    }

    public int getBalanceBuyCount() {
        return balanceBuyCount;
    }

    public void setBalanceBuyCount(int balanceBuyCount) {
        this.balanceBuyCount = balanceBuyCount;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getBuycount() {
        return buycount;
    }

    public void setBuycount(int buycount) {
        this.buycount = buycount;
    }


    @Override
    public int hashCode() {
        return String.valueOf(rank).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null){
            int rank=((VipTitle)obj).getRank();
            if(String.valueOf(rank).equals(String.valueOf(rank))){
                return true;
            }
        }
        return false;
    }
}
