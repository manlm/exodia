package com.exodia.bom.service;

import com.exodia.bom.config.Properties;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.common.util.MD5Util;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.UserStatus;
import com.exodia.mail.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by manlm1 on 9/24/2015.
 */
@Service
public class IndexService {

    private static final Logger LOG = Logger.getLogger(IndexService.class);

    @Autowired
    private Properties properties;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    @Autowired
    private ValidService validService;

    /**
     * Get admin account by username
     *
     * @param username
     * @return
     */
    public AdminAccount getByUsername(String username) {
        return adminAccountDAO.getByUsername(username);
    }

    /**
     * Send email reset password of Admin Account
     *
     * @param email
     * @return
     */
    public boolean forgotPassword(String email) {

        LOG.info(new StringBuilder("[forgotPassword] Start: email = ").append(email));

        AdminAccount account = adminAccountDAO.getByEmail(email);
        boolean result = true;
        if (account != null) {
            String password = accountService.resetPassword(account);
            result = mailService.sendMail(email, properties.getProperty("template_reset_password")
                    , properties.getProperty("subject_reset_password"), account.getUsername(), password);
        }

        LOG.info("[forgotPassword End");
        return result;
    }

    /**
     * Update Admin Account's profile
     *
     * @param username
     * @param email
     * @param password
     * @param newPassword
     * @param confirmPassword
     * @return 2 wrong password, 1 update success, 0 update failed
     */
    public int updateProfile(String username, String email, String password, String newPassword,
                             String confirmPassword) {

        LOG.info(new StringBuilder("[updateProfile] Start: username = ").append(username)
                .append(", email = ").append(email));

        AdminAccount account = adminAccountDAO.getByUsername(username);

        if (validService.isEmailExisted(email.toLowerCase(), username)) {
            LOG.info("[updateAdminAccount] End");
            return 3;
        }

        if (!MD5Util.stringToMD5(password).equals(account.getPassword())) {
            LOG.info("[updateProfile] End");
            return 2;
        }


        account.setEmail(email);

        if (!newPassword.equals("")) {
            account.setPassword(MD5Util.stringToMD5(newPassword));
        }

        account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
        account = adminAccountDAO.update(account);

        if (account != null) {
            LOG.info("[updateProfile] End");
            return 1;
        }

        LOG.info("[updateProfile] End");
        return 0;
    }

    /**
     * Update account when 1st login
     *
     * @param username
     * @param password
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    public int updateFirstLogin(String username, String password, String newPassword, String confirmPassword) {

        LOG.info(new StringBuilder("[updateFirstLogin] Start: username = ").append(username));

        AdminAccount account = adminAccountDAO.getByUsername(username);

        if (!MD5Util.stringToMD5(password).equals(account.getPassword())) {
            LOG.info("[updateFirstLogin] End");
            return 2;
        }

        account.setPassword(MD5Util.stringToMD5(newPassword));

        UserStatus status = new UserStatus();
        status.setId(Constant.STATUS_ID.ACTIVE.getValue());
        account.setStatus(status);
        account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
        account = adminAccountDAO.update(account);

        if (account != null) {
            LOG.info("[updateFirstLogin] End");
            return 1;
        }

        LOG.info("[updateFirstLogin] End");
        return 0;
    }
}
