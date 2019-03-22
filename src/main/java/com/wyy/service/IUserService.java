package com.wyy.service;

import com.wyy.bean.Admin;
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
public interface IUserService {

    //查询用户
    public User selectUser(User user);
    //查询用户
    public Admin selectAdmin(Admin admin);
}
