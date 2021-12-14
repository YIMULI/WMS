package com.yjxxt.wms.query;

import com.yjxxt.wms.base.BaseQuery;

public class SellGoodsQuery extends BaseQuery {
    private Integer id;
    private Integer goodsId;
    private Integer userId;

    public SellGoodsQuery(Integer id, Integer goodsId, Integer userId) {
        this.id = id;
        this.goodsId = goodsId;
        this.userId = userId;
    }

    public SellGoodsQuery() {
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
