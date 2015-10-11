package com.exodia.webservice.controller;

import com.exodia.mail.service.MailService;
import com.exodia.webservice.business.Authentication;
import com.exodia.webservice.response.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * Register new account
     *
     * @param email
     * @param password
     * @param response
     * @return
     */
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

    /**
     * Login
     *
     * @param email
     * @param password
     * @param response
     * @return
     */
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

    /**
     * Reauthorize
     *
     * @param email
     * @param sessionId
     * @param response
     * @return
     */
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

    /**
     * Reset password
     *
     * @param email
     * @param response
     * @return
     */
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    @ResponseBody
    public ForgotPasswordResponse forgotPassword(@RequestParam(value = "email") String email,
                                                 HttpServletResponse response) {
        LOG.info("[forgotPassword] Start");
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOG.info("[forgotPassword] End");
        return auth.doForgotPassword(email);
    }

    /**
     * Sync Player Score
     *
     * @return
     */
    @RequestMapping(value = "syncData", method = RequestMethod.POST)
    @ResponseBody
    public SyncDataResponse synData(@RequestParam(value = "email") String email,
                                    @RequestParam(value = "score") String score,
                                    HttpServletResponse response) {
        LOG.info("[synData] Start");
        response.setHeader("Access-Control-Allow-Origin", "*");
        LOG.info("[synData] End");
        return auth.doSyncData(email, Integer.valueOf(score));
    }

    // TODO remove
    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    @ResponseBody
    public String sendMail() {
        mailService.sendMail("manlmse61239@fpt.edu.vn", "admin-activate.vm", "Reset Password", "manlm", "new password");
        return "Sent email";
    }
}
