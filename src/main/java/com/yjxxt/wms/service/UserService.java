package com.yjxxt.wms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.wms.base.BaseService;
import com.yjxxt.wms.bean.User;
import com.yjxxt.wms.dao.UserMapper;
import com.yjxxt.wms.query.UserQuery;
import com.yjxxt.wms.util.AssertUtil;
import com.yjxxt.wms.util.Md5Util;
import com.yjxxt.wms.util.PhoneUtil;
import com.yjxxt.wms.util.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends BaseService<User,Integer> {

    @Autowired
    private UserMapper userMapper;

    //    注册
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user){
        // 1. 参数校验
        checkUser(user.getUserAccount(),
                user.getUserPassword(),
                user.getUserName(),
                user.getUserSex(),
                user.getUserAge(),
                user.getUserPhone(),
                user.getUserEmail(),
                user.getUserAddress());
        // 2. 设置默认参数
        user.setUserPassword(Md5Util.encode(user.getUserPassword()));
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        // 3. 执行添加，判断结果
        AssertUtil.isTrue(userMapper.insertSelective(user) < 1, "用户注册失败！");
    }

    /**
     * 校验
     * **/
    private void checkUser(String userAccount, String userPassword, String userName, String userSex, String userAge, String userPhone, String userEmail, String userAddress) {
        AssertUtil.isTrue(StringUtils.isBlank(userAccount),"用户名不能为空");
        //验证用户名是否存在
        User temp = userMapper.queryUserByUserName(userAccount);
        AssertUtil.isTrue(null!=temp,"用户名已存在，请重新输入");
        AssertUtil.isTrue(StringUtils.isBlank(userPassword), "密码不能为空");
        AssertUtil.isTrue(userPassword.length()<6,"密码长度不能小于6位");
        AssertUtil.isTrue(userPassword.length()>16,"密码长度不能大于16位");
        AssertUtil.isTrue(StringUtils.isBlank(userName),"姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userSex),"请选择性别");
        AssertUtil.isTrue(StringUtils.isBlank(userAge),"年龄不能为空");
        AssertUtil.isTrue(Integer.parseInt(userAge)<0&&Integer.parseInt(userAge)>150,"年龄不合法");
        AssertUtil.isTrue(!PhoneUtil.isMobile(userPhone),"手机号格式不正确！");
        AssertUtil.isTrue(StringUtils.isBlank(userEmail),"邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userAddress),"请选择城市");

    }
//    注册



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
        User user = userMapper.selectByPrimaryKey(userId);
//        1、参数校验
        checkPasswordParams(user,oldPwd,newPwd,towPwd);
//        2、设置用户新密码
        user.setUserPassword(Md5Util.encode(newPwd));
//        3、执行更新操作
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"用户密码更新失败!");


    }

    /**
     * 验证用户密码修改参数
     * 用户ID：userId 非空 用户对象必须存在
     * 原始密码：oldPassword 非空 与数据库中密文密码保持一致
     * 新密码：newPassword 非空 与原始密码不能相同
     * 确认密码：confirmPassword 非空 与新密码保持一致
     *
     * @param user
     * @param oldPwd
     * @param newPwd
     * @param towPwd
     */

    private void checkPasswordParams(User user, String oldPwd, String newPwd, String towPwd) {
//        user对象，非空验证
        AssertUtil.isTrue(null==user,"用户不存在");
//        原始密码，非空验证
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd),"请输入原始密码");
//        原始密码要与数据库中的密码保持一致
        AssertUtil.isTrue(!(user.getUserPassword().equals(Md5Util.encode(oldPwd))),"原始密码不正确");
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






    //登录
    public UserQuery userLogin(String userName, String userPwd) {
        checkUserLoginParam(userName, userPwd);
        //用户是否存在
        User temp = userMapper.selectUserByName(userName);
        AssertUtil.isTrue(temp == null, "用户不存在");
        //用户的密码是否正确
        checkUserPwd(userPwd, temp.getUserPassword());
        //构建返回对象
        return builderUserInfo(temp);
    }

    /**
     * 构建返回目标对象的
     *
     * @param user
     * @return
     */
    private UserQuery builderUserInfo(User user) {
        //实例化目标对象
        UserQuery userModel = new UserQuery();
        //加密
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getUserId()));
        userModel.setUserName(user.getUserName());
        //返回目标对象
        return userModel;
    }

    /**
     * 校验用户密码
     *
     * @param userName
     * @param userPwd
     */
    private void checkUserLoginParam(String userName, String userPwd) {
        //用户非空
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户不能为空");
        //密码非空
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "密码不能为空");
    }


    /**
     * 验证密码
     *
     * @param userPwd
     * @param userPwd1
     */
    private void checkUserPwd(String userPwd, String userPwd1) {
        //对输入的密码加密
        userPwd = Md5Util.encode(userPwd);
        //加密的密码和数据中的密码对比
        AssertUtil.isTrue(!userPwd.equals(userPwd1), "用户密码不正确！");
    }

    public User selectByParamsByName(String userName) {
        User user = userMapper.selectUserByName(userName);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void upDateUser(User user,int userId) {
        AssertUtil.isTrue(!PhoneUtil.isMobile(user.getUserPhone()),"手机号码格式不正确！");
        AssertUtil.isTrue(Integer.parseInt(user.getUserAge())<0 && Integer.parseInt(user.getUserAge())>150,"年龄不合法！");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"真实姓名不能为空");
        user.setUserId(userId);
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"更改失败！");
    }
    //登录




    /**
     * 批量删除
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUserIds(Integer [] ids){
        //验证
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");
    }



    public Map<String, Object> findUserByParams(UserQuery userQuery) {
        //实例化map
        Map<String, Object> map = new HashMap<String, Object>();
        //初始化分页单位
        PageHelper.startPage(userQuery.getPage(), userQuery.getLimit());
        //开始分页
        PageInfo<User> plist = new PageInfo<User>(selectByParams(userQuery));

        //准备数据
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", plist.getTotal());
        map.put("data", plist.getList());
        //返回目标map
        return map;

    }



    private void checkUser(String userName, String email, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(email), "邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "手机号不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "输入合法的手机号");
    }




    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        System.out.println(user);
        //验证
        checkUser(user.getUserName(), user.getUserEmail(), user.getUserPhone());
        //用户名唯一
        User temp = userMapper.selectUserByName(user.getUserAccount());
        AssertUtil.isTrue(temp != null, "用户名已经存在");
        //设定默认
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        //密码加密
        user.setUserPassword(Md5Util.encode("123456"));
        //验证是否成功
        AssertUtil.isTrue(insertSelective(user) < 1, "添加失败了");
//        AssertUtil.isTrue(insertHasKey(user) < 1, "添加失败了");
        System.out.println(user.getUserId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void changeUser(User user) {
        System.out.println(user);
        //根据ID获取用户的信息
        User temp = userMapper.selectByPrimaryKey(user.getUserId());
        //判断
        AssertUtil.isTrue(temp == null, "待修改的记录不存在");
        //验证参数
        checkUser(user.getUserName(), user.getUserEmail(), user.getUserPhone());
        //修改用户名已经存在问题
        User temp2 = userMapper.selectUserByName(user.getUserAccount());
        AssertUtil.isTrue(temp2 != null, "用户名称已经存在");
        System.out.println("yhmyzcg");
        //设定默认值
        user.setUpdateDate(new Date());
        //判断修改是否成功

        AssertUtil.isTrue(updateByPrimaryKeySelective(user) < 1, "修改失败了");
        System.out.println("zqmtll");

    }


}
