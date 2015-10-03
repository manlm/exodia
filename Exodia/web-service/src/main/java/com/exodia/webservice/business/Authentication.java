package com.exodia.webservice.business;

import com.exodia.common.constant.Constant;
import com.exodia.common.util.*;
import com.exodia.database.dao.PlayerAccountDAO;
import com.exodia.database.entity.PlayerAccount;
import com.exodia.webservice.config.Properties;
import com.exodia.webservice.model.ForgotPasswordModel;
import com.exodia.webservice.model.LoginModel;
import com.exodia.webservice.model.ReauthorizeModel;
import com.exodia.webservice.model.RegisterModel;
import com.exodia.webservice.response.LoginResponse;
import com.exodia.webservice.response.ReauthorizeResponse;
import com.exodia.webservice.response.RegisterResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by manlm1 on 9/8/2015.
 */
@Service
public class Authentication {

    private static final Logger LOG = Logger.getLogger(Authentication.class);

    @Autowired
    private Properties properties;

    @Autowired
    private PlayerAccountDAO playerAccountDAO;

    @Autowired
    private MemcachedClient memcachedClient;

    /**
     * Register
     *
     * @param email
     * @param password
     * @return
     */
    public RegisterResponse doRegister(String email, String password) {

        LOG.info(new StringBuilder("[doRegister] Start: email = ").append(email).append(", password").append(password));

        RegisterResponse response = new RegisterResponse();
        RegisterModel model = new RegisterModel();
        PlayerAccount account = new PlayerAccount();

        account.setEmail(email);
        account.setPassword(MD5Util.stringToMD5(password));
        account.setStatus(Constant.STATUS_ID.ACTIVE.getValue());
        account.setCreationTime(DateTimeUtil.getCurUTCInMilliseconds());
        account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
        playerAccountDAO.save(account);

        model.setEmail(email);
        response.setData(model);
        response.setStatusCode(properties.getProperty("status_code_success"));

        LOG.info("[doRegister] End");
        return response;
    }

    /**
     * Login
     *
     * @param email
     * @param password
     * @return
     */
    public LoginResponse doLogin(String email, String password) {

        LOG.info(new StringBuilder("[doLogin] Start: email = ").append(email).append(", password = ").append(password));

        LoginResponse response = new LoginResponse();
        LoginModel model = new LoginModel();
        PlayerAccount account = playerAccountDAO.getByEmail(email);
        if (account == null) {
            LOG.info("[doLogin] account == null");
            response.setStatusCode(properties.getProperty("status_code_failed"));
            LOG.info("[doLogin] End");
            return response;
        }

        if (!MD5Util.stringToMD5(password).equals(account.getPassword())) {
            LOG.info("[doLogin] password does not match");
            response.setMessage("Invalid email or password");
            response.setStatusCode(properties.getProperty("status_code_failed"));
            LOG.info("[doLogin] End");
            return response;
        }

        String sessionId = IdUtil.generateId();
        memcachedClient.set(email, sessionId, Integer.valueOf(properties.getProperty("cache_alive_time")));
        model.setSessionId(sessionId);
        response.setData(model);
        response.setStatusCode(properties.getProperty("status_code_success"));
        LOG.info("[doLogin] End");
        return response;
    }

    /**
     * Reauthorize
     *
     * @param email
     * @param sessionId
     * @return
     */
    public ReauthorizeResponse doReauthorize(String email, String sessionId) {

        LOG.info(new StringBuilder("[doReauthorize] Start: email = ").append(email)
                .append(", sessionId = ").append(sessionId));

        ReauthorizeResponse response = new ReauthorizeResponse();
        ReauthorizeModel model = new ReauthorizeModel();

        if (memcachedClient.get(email).equals(sessionId)) {
            LOG.info("[doReauthorize] Session exist");
            model.setEmail(email);
            response.setData(model);
            response.setStatusCode(properties.getProperty("status_code_success"));
            LOG.info("[doReauthorize] End");
            return response;
        }

        LOG.info("[doReauthorize] Session does not exist");
        response.setStatusCode(properties.getProperty("status_code_failed"));
        LOG.info("[doReauthorize] End");
        return response;
    }

//    public ForgotPasswordModel doForgotPassword(String email) {
//        PlayerAccount account = playerAccountDAO.getByEmail(email);
//        String password = MD5Util.stringToMD5(String.valueOf(PasswordUtil.generatePswd()));
//        account.setPassword(password);
//
//    }
}
