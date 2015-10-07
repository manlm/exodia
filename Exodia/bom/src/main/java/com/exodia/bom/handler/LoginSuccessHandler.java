package com.exodia.bom.handler;

import com.exodia.bom.config.Properties;
import com.exodia.bom.service.CommonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by manlm1 on 9/13/2015.
 */

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOG = Logger.getLogger(LoginSuccessHandler.class);

    @Autowired
    private Properties properties;

    @Autowired
    private CommonService commonService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        LOG.info("[onAuthenticationSuccess] Start");

        HttpSession session = request.getSession();
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());

        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);

        commonService.saveAccessLog(properties.getProperty("log_login_success"));

        LOG.info("[onAuthenticationSuccess] End");
        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        response.sendRedirect("main");
    }
}
