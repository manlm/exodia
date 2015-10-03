package com.exodia.webservice.controller;

import com.exodia.mail.service.MailService;
import com.exodia.webservice.business.Authentication;
import com.exodia.webservice.model.LoginModel;
import com.exodia.webservice.model.RegisterModel;
import com.exodia.webservice.response.ForgotPasswordResponse;
import com.exodia.webservice.response.LoginResponse;
import com.exodia.webservice.response.ReauthorizeResponse;
import com.exodia.webservice.response.RegisterResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by manlm1 on 9/8/2015.
 */
@Controller
public class AuthenticationController {

    private static final Logger LOG = Logger.getLogger(AuthenticationController.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private Authentication auth;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public RegisterResponse register(@RequestParam(value = "email") String email,
                                     @RequestParam(value = "password") String password,
                                     HttpServletResponse response) {
        LOG.info("[register] Start");
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOG.info("[register] End");
        return auth.doRegister(email, password);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResponse login(@RequestParam(value = "email") String email,
                               @RequestParam(value = "password") String password,
                               HttpServletResponse response) {
        LOG.info("[login] Start");
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOG.info("[login] End");
        return auth.doLogin(email, password);
    }

    @RequestMapping(value = "/reauthorize", method = RequestMethod.POST)
    @ResponseBody
    public ReauthorizeResponse reauthorize(@RequestParam(value = "email") String email,
                                           @RequestParam(value = "sessionId") String sessionId,
                                           HttpServletResponse response) {
        LOG.info("[reauthorize] Start");
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOG.info("[reauthorize] End");
        return auth.doReauthorize(email, sessionId);
    }

    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    @ResponseBody
    public String sendMail() {
        mailService.sendMail("manlmse61239@fpt.edu.vn", "admin-activate.vm", "Reset Password", "manlm", "new password");
        return "Sent email";
    }
}
