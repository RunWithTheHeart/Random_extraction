package com.wyy.test;

import com.wyy.utils.MailSender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailSenderUtil {
    /**
     * 参考：http://wang3065.iteye.com/blog/1718381
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        MailSender sender = (MailSender) ac.getBean("MailSender");
        JavaMailSender javaMailSender = sender.getMailSender();
        MimeMessage mime = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mime, true, "utf-8");
            helper.setTo("rw@qq.com");// 收件人邮箱地址
            helper.setFrom("2572277840@qq.com");// 发件人
            helper.setSubject("SpringMailTest");// 主题
            helper.setText("测试Spring自带邮件发送功能");// 正文

        } catch (MessagingException me) {
            me.printStackTrace();
        }
        javaMailSender.send(mime);
    }

}