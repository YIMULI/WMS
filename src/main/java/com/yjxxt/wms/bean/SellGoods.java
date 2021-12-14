package com.yjxxt.wms.bean;

import java.util.Date;

public class SellGoods {
    private Integer id;

    private Integer goodsId;

    private Integer sellNum;

    private String sellAddress;

    private Integer userId;

    private Date sellDate;

    private Integer isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSellNum() {
        return sellNum;
    }

    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    public String getSellAddress() {
        return sellAddress;
    }

    public void setSellAddress(String sellAddress) {
        this.sellAddress = sellAddress == null ? null : sellAddress.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "SellGoods{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", sellNum=" + sellNum +
                ", sellAddress='" + sellAddress + '\'' +
                ", userId=" + userId +
                ", sellDate=" + sellDate +
                ", isValid=" + isValid +
                '}';
    }
}