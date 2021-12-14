package com.yjxxt.wms.controller;

import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.dto.TreeDto;
import com.yjxxt.wms.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {

    @Resource
    private ModuleService moduleService;


    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeDto> queryAllModules(Integer roleId){
        return moduleService.selectAllModiles02(roleId);
    }

}
