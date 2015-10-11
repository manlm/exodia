package com.exodia.webservice.business;

import com.exodia.common.constant.Constant;
import com.exodia.common.util.*;
import com.exodia.database.dao.PlayerAccountDAO;
import com.exodia.database.dao.PlayerScoreDAO;
import com.exodia.database.entity.PlayerAccount;
import com.exodia.database.entity.PlayerScore;
import com.exodia.database.entity.UserStatus;
import com.exodia.mail.service.MailService;
import com.exodia.webservice.config.Properties;
import com.exodia.webservice.model.*;
import com.exodia.webservice.response.*;
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
    private PlayerScoreDAO playerScoreDAO;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private MailService mailService;

    /**
     * Register
     *
     * @param email
     * @param password
     * @return
     */
    public RegisterResponse doRegister(String email, String password) {

        LOG.info(new StringBuilder("[doRegister] Start: email = ").append(email));

        RegisterResponse response = new RegisterResponse();
        RegisterModel model = new RegisterModel();

        PlayerAccount playerAccount = playerAccountDAO.getByEmail(email);

        if (playerAccount != null) {
            model.setEmail(email);
            response.setData(model);
            response.setMessage("");
            response.setStatusCode(properties.getProperty("status_code_failed"));
            return response;
        }

        PlayerAccount account = new PlayerAccount();

        account.setEmail(email);
        account.setPassword(MD5Util.stringToMD5(password));

        UserStatus status = new UserStatus();
        status.setId(Constant.STATUS_ID.ACTIVE.getValue());
        account.setStatus(status);

        account.setCreationTime(DateTimeUtil.getCurUTCInMilliseconds());
        account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
        playerAccountDAO.save(account);

        model.setEmail(email);
        response.setData(model);
        response.setMessage("");
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

        LOG.info(new StringBuilder("[doLogin] Start: email = ").append(email));

        LoginResponse response = new LoginResponse();
        LoginModel model = new LoginModel();
        PlayerAccount account = playerAccountDAO.getByEmail(email);
        if (account == null) {
            LOG.info("[doLogin] account == null");
            model.setSessionId("");
            response.setData(model);
            response.setMessage("");
            response.setStatusCode(properties.getProperty("status_code_failed"));
            LOG.info("[doLogin] End");
            return response;
        }

        if (!MD5Util.stringToMD5(password).equals(account.getPassword())) {
            LOG.info("[doLogin] password does not match");
            model.setSessionId("");
            response.setData(model);
            response.setMessage("");
            response.setStatusCode(properties.getProperty("status_code_failed"));
            LOG.info("[doLogin] End");
            return response;
        }

        String sessionId = IdUtil.generateId();
        memcachedClient.set(email, sessionId, Integer.valueOf(properties.getProperty("cache_alive_time")));
        model.setSessionId(sessionId);
        response.setData(model);
        response.setMessage("");
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
        if (!email.equals("")) {
            String cached = memcachedClient.get(email);
            if (cached != null) {
                if (cached.equals(sessionId)) {
                    LOG.info("[doReauthorize] Session exist");
                    String newSessionId = IdUtil.generateId();
                    memcachedClient.set(email, newSessionId, Integer.valueOf(properties.getProperty("cache_alive_time")));
                    model.setEmail(email);
                    model.setSessionId(newSessionId);
                    response.setData(model);
                    response.setMessage("");
                    response.setStatusCode(properties.getProperty("status_code_success"));
                    LOG.info("[doReauthorize] End");
                    return response;
                }
            }
        }

        LOG.info("[doReauthorize] Session does not exist");
        model.setEmail("");
        response.setData(model);
        response.setMessage("");
        response.setStatusCode(properties.getProperty("status_code_failed"));
        LOG.info("[doReauthorize] End");
        return response;
    }

    /**
     * Reset password for an Player Account
     *
     * @param email
     * @return
     */
    public ForgotPasswordResponse doForgotPassword(String email) {

        LOG.info(new StringBuilder("[doForgotPassword] Start: email = ").append(email));

        ForgotPasswordResponse response = new ForgotPasswordResponse();
        ForgotPasswordModel model = new ForgotPasswordModel();
        model.setEmail(email);
        response.setData(model);
        response.setMessage("");
        response.setStatusCode(properties.getProperty("status_code_success"));

        PlayerAccount account = playerAccountDAO.getByEmail(email);
        if (account != null) {
            LOG.info("[doForgotPassword] Email exist");
            String password = String.valueOf(PasswordUtil.generatePswd());
            account.setPassword(MD5Util.stringToMD5(password));
            account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
            playerAccountDAO.update(account);
            mailService.sendMail(email, properties.getProperty("mail_reset_password")
                    , properties.getProperty("mail_subject_reset_password"), account.getEmail(), password);
        }

        LOG.info("[doForgotPassword] End");
        return response;
    }

    /**
     * Sync Player Score
     *
     * @param email
     * @param score
     * @return
     */
    public SyncDataResponse doSyncData(String email, int score) {

        LOG.info(new StringBuilder("[doSyncData] Start: email = ").append(email)
                .append(", score = ").append(score));

        SyncDataResponse response = new SyncDataResponse();
        SyncDataModel model = new SyncDataModel();

        PlayerAccount account = playerAccountDAO.getByEmail(email);

        PlayerScore playerScore = new PlayerScore();
        playerScore.setPlayerAccount(account);
        playerScore.setPlayTime(DateTimeUtil.getCurUTCInMilliseconds());
        playerScore.setScore(score);

        playerScore = playerScoreDAO.save(playerScore);

        if (playerScore != null) {
            model.setScore(score);
            response.setMessage("");
            response.setData(model);
            response.setStatusCode(properties.getProperty("status_code_success"));

            LOG.info("[doSyncData] End");
            return response;
        }

        response.setStatusCode(properties.getProperty("status_code_failed"));
        LOG.info("[doSyncData] End");
        return response;
    }

    /**
     * Change password
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public UpdateProfileResponse doUpdateProfile(String email, String oldPassword, String newPassword) {

        LOG.info(new StringBuilder("[doUpdateProfile] Start: email = ").append(email));

        UpdateProfileResponse response = new UpdateProfileResponse();
        UpdateProfileModel model = new UpdateProfileModel();
        PlayerAccount account = playerAccountDAO.getByEmail(email);

        model.setEmail(email);
        response.setData(model);

        if (!MD5Util.stringToMD5(oldPassword).equals(account.getPassword())) {
            response.setStatusCode(properties.getProperty("status_code_failed"));
            return response;
        }

        account.setPassword(MD5Util.stringToMD5(newPassword));
        account = playerAccountDAO.update(account);

        if (account != null) {
            response.setStatusCode(properties.getProperty("status_code_success"));
            LOG.info("[doUpdateProfile] End");
            return response;
        }

        response.setStatusCode(properties.getProperty("status_code_failed"));
        LOG.info("[doUpdateProfile] End");
        return response;
    }
}
