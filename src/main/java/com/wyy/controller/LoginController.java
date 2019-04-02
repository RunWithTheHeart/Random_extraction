package com.wyy.controller;

import com.wyy.bean.Admin;
import com.wyy.bean.User;
import com.wyy.service.IUserService;
import com.wyy.service.impl.UserServiceImpl;
import com.wyy.utils.MailSender;
import com.wyy.utils.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(@RequestParam(required = false) String errmsg, Map<String, Object> map) {
        if (errmsg != null) {
            map.put("errmsg", errmsg);
        }
        return "login";
    }

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
            admin2 = userServiceImpl.selectAdmin(admin);
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

            return "login?errmsg=-1";

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
        return "login";
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
     * 发送验证码
     *
     * @param
     * @param
     */
    @RequestMapping("/sendCode")
    public void sendCode(HttpSession httpSession,@RequestParam("email") String email, @RequestParam("type") int type) {
        int code = (int) ((Math.random()) * 1000000);
        //判断邮箱是否存在

        JavaMailSender javaMailSender = mailSender.getMailSender();
        System.out.println("code验证码" + code);
        System.out.println("用户类型" + type);
        MimeMessage mime = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            //305396947@qq.com
            helper = new MimeMessageHelper(mime, true, "utf-8");
            helper.setTo(email);// 收件人邮箱地址
            //helper.setTo("rwtheart@163.com");// 收件人邮箱地址
           //helper.setTo("2572277840@qq.com");// 收件人邮箱地址
            helper.setFrom("2572277840@qq.com");// 发件人
            helper.setSubject("验证码");// 主题
            helper.setText("你好，你的验证码为：" + code);// 正文

        } catch (MessagingException me) {
            me.printStackTrace();
        }
        javaMailSender.send(mime);
        httpSession.setAttribute("code", code);
    }

    /**
     * 判断验证码是否正确
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/checkCode")
    public void checkCode(HttpSession httpSession,HttpServletResponse response, @RequestParam("code") String code) {

        //邮箱验证码code1 用户输入的为code
        String code1 = httpSession.getAttribute("code").toString();
        System.out.println("code1" + code1);
        System.out.println("code2" + code);
        try {
            PrintWriter out = response.getWriter();
            if(code.equals(code1)){
                out.append("true");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重置密码
     * @param httpSession
     * @param code
     */
    @RequestMapping("/resetPassword")
    public void resetPwd(HttpSession httpSession, @RequestParam("email") String email,
                           @RequestParam("code") String code, @RequestParam("password") String password) {

        //邮箱验证码code1 用户输入的为code
        String code1 = httpSession.getAttribute("code").toString();
        System.out.println("走到这里");
        System.out.println("email" + email);
        System.out.println("code" + code);
        System.out.println("password" + password);


        //return "login";
    }

}
