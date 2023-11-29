package com.example.demo.service;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    public void sendTestEmail();

    void sendTemplateEmail(String userName) throws IOException, TemplateException, MessagingException;
}
