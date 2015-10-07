package com.exodia.bom.handler;

import com.exodia.bom.config.Properties;
import com.exodia.bom.service.CommonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by manlm1 on 9/13/2015.
 */
public class LoginFailedHandler implements AuthenticationFailureHandler {

    private static final Logger LOG = Logger.getLogger(LoginFailedHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        String username = request.getParameter("username");
        LOG.info(new StringBuilder("[onAuthenticationFailure] Start: username = ").append(username));
        LOG.info("[onAuthenticationFailure] End");
        response.sendRedirect("login?error=error&loggedUsername=" + username);
    }
}
