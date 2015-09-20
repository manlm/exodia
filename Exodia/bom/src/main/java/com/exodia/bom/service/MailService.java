package com.exodia.bom.service;

import com.exodia.bom.config.Mailer;
import com.exodia.bom.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manlm1 on 9/20/2015.
 */

@Service
public class MailService {

    @Autowired
    private Mailer mailer;

    public boolean sendMail(String email, String template, String subject, String account, String password) {
        Mail mail = new Mail();
        mail.setMailTo(email);
        mail.setTemplateName(template);
        mail.setMailSubject(subject);
        Map<String, String> mailAttribute = new HashMap<>();
        mailAttribute.put("account", account);
        mailAttribute.put("password", password);
        mailer.sendMail(mail, mailAttribute);
        return false;
    }
}
