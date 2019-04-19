package com.wyy.service.impl;

import com.wyy.bean.Admin;
import com.wyy.bean.PageInfo;
import com.wyy.bean.User;
import com.wyy.dao.AdminMapper;
import com.wyy.dao.UserMapper;
import com.wyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
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
     * 查询所有用户
     * @return
     */
    @Override
    public PageInfo<User> selectAllUser(int start,int length,int draw) {
        int count = userMapper.count();

        Map<String, Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);

        PageInfo<User> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(userMapper.selectAllUser(params));

        return pageInfo;
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

    /**
     * 修改密码
     * @param username
     * @param password
     * @param type
     * @return
     */
    @Override
    public int changePwd(String username, String password, int type) {
        if(type==1){
            return adminMapper.changePwd(username,password);
        }
        return userMapper.changePwd(username,password);
    }


}
