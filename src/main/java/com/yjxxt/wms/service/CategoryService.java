package com.yjxxt.wms.service;

import com.yjxxt.wms.base.BaseService;
import com.yjxxt.wms.bean.Category;
import com.yjxxt.wms.dao.CategoryMapper;
import com.yjxxt.wms.dto.TreeDto;
import com.yjxxt.wms.util.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService extends BaseService<Category,Integer> {

    @Resource
    private CategoryMapper categoryMapper;


    public List<TreeDto> findModulesByRoleId(Integer roleId) {
        //获取所有资源信息
        List<TreeDto> tlist = categoryMapper.selectModules();

        return tlist;
    }


    public Map<String, Object> categoryList() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Category> category = categoryMapper.queryCategory();
        result.put("count", category.size());
        result.put("data", category);
        result.put("code", 0);
        result.put("msg", "success");
        return result;
    }

    /**
     * 添加
     **/
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCategory(Category category) {
        System.out.println("18272"+category);
        AssertUtil.isTrue(StringUtils.isBlank(category.getCategoryName()), "请输入菜单名!");
        Integer grade = category.getGrade();
//        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "菜单层级不合法!");
        AssertUtil.isTrue(null !=categoryMapper.queryModuleByGradeAndModuleName(category.getGrade(),category.getCategoryName()),"该层级下菜单重复!");
        category.setIsValid((byte) 1);
//        category.setGrade(0);
        AssertUtil.isTrue(insertSelective(category) < 1, "菜单添加失败!");
    }



    /**
     * 修改
     * **/
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCategory(Category category){
        System.out.println(category);
        AssertUtil.isTrue(null == category.getCategoryId() || null== selectByPrimaryKey(category.getCategoryId()),"待更新记录不存在!");

        AssertUtil.isTrue(StringUtils.isBlank(category.getCategoryName()),"请指定菜单名称!");
        Integer grade =category.getGrade();
//        AssertUtil.isTrue(null== grade|| !(grade==0||grade==1||grade==2),"菜单层级不合法!");
        Category temp = categoryMapper.queryModuleByGradeAndModuleName(grade, category.getCategoryName());

        if(grade !=0){
            Integer parentId = category.getParentId();
            AssertUtil.isTrue(null==parentId ||
                    null==selectByPrimaryKey(parentId),"请指定上级菜单!");
        }
        AssertUtil.isTrue(updateByPrimaryKeySelective(category)<1,"菜单更新失败!");
    }

    /**
     * 删除
     * **/
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCategoryById(Integer mid){
        Category temp =selectByPrimaryKey(mid);
        AssertUtil.isTrue(null == mid || null == temp,"待删除记录不存在!");
        temp.setIsValid((byte) 0);
        AssertUtil.isTrue(updateByPrimaryKeySelective(temp)<1,"菜单删除失败!");
    }

    public List<Map<String, Object>> queryAllModulesByGrade(Integer grade) {
        return  categoryMapper.selectAllModuleByGrade(grade);
    }
}
