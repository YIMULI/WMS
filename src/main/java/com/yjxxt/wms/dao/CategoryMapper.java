package com.yjxxt.wms.dao;

import com.yjxxt.wms.base.BaseMapper;
import com.yjxxt.wms.bean.Category;
import com.yjxxt.wms.dto.TreeDto;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper extends BaseMapper<Category,Integer> {

    List<Category> queryCategory();

    List<TreeDto> selectModules();

    Category queryModuleByGradeAndModuleName(Integer grade, String categoryName);

    @MapKey("")
    List<Map<String, Object>> selectAllModuleByGrade(Integer grade);
}