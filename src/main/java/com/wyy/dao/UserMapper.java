package com.wyy.dao;

import com.wyy.bean.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper {

    //登录
    User selectByAll(User user);

}