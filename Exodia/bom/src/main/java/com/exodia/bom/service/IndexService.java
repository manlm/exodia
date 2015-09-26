package com.exodia.bom.service;

import com.exodia.bom.config.Properties;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.entity.AdminAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by manlm1 on 9/24/2015.
 */
@Service
public class IndexService {

    private static final String RESET_PASSWORD_EMMAIL_TEMPLATE = "reset-password.vm";

    private static final Logger LOG = Logger.getLogger(IndexService.class);

    @Autowired
    private Properties properties;

    @Autowired
    private AccountService accountService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    public boolean forgotPassword(String email) {

        LOG.info(new StringBuilder("[forgotPassword] Start: email = ").append(email));

        AdminAccount account = adminAccountDAO.getByEmail(email);
        boolean result = true;
        if (account != null) {
            String password = accountService.resetPassword(account);
            result = mailService.sendMail(email, RESET_PASSWORD_EMMAIL_TEMPLATE
                    , properties.getProperty("subject_reset_password"), account.getUsername(), password);
        }

        LOG.info("[forgotPassword End");
        return result;
    }

}
