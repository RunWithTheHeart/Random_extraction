package com.wyy.service;

import com.wyy.bean.Admin;
import com.wyy.bean.PageInfo;
import com.wyy.bean.User;
import com.wyy.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc:
 *
 * @author wyy
 * @date 2019-03-07 14:45
 **/
@Service
public interface UserService {

    //查询用户
    User selectUser(User user);
    //查询所有用户
    PageInfo<User> selectAllUser(int start,int length,int draw);
    //查询用户
    Admin selectAdmin(Admin admin);

    //根据email查询用户名
    String selectUsername(String email,int type);
    //根据email重置密码
    int updatePwd(String email,String password,int type);
    //修改密码
    int changePwd(String username, String password, int type);



}
