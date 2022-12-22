package com.vishvesh.capstone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
    @Autowired
    private JavaMailSender jms;
    
    public void sendEMail(String to, String subject,String body) {
        SimpleMailMessage mail=new SimpleMailMessage();
         
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);
        jms.send(mail);
        
    }
        
 }