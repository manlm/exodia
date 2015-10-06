package com.exodia.bom.handler;


import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by manlm1 on 9/13/2015.
 */
public class LogoutHandler implements LogoutSuccessHandler {

    private static final Logger LOG = Logger.getLogger(LogoutHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        LOG.info(new StringBuilder("[onLogoutSuccess] Start: username = ").append(username));
        LOG.info("[onLogoutSuccess] End");
        response.sendRedirect("login?loggedUsername=" + username);
    }
}
