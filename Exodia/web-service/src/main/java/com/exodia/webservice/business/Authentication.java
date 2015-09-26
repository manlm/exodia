package com.exodia.webservice.business;

import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.common.util.IdUtil;
import com.exodia.common.util.MD5Util;
import com.exodia.common.util.MemcachedClient;
import com.exodia.database.dao.PlayerAccountDAO;
import com.exodia.database.entity.PlayerAccount;
import com.exodia.webservice.model.LoginModel;
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
        response.setMessage("Successful");
        response.setStatusCode("01");

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
            response.setMessage("Invalid email or password");
            response.setStatusCode("00");
            LOG.info("[doLogin] End");
            return response;
        }

        if (!MD5Util.stringToMD5(password).equals(account.getPassword())) {
            LOG.info("[doLogin] password does not match");
            response.setMessage("Invalid email or password");
            response.setStatusCode("00");
            LOG.info("[doLogin] End");
            return response;
        }

        String sessionId = IdUtil.generateId();
        memcachedClient.set(email, sessionId, 2592000);
        model.setSessionId(sessionId);
        response.setData(model);
        response.setMessage("Successful");
        response.setStatusCode("01");
        LOG.info("[doLogin] End");
        return response;
    }

    public ReauthorizeResponse doReauthorize(String email, String sessionId) {
        ReauthorizeResponse response = new ReauthorizeResponse();
        return response;
    }
}
