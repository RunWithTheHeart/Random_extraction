package com.wyy.dao;

import com.wyy.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    //登录
    User selectByAll(User user);
    //根据邮箱查找用户名 username
    String selectByEmail(String email);
    //重置密码
    int resetPwd(@Param("email") String email,@Param("password") String password);

}