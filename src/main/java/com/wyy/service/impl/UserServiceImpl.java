package com.wyy.service.impl;

import com.wyy.bean.Admin;
import com.wyy.bean.User;
import com.wyy.dao.AdminMapper;
import com.wyy.dao.UserMapper;
import com.wyy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    public UserServiceImpl() {
    }

    /**
     * 普通用户
     * @param user
     * @return
     */
    @Override
    public User selectUser(User user) {
        return userMapper.selectByAll(user);
    }

    /**
     * 管理员
     * @param admin
     * @return
     */
    @Override
    public Admin selectAdmin(Admin admin) {
        return adminMapper.selectByAll(admin);
    }

    @Override
    public String selectUsername(String email,int type) {
        if(type ==1){
            return adminMapper.selectByEmail(email);
        }
        return userMapper.selectByEmail(email);
    }

    @Override
    public int updatePwd(String email, String password,int type) {
        if(type==1){
            return adminMapper.resetPwd(email,password);
        }
        return userMapper.resetPwd(email,password);
    }
}
