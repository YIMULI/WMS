package com.yjxxt.wms.controller;

import com.yjxxt.wms.annotation.RequirePermission;
import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.base.ResultInfo;
import com.yjxxt.wms.bean.AddGoods;
import com.yjxxt.wms.bean.SellGoods;
import com.yjxxt.wms.query.AddGoodsQuery;
import com.yjxxt.wms.query.SellGoodsQuery;
import com.yjxxt.wms.service.SellGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("customer_serve2")
public class SellGoodsController extends BaseController {

    @Resource
    private SellGoodsService sellGoodsService;

    @RequestMapping("index")
    public String sayPage(){
        return "customer/sell_goods";
    }

    /**
     * 出库列表和多条件查询
     * @param sellGoodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @RequirePermission(code = "30")
    public Map<String,Object> sayList(SellGoodsQuery sellGoodsQuery){
        Map<String,Object> map =  sellGoodsService.getMap(sellGoodsQuery);
        return map;
    }


    /**
     * 添加删除选择接口
     * @return
     */
    @RequestMapping("addOrUpdateGoodsPage")
    public String toAddOrUpdate(Integer id, Model model){
        if(id!=null){
            SellGoods sellGoods = sellGoodsService.queryByPrimaryKey(id);//通过id获取对象
            model.addAttribute("seg",sellGoods);
        }
        return "customer/sell_addPage";
    }

    /**
     * 出库记录添加
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo toAdd(HttpServletRequest req, SellGoods sellGoods){
        sellGoodsService.addChance(sellGoods);//添加数据
        System.out.println("添加入库记录成功");
        return success("添加入库记录成功");
    }

    /**
     * 出库记录删除
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteSale(Integer[] ids){
        sellGoodsService.deleteChance(ids);//批量删除营销记录
        System.out.println("删除入库记录成功");
        return success("删除入库记录成功");
    }


    /**
     * 出库记录更新
     * @param req
     * @param sellGoods
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateSale(HttpServletRequest req,SellGoods sellGoods){
        System.out.println(sellGoods);
        sellGoodsService.replaceChance(sellGoods);//更新数据
        System.out.println("更新成功");
        return success("更新成功");
    }
}
