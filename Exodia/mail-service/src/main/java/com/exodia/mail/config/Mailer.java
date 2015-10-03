package com.exodia.mail.config;

/**
 * Created by manlm1 on 9/19/2015.
 */

import com.exodia.mail.model.Mail;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

public class Mailer {
    private MailSender mailSender;

    private VelocityEngine velocityEngine;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void sendMail(Mail mail, Map<String, String> mailAttribute) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(mail.getMailFrom());
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getMailSubject());

        Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());

        VelocityContext velocityContext = new VelocityContext();
        Set<Map.Entry<String, String>> entrySet = mailAttribute.entrySet();
        for (Map.Entry entry : entrySet) {
            velocityContext.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }

        StringWriter stringWriter = new StringWriter();

        template.merge(velocityContext, stringWriter);

        message.setText(stringWriter.toString());

        mailSender.send(message);
    }
}