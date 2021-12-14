package com.yjxxt.wms.dao;

import com.yjxxt.wms.base.BaseMapper;
import com.yjxxt.wms.bean.Module;
import com.yjxxt.wms.dto.TreeDto;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module,Integer> {

    List<TreeDto> selectAllModules();

}