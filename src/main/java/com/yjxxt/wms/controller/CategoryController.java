package com.yjxxt.wms.controller;

import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.base.ResultInfo;
import com.yjxxt.wms.bean.Category;
import com.yjxxt.wms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("category")
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;


    @RequestMapping("index")
    public String index(){
    return "category/category";
}


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> categroyList(){
        return categoryService.categoryList();
    }

    // 添加资源页视图转发
    @RequestMapping("addModulePage")
    public String addModulePage(Integer grade, Integer parentId, Model model){
        model.addAttribute("grade",grade);
        model.addAttribute("parentId",  parentId);
        return "category/add";
    }
    // 更新资源页视图转发
    @RequestMapping("updateModulePage")
    public String updateModulePage(Integer categoryId,Model model){

        model.addAttribute("category",categoryService.selectByPrimaryKey(categoryId));
        return "category/update";
    }
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveCategory(Category category){
        categoryService.saveCategory(category);
        return success("菜单添加成功");
    }
    @RequestMapping("queryAllModulesByGrade")
    @ResponseBody
    public List<Map<String,Object>> queryAllModulesByGrade(Integer grade){
        return categoryService.queryAllModulesByGrade(grade);
    }
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCategory(Category category){
        System.out.println("aaaaa"+category);
        categoryService.updateCategory(category);
        return success("菜单更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCategory(Integer id){
        categoryService.deleteCategoryById(id);
        return success("菜单删除成功");
    }


}
