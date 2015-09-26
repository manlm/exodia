package com.exodia.webservice.controller;

import com.exodia.webservice.business.Authentication;
import com.exodia.webservice.model.RegisterModel;
import com.exodia.webservice.response.ForgotPasswordResponse;
import com.exodia.webservice.response.RegisterResponse;
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
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private Authentication auth;

    @RequestMapping(value = "/call/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = false) String password) {

        return auth.doLogin(username, password);
    }

    @RequestMapping(value = "/call/register", method = RequestMethod.POST)
    @ResponseBody
    public RegisterResponse register(@RequestParam(value = "email") String email,
                                     @RequestParam(value = "password") String password,
                                     HttpServletResponse response) {


        response.setHeader("Access-Control-Allow-Origin", "*");
        return auth.doRegister(email, password);
    }
}
