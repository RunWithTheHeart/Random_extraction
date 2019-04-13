package com.wyy.controller;

import com.wyy.bean.Admin;
import com.wyy.bean.User;
import com.wyy.service.impl.UserServiceImpl;
import com.wyy.utils.MailSender;
import com.wyy.utils.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 *
 * @author qianqian.zhang
 * @date 2018-03-13 10:17
 **/
@SessionAttributes(value = {"errmsg"})
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserServiceImpl userServiceImpl;
     @Autowired
     private MailSender mailSender;

    //返回的查询结果
    Admin admin2 = null;
    User user2 = null;




    /**
     * 用户登录
     *
     * @param httpSession
     * @return String username, String password,
     */
    @RequestMapping("/userLogin")
    public String userLogin(HttpSession httpSession, User user, Model model) {

        System.out.println(user);
        //1.管理员  2.普通用户
        if (user.getType() == 1) {
            Admin admin = new Admin();
            admin.setUsername(user.getUsername());
            admin.setPassword(user.getPassword());
//            admin2 = userServiceImpl.selectAdmin(admin);
            //System.out.println(admin2);
        } else {
            user2 = userServiceImpl.selectUser(user);
        }


        if (user.getType() == 1 && admin2 != null) {
            //管理员登陆成功
            System.out.println("登录成功");
            httpSession.setAttribute("username", user.username);
            httpSession.removeAttribute("errmsg");
            return "index";
        }
        if (user.getType() == 2 && user2 != null) {
            //普通用户登陆成功
            System.out.println("登录成功");
            httpSession.setAttribute("username", user.username);
            httpSession.removeAttribute("errmsg");
            return "index";
        } else {
            //登陆失败
            System.out.println("登陆失败");
            model.addAttribute("errmsg", "用户名或密码错误！");

            return "login";

        }
    }

    /**
     * 用户登出
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/userLogout")
    public String userLogout(HttpSession httpSession) {
        httpSession.invalidate();
        return "/login.jsp";
    }

    /**
     * pdf下载
     *
     * @param request
     * @param response
     * @author qianqian 2017-3-16 下午4:19:35
     */
    @RequestMapping("/testDownload")
    public void downloadLetter(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("name", "zxxx");
            String basePath = request.getSession().getServletContext().getRealPath("/");
            variables.put("contextPath", basePath);
            PdfGenerator.printPDF(basePath, variables, "hello", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到登录页面
     * @return
     */

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 跳转到忘记密码页面
     * @param request
     * @param response
     * @return
     */

    @RequestMapping("/a")
    public String downloadLett(HttpServletRequest request, HttpServletResponse response) {
        return "resetPassword";
    }

}
