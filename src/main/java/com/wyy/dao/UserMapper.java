package com.wyy.dao;


import com.wyy.bean.PageInfo;
import com.wyy.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //登录
    User selectByAll(User user);
    //根据邮箱查找用户名 username
    String selectByEmail(String email);
    //重置密码
    int resetPwd(@Param("email") String email,@Param("password") String password);
    //修改密码
    int changePwd(@Param("username")String username,@Param("password") String password);
    //查询所有用户
    //map start,length
    List<User> selectAllUser(Map<String,Object> params);

    //查询数据总条数
    int count();
}