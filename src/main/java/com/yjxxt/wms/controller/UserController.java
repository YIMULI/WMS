package com.yjxxt.wms.controller;

import com.yjxxt.wms.annotation.RequirePermission;
import com.yjxxt.wms.base.BaseController;
import com.yjxxt.wms.base.ResultInfo;
import com.yjxxt.wms.bean.User;
import com.yjxxt.wms.query.UserQuery;
import com.yjxxt.wms.service.UserService;
import com.yjxxt.wms.util.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;


    @RequestMapping("index")
    public String index() {
        return "user/user";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(User user) {
        //用户的添加
        System.out.println(user);
        userService.addUser(user);
        //返回目标数据对象
        return success("用户添加OK");
    }

//    @RequestMapping("update")
//    @ResponseBody
//    public ResultInfo update(User user) {
//        System.out.println("update runing");
//        System.out.println(user);
//        //用户的添加
//        userService.changeUser(user);
//        //返回目标数据对象
//        return success("用户修改OK");
//    }

    @RequestMapping("/update/user")
    @ResponseBody
    public ResultInfo updateuser(User user) {
        System.out.println("update runing");
        System.out.println(user);
//        //用户的添加
        userService.changeUser(user);
        //返回目标数据对象
        return success("用户修改OK");
    }

    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }


    @RequestMapping("toSettingPage")
    public String toSettingPage(HttpServletRequest req){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);
        User user = userService.selectByPrimaryKey(userId);
        req.setAttribute("user",user);
        return "user/setting";
    }

    @RequestMapping("login")
    @ResponseBody
    public ResultInfo say(User user) {
        ResultInfo resultInfo = new ResultInfo();
        UserQuery userModel = userService.userLogin(user.getUserAccount(), user.getUserPassword());
        resultInfo.setResult(userModel);
        return resultInfo;
    }

//    @ResponseBody
//    public ResultInfo register(User user){
//        System.out.println(user);
//        return success("ok");
//    }

//    @RequestMapping("login")
//    @ResponseBody
//    public ResultInfo say(User user) {
//        ResultInfo resultInfo = new ResultInfo();
//        UserQuery userModel = userService.userLogin(user.getUserAccount(), user.getUserPassword());
//        resultInfo.setResult(userModel);
//        return resultInfo;
//    }



//    @RequestMapping("upuser")
//    @ResponseBody
//    public ResultInfo upuser(String oldPwd,String newPwd,String towPwd){
//        System.out.println(oldPwd+">>>>>>>>>"+newPwd+">>>>>>>>>"+towPwd);
//        return success("ok");
//    }



    @RequestMapping("upuser")
    @ResponseBody
    public ResultInfo updateUserPassword (HttpServletRequest request, String oldPwd, String newPwd, String towPwd) {
        ResultInfo resultInfo = new ResultInfo();
//        获取userID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
//        调用service层的密码修改密码
        userService.updateUserPassword(userId,oldPwd,newPwd,towPwd);
////        设置状态栏和提示信息
//        resultInfo.setCode(getcode);
//        resultInfo.setMsg(getmsg);
        return resultInfo;
    }



    @RequestMapping("upusermsg")
    @ResponseBody
    public ResultInfo upusermsg (User user,HttpServletRequest req) {
        ResultInfo resultInfo = new ResultInfo();
        int id = LoginUserUtil.releaseUserIdFromCookie(req);
        userService.upDateUser(user,id);
        return resultInfo;
    }




    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids) {
        System.out.println(Arrays.toString(ids));
        userService.removeUserIds(ids);
        //返回目标数据对象
        return success("批量删除用户OK");
    }

    @RequestMapping("remove")
    @ResponseBody
    public ResultInfo remove(Integer ids) {
        System.out.println("id="+ids);
        User user = new User();
        user.setUserId(ids);
        user.setIsValid(0);
        userService.updateByPrimaryKeySelective(user);
//        userService.(ids);
        //返回目标数据对象
        return success("批量删除用户OK");
    }

    @RequestMapping("list")
    @ResponseBody
    @RequirePermission(code = "20")
    public Map<String,Object> list(UserQuery userQuery) {
        return userService.findUserByParams(userQuery);
    }


    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdatePage(Integer id, Model model) {
        System.out.println(id);
        if (id != null) {
            User user = userService.selectByPrimaryKey(id);
            if(null == user.getUserSex()){
                user.setUserSex("未知");
            }
            if(null == user.getUserAge()){
                user.setUserSex("未知");
            }
            model.addAttribute("user", user);
        }
        return "user/add_update";
    }



    @RequestMapping("balance")
    public String balance(){
        return "user/balance";
    }


    @RequestMapping("addOrUpdateUserPageByBalance")
    public String addOrUpdateUserPageByBalance(Integer id, Model model){
        System.out.println(id);
        if (id != null) {
            User user = userService.selectByPrimaryKey(id);
            if(null == user.getUserSex()){
                user.setUserSex("未知");
            }
            if(null == user.getUserAge()){
                user.setUserSex("未知");
            }
            model.addAttribute("user", user);
        }
        return "user/add_balance";
    }


    @RequestMapping("update/balance")
    @ResponseBody
    public ResultInfo balances(User user){
        System.out.println(1001);
        userService.updateByPrimaryKeySelective(user);
        return success("修改成功");
    }


}
