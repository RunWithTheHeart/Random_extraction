package com.wyy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void send(SimpleMailMessage mail) {
        mailSender.send(mail);
    }

    public JavaMailSender getMailSender(){
        return this.mailSender;
    }

}