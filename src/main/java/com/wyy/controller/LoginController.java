package com.wyy.controller;

import com.wyy.bean.User;
import com.wyy.service.IUserService;
import com.wyy.service.impl.UserServiceImpl;
import com.wyy.utils.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class LoginController extends BaseController{

    @Autowired
    private UserServiceImpl userServiceImpl;
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(@RequestParam(required = false)String errmsg,Map<String,Object> map){
        if(errmsg !=null){
            map.put("errmsg", errmsg);
        }
        return "login";
    }

    /**
     * 用户登录
     * @param httpSession
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/userLogin")
    public String userLogin(HttpSession httpSession, String username, String password, Model model) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if (!((userServiceImpl.selectUser(user))== null)) {
            //登陆成功
            System.out.println("登录成功");
            httpSession.setAttribute("username", username);
//          httpSession.removeAttribute("errmsg");
            return "index";
        } else {
            //登陆失败
            System.out.println("登陆失败");
            model.addAttribute("errmsg","登录失败，请重新登录！");

            return "login?errmsg=-1";

        }
    }

    /**
     * 用户登出
     * @param httpSession
     * @return
     */
    @RequestMapping("/userLogout")
    public String userLogout(HttpSession httpSession){
        httpSession.invalidate();
        return "login";
    }

    /**
     * pdf下载
     * @param request
     * @param response
     * @author qianqian 2017-3-16 下午4:19:35
     */
    @RequestMapping("/testDownload")
    public void downloadLetter(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String,Object> variables = new HashMap<String,Object>();
            variables.put("name", "zxxx");
            String basePath = request.getSession().getServletContext().getRealPath("/");
            variables.put("contextPath", basePath);
            PdfGenerator.printPDF(basePath, variables, "hello", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
