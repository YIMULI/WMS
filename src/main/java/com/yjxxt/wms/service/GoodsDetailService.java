package com.yjxxt.wms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.wms.base.BaseService;
import com.yjxxt.wms.bean.Admin;
import com.yjxxt.wms.bean.GoodsDetails;
import com.yjxxt.wms.dao.GoodsDetailsMapper;
import com.yjxxt.wms.query.GoodsDetaQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoodsDetailService extends BaseService<GoodsDetails,Integer> {

    @Resource
    private GoodsDetailsMapper goodsDetailsMapper;


    public Map<String, Object> queryAllGoodsById(GoodsDetaQuery query) {
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(),query.getLimit());
        PageInfo<GoodsDetails> pageInfo = new PageInfo<>(goodsDetailsMapper.selectByParams(query));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
}
