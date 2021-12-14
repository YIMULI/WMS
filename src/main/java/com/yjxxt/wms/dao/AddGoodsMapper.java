package com.yjxxt.wms.dao;

import com.yjxxt.wms.base.BaseMapper;
import com.yjxxt.wms.bean.AddGoods;
import com.yjxxt.wms.bean.SellGoods;
import com.yjxxt.wms.query.AddGoodsQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AddGoodsMapper extends BaseMapper<AddGoods,Integer> {

    AddGoods queryByPrimaryKey(@Param("id") Integer id);
}