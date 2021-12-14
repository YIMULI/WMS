package com.yjxxt.wms.controller;

import com.yjxxt.wms.annotation.RequirePermission;
import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.query.AdminQuery;
import com.yjxxt.wms.query.GoodsDetaQuery;
import com.yjxxt.wms.service.GoodsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("goods")

public class GoodsDetailController extends BaseController {

    @Resource
    private GoodsDetailService goodsDetailService;

    @RequestMapping("list")
    @ResponseBody
//    @RequirePermission(code = "10")
    public Map<String,Object> list(GoodsDetaQuery query){
        return goodsDetailService.queryAllGoodsById(query);
    }

    @RequestMapping("index")
    public String index(){
        return "goods/goods";
    }

}
