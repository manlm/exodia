package com.exodia.webservice.controller;

import com.exodia.webservice.business.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
