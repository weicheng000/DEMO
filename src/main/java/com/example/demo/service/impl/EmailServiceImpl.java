package com.example.demo.service.impl;

import com.example.demo.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final Configuration configuration;

    @Override
    public void sendTestEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jiangweicheng104@gmail.com");
        message.setTo("weicheng000@live.com");
        message.setSubject("主旨：Hello World!");
        message.setText("這是一封測試郵件，恭喜我吧！成功發送！");

        mailSender.send(message);
    }

    @Override
    public void sendTemplateEmail(String userName) throws IOException, TemplateException, MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom("jiangweicheng104@gmail.com");
        helper.setTo("weicheng000@live.com");
        helper.setSubject("主旨：模板信件");
        String emailContent = getEmailContent(userName);



        helper.setText(emailContent, true);

        ClassPathResource resource = new ClassPathResource("images/logo.png");
        helper.addInline("image", resource);

        mailSender.send(mimeMessage);
    }

    String getEmailContent(String userName) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("userName", userName);

        configuration.getTemplate("template.ftlh").process(model,stringWriter);

        return stringWriter.getBuffer().toString();
    }
}
