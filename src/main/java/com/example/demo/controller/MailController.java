package com.example.demo.controller;

import com.example.demo.service.EmailService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final EmailService emailService;

    @GetMapping("/test")
    public void sentTestMail(){
        emailService.sendTestEmail();
    }

    @GetMapping("/template")
    public void sentTemplateEmail(String userName) throws TemplateException, MessagingException, IOException {
        log.info("Start Template Mail, param: {}", userName);
        emailService.sendTemplateEmail(userName);
    }
}
