package com.yjxxt.wms.query;

import com.yjxxt.wms.base.BaseQuery;

public class AddGoodsQuery extends BaseQuery {

    private Integer id;
    private Integer goodsId;
    private Integer adminId;

    public AddGoodsQuery(Integer id, Integer goodsId, Integer adminId) {
        this.id = id;
        this.goodsId = goodsId;
        this.adminId = adminId;
    }

    public AddGoodsQuery() {
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

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
