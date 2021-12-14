package com.yjxxt.wms.controller;

import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.base.ResultInfo;
import com.yjxxt.wms.bean.AddGoods;
//import com.yjxxt.wms.bean.SellGoods;
//import com.yjxxt.wms.dao.AddGoodsMapper;
import com.yjxxt.wms.query.AddGoodsQuery;
import com.yjxxt.wms.service.AddGoodsService;

//import com.yjxxt.wms.service.UserService;
//import com.yjxxt.wms.util.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
//import javax.xml.transform.Result;
//import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("customer_serve1")
public class AddGoodsController extends BaseController {

    @Resource
    private AddGoodsService addGoodsService;

//    @Resource
//    private UserService userService;

    /**
     * 商品管理主界面
     * @return
     */
    @RequestMapping("index")
    public String sayAdd(){
        return "customer/add_goods";
    }

    /**
     * 入库列表和多条件查询
     * @param addGoodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(AddGoodsQuery addGoodsQuery){
        Map<String,Object> map =  addGoodsService.getMap(addGoodsQuery);
        return map;
    }


    /**
     * 添加删除选择接口 + 更新回显
     * @return
     */
    @RequestMapping("addOrUpdateGoodsPage")
    public String toAddOrUpdate(Integer id, Model model){
        if(id!=null){
            AddGoods addGoods = addGoodsService.queryByPrimaryKey(id);//通过id获取对象
            model.addAttribute("adg",addGoods);
        }
        return "customer/addPage";
    }

    /**
     * 入库记录添加
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo toAdd(HttpServletRequest req, AddGoods addGoods){
        addGoodsService.addChance(addGoods);//添加数据
        System.out.println("添加入库记录成功");
        return success("添加入库记录成功");
    }

    /**
     * 入库记录删除
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteSale(Integer[] ids){
        addGoodsService.deleteChance(ids);//批量删除营销记录
        System.out.println("删除入库记录成功");
        return success("删除入库记录成功");
    }


    /**
     * 出库记录更新
     * @param req
     * @param addGoods
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateSale(HttpServletRequest req, AddGoods addGoods){
        addGoodsService.replaceChance(addGoods);//更新数据
        System.out.println("更新成功");
        return success("更新成功");
    }
}
