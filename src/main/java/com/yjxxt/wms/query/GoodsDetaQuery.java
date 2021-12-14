package com.yjxxt.wms.query;

import com.yjxxt.wms.base.BaseQuery;

public class GoodsDetaQuery extends BaseQuery {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
