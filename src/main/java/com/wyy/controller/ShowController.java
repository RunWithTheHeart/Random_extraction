package com.wyy.controller;

import com.wyy.bean.PageInfo;
import com.wyy.bean.User;
import com.wyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author qianqian.zhang
 * @date 2018-03-13 10:17
 **/
@Controller
public class ShowController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/show_user")
    @ResponseBody
    public PageInfo<User> showUser(HttpServletRequest request, HttpServletResponse response) {

        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        int draw = Integer.parseInt(request.getParameter("draw"));

        System.out.println("执行了"+draw+";start:"+start+";+length:"+length);

        PageInfo<User> users = userService.selectAllUser(start, length, draw);
        System.out.println(users);
        return users;
    }
}
