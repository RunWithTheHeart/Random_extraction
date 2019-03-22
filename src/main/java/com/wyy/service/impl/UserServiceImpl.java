package com.wyy.service.impl;

import com.wyy.bean.User;
import com.wyy.dao.UserMapper;
import com.wyy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectUser(User user) {
        return userMapper.selectByAll(user);
    }
}
