package com.yjxxt.wms.dao;

import com.yjxxt.wms.base.BaseMapper;
import com.yjxxt.wms.bean.SellGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SellGoodsMapper  extends BaseMapper<SellGoods,Integer> {
    SellGoods queryByPrimaryKey(@Param("id") Integer id);
}