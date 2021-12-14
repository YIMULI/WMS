package com.yjxxt.wms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.wms.base.BaseService;
import com.yjxxt.wms.bean.Admin;
import com.yjxxt.wms.bean.User;
import com.yjxxt.wms.dao.AdminMapper;
import com.yjxxt.wms.query.AdminQuery;
import com.yjxxt.wms.query.UserQuery;
import com.yjxxt.wms.util.AssertUtil;
import com.yjxxt.wms.util.Md5Util;
import com.yjxxt.wms.util.PhoneUtil;
import com.yjxxt.wms.util.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AdminService extends BaseService<Admin,Integer> {

    @Resource
    private AdminMapper adminMapper;


    public Map<String, Object> queryUserByParams(AdminQuery query) {
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(),query.getLimit());
        PageInfo<Admin> pageInfo = new PageInfo<>(adminMapper.selectByParams(query));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void addUserMsg(Admin admin){
        System.out.println(12);
        // 1.参数校验
        checkParams(admin.getAdminAccount(),admin.getAdminEmail(),admin.getAdminPhone());

        AssertUtil.isTrue(!("男".equals(admin.getAdminSex()) || "女".equals(admin.getAdminSex())),"性别不合法！");
        AssertUtil.isTrue(Integer.parseInt(admin.getAdminAge())<0||Integer.parseInt(admin.getAdminAge())>150,"年龄不合法！");

//       2. 设置默认参数
        admin.setAdminAddress("上海");
//       isValid 1
        admin.setIsValid(1);
//       creteDate 当前时间
        admin.setCreateDate(new Date());
//       updateDate 当前时间
        admin.setUpdateDate(new Date());
//       userPwd 123456 -> md5加密
        admin.setAdminPassword(Md5Util.encode("123456"));
        // 3.执行添加 判断结果
        AssertUtil.isTrue(adminMapper.insertSelective(admin)<1,"添加数据失败！");
//        System.out.println(admin.getRoleIds());
//        System.out.println(admin.getId());
//        relaionUserRole(admin.getAdminId(), admin.getRoleIds());

    }

//    private void relaionUserRole(Integer useriD, String roleIds) {
//
//        Integer count = userRoleMapper.countUserRoleByUserId(useriD);
//
//        if(count > 0){
//            AssertUtil.isTrue(userRoleMapper.deleteByUserId(useriD) != count,"用户角色分配失败!");
//        }
//        List<UserRole> userRoles = new ArrayList<>();
//        if(StringUtils.isNotBlank(roleIds)){
//            String[] roles = roleIds.split(",");
//
//            for(String role : roles){
//                UserRole userRole = new UserRole();
//                System.out.println(role);
//                userRole.setRoleId(Integer.parseInt(role));
//                userRole.setUserId(useriD);
//                userRole.setCreateDate(new Date());
//                userRole.setUpdateDate(new Date());
//                userRoles.add(userRole);
//
//            }
//            System.out.println(userRoles);
//            AssertUtil.isTrue(userRoleMapper.insertBatch(userRoles) != userRoles.size(),"用户角色分配失败!");
//        }
//
//
//    }


    private void checkParams(String name, String email, String phone) {
        System.out.println(19);
        AssertUtil.isTrue(null == name,"用户名不能为空！");
        Admin admin = adminMapper.selectUserByName(name);
        AssertUtil.isTrue(null != admin,"用户名已存在！");
        AssertUtil.isTrue(null == email,"邮箱不能为空！");
        AssertUtil.isTrue(null == phone,"手机号不能为空");
        System.out.println(phone);
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"请输入正确的手机号");
    }
    private void checkParamsByUpData(Admin admin) {
        System.out.println(91);
        Admin admin1 = adminMapper.selectUserByName(admin.getAdminAccount());
//        System.out.println(user.getUserName()+">>>>>>"+user.getPhone()+">>>>>>>>"+user.getEmail());
//        System.out.println(name+">>>>>>"+phone+">>>>>>>>"+email);
        AssertUtil.isTrue(null == admin.getAdminAccount(),"用户名不能为空！");
        System.out.println(admin);

        if(admin.getAdminAccount().equals(admin1.getAdminAccount())){
            AssertUtil.isTrue(adminMapper.updateByPrimaryKey(admin)<1,"未更改数据");
        }

        AssertUtil.isTrue(null == admin.getAdminEmail(),"邮箱不能为空！");
        AssertUtil.isTrue(null == admin.getAdminPhone(),"手机号不能为空");
        System.out.println(admin.getAdminPhone());
        AssertUtil.isTrue(!PhoneUtil.isMobile(admin.getAdminPhone()),"请输入正确的手机号");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Integer[] ids){
        // 判断要删除的id是否为空
        AssertUtil.isTrue(null == ids || ids.length == 0,"请选择需要删除的数据！");
        AssertUtil.isTrue(adminMapper.deleteBatch(ids)<=0,"删除失败！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUser(Admin admin){
        AssertUtil.isTrue(adminMapper.updateByPrimaryKeySelective(admin)<1,"删除失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void changeUser(Admin admin){
        System.out.println(13);
        // 1. 参数校验
        // 通过id查询用户对象
        Admin admin1 = adminMapper.selectByPrimaryKey(admin.getAdminId());
        // 验证参数
        AssertUtil.isTrue(null == admin1,"用户不存在");
        AssertUtil.isTrue(!("男".equals(admin.getAdminSex()) || "女".equals(admin.getAdminSex())),"性别不合法！");
        AssertUtil.isTrue(Integer.parseInt(admin.getAdminAge())<0||Integer.parseInt(admin.getAdminAge())>150,"年龄不合法！");
        // 2. 设置默认参数
        admin.setIsValid(1);
        admin.setAdminPassword(Md5Util.encode("123456"));
        System.out.println(admin1.getCreateDate());
        admin.setAdminAddress(admin1.getAdminAddress()) ;
        admin.setCreateDate(admin1.getCreateDate());
        checkParamsByUpData(admin);
        admin.setUpdateDate(new Date());
        // 3. 执行更新，判断结果
        AssertUtil.isTrue(adminMapper.updateByPrimaryKeySelective(admin)<1,"用户更新失败！");
//        relaionUserRole(user.getId(), user.getRoleIds());
    }


    public UserQuery adminLogin(String adminAccount, String adminPassword) {

        checkUserLoginParam(adminAccount, adminPassword);
        //用户是否存在
        Admin admin = adminMapper.selectUserByName(adminAccount);
        AssertUtil.isTrue(admin == null, "用户不存在");
        //用户的密码是否正确
        checkUserPwd(adminPassword, admin.getAdminPassword());
        //构建返回对象
        return builderUserInfo(admin);

    }

    private void checkUserLoginParam(String userName, String userPwd) {
        //用户非空
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户不能为空");
        //密码非空
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "密码不能为空");
    }

    private void checkUserPwd(String userPwd, String userPwd1) {
        //对输入的密码加密
        userPwd = Md5Util.encode(userPwd);
        //加密的密码和数据中的密码对比
        AssertUtil.isTrue(!userPwd.equals(userPwd1), "用户密码不正确！");
    }

    private UserQuery builderUserInfo(Admin admin) {
        //实例化目标对象
        UserQuery userModel = new UserQuery();
        //加密
        userModel.setUserIdStr(UserIDBase64.encoderUserID(admin.getAdminId()));
        userModel.setUserName(admin.getAdminName());
        //返回目标对象
        return userModel;
    }

    public Admin selectByParamsByAccount(String adminAccount) {
        return adminMapper.selectUserByName(adminAccount);
    }

    //修改密码
    /**
     * 用户密码修改
     * 1. 参数校验
     * 用户ID：userId 非空 用户对象必须存在
     * 原始密码：oldPassword 非空 与数据库中密文密码保持一致
     * 新密码：newPassword 非空 与原始密码不能相同
     * 确认密码：confirmPassword 非空 与新密码保持一致
     * 2. 设置用户新密码
     * 新密码进行加密处理
     * 3. 执行更新操作
     * 受影响的行数小于1，则表示修改失败
     * <p>
     * 注：在对应的更新方法上，添加事务控制
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserPassword(Integer userId, String oldPwd, String newPwd, String towPwd) {
//      通过useId获取用户对象
        Admin admin = adminMapper.selectByPrimaryKey(userId);
//        1、参数校验
        checkPasswordParams(admin,oldPwd,newPwd,towPwd);
//        2、设置用户新密码
        admin.setAdminPassword(Md5Util.encode(newPwd));
//        3、执行更新操作
        AssertUtil.isTrue(adminMapper.updateByPrimaryKeySelective(admin)<1,"用户密码更新失败!");


    }

    /**
     * 验证用户密码修改参数
     * 用户ID：userId 非空 用户对象必须存在
     * 原始密码：oldPassword 非空 与数据库中密文密码保持一致
     * 新密码：newPassword 非空 与原始密码不能相同
     * 确认密码：confirmPassword 非空 与新密码保持一致
     *
     * @param admin
     * @param oldPwd
     * @param newPwd
     * @param towPwd
     */

    private void checkPasswordParams(Admin admin, String oldPwd, String newPwd, String towPwd) {
//        user对象，非空验证
        AssertUtil.isTrue(null==admin,"用户不存在");
//        原始密码，非空验证
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd),"请输入原始密码");
//        原始密码要与数据库中的密码保持一致
        AssertUtil.isTrue(!(admin.getAdminPassword().equals(Md5Util.encode(oldPwd))),"原始密码不正确");
//       新密码不能为空
        AssertUtil.isTrue(StringUtils.isBlank(newPwd),"新密码不能为空");
//        新密码和原始密码不能相同
        AssertUtil.isTrue(oldPwd.equals(newPwd),"新密码不能和原始密码相同");
//        确认密码  非空检验
        AssertUtil.isTrue(StringUtils.isBlank(towPwd),"请输入确认密码");
//        新密码要与确认密码保持一致
        AssertUtil.isTrue(!(newPwd.equals(towPwd)),"新密码与确认密码不一致");
    }

//修改密码

    @Transactional(propagation = Propagation.REQUIRED)
    public void upDateUser(Admin admin,int userId) {
        AssertUtil.isTrue(!PhoneUtil.isMobile(admin.getAdminPhone()),"手机号码格式不正确！");
        AssertUtil.isTrue(Integer.parseInt(admin.getAdminAge())<0 && Integer.parseInt(admin.getAdminAge())>150,"年龄不合法！");
        AssertUtil.isTrue(StringUtils.isBlank(admin.getAdminName()),"真实姓名不能为空");
        admin.setAdminId(userId);
        AssertUtil.isTrue(adminMapper.updateByPrimaryKeySelective(admin)<1,"更改失败！");
    }


}
