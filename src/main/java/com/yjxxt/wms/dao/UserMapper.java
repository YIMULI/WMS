package com.yjxxt.wms.dao;

import com.yjxxt.wms.base.BaseMapper;
import com.yjxxt.wms.bean.User;
import com.yjxxt.wms.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User,Integer> {

    User queryUserByUserName(String userName);

    UserQuery queryUserByAccount(@Param("account") String account);

    User selectUserByName(String userName);
}