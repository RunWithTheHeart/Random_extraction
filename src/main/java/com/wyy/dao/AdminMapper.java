package com.wyy.dao;

import com.wyy.bean.Admin;
import com.wyy.bean.User;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

    //登录
    Admin selectByAll(Admin admin);
    //根据邮箱查找用户名 username
    String selectByEmail(String email);
    //重置密码
    int resetPwd(@Param("email") String email, @Param("password") String password);
}