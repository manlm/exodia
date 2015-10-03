package com.exodia.mail.service;

import com.exodia.mail.config.Mailer;
import com.exodia.mail.model.Mail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manlm1 on 9/20/2015.
 */

@Service
public class MailService {

    private static final Logger LOG = Logger.getLogger(MailService.class);

    @Autowired
    private Mailer mailer;

    public boolean sendMail(String email, String template, String subject, String account, String password) {

        LOG.info(new StringBuilder("[sendMail] Start: email = ").append(email).append(", template = ").append(template)
                .append(", subject = ").append(subject).append(", account = ").append(account));

        Mail mail = new Mail();
        mail.setMailTo(email);
        mail.setTemplateName(template);
        mail.setMailSubject(subject);
        Map<String, String> mailAttribute = new HashMap<>();
        mailAttribute.put("account", account);
        mailAttribute.put("password", password);
        mailer.sendMail(mail, mailAttribute);

        LOG.info("[sendMail] End");
        return true;
    }
}
