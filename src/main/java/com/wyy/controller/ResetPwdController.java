package com.wyy.controller;

import com.wyy.bean.Admin;
import com.wyy.bean.User;
import com.wyy.service.impl.UserServiceImpl;
import com.wyy.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@Controller
public class ResetPwdController extends BaseController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private MailSender mailSender;

    //返回的查询结果
    Admin admin2 = null;
    User user2 = null;


    /**
     * 发送验证码
     *
     * @param
     * @param
     */
    @RequestMapping("/sendCode")
    public void sendCode(HttpSession httpSession,HttpServletResponse response,@RequestParam("email") String email, @RequestParam("type") int type) {

        int code = (int) ((Math.random()) * 1000000);

         System.out.println("code验证码" + code);
         System.out.println("用户类型" + type);

        JavaMailSender javaMailSender = mailSender.getMailSender();

        MimeMessage mime = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            //305396947@qq.com
            helper = new MimeMessageHelper(mime, true, "utf-8");
            helper.setTo(email);// 收件人邮箱地址
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
     * 判断邮箱是否存在
     * @param httpSession
     * @return
     */
    @RequestMapping("/checkEmail")
    public void checkEmail(HttpSession httpSession,HttpServletResponse response,@RequestParam("email") String email, @RequestParam("type") int type) {

        //判断邮箱是否存在
        String username = userServiceImpl.selectUsername(email,type);
        if(username == null) {
            //返回错误信息
            try {
                PrintWriter out = response.getWriter();
                out.append("false");

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("邮箱不存在");
        }

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
     * @param
     * @param
     */
    /*@RequestMapping("/resetPwd")
    public String resetPwd(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        int type = Integer.parseInt(request.getParameter("type"));
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            if (userServiceImpl.updatePwd(email, pwd,type) == 1) {
                //重置密码成功
                out.print("<script language=\"javascript\">alert('重置密码成功,请返回登录');window.location.href='/Random_extraction/login'</script>");
                //out.write("<script language=\"javascript\">alert('重置密码成功,请返回登录');window.location.href='/Random_extraction/login'</script>");
                System.out.println("重置密码成功,请返回登录");
                return "login";
            } else {
                System.out.println("重置密码失败,请重新操作");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "resetPassword";
    }*/
    @RequestMapping("/resetPwd")
    public void resetPwd(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        int type = Integer.parseInt(request.getParameter("type"));
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            if (userServiceImpl.updatePwd(email, pwd,type) == 1) {
                //重置密码成功
               out.append("true");
                System.out.println("重置密码成功,请返回登录");
            } else {
                out.append("false");
                System.out.println("重置密码失败,请重新操作");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     * @param request
     * @param response
     */
    @RequestMapping("/change_password")
    public void changePwd(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        int type = Integer.parseInt(request.getParameter("type"));
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            if (userServiceImpl.changePwd(username,pwd,type) == 1) {
                //重置密码成功
                out.append("true");
                System.out.println("密码更改成功,请返回登录");
            } else {
                out.append("false");
                System.out.println("密码更改失败,请重新操作");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
