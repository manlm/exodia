package com.exodia.bom.service;

import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.entity.AdminAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by manlm1 on 10/6/2015.
 */
@Service
public class ValidService {

    private static final Logger LOG = Logger.getLogger(ValidService.class);

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    public boolean isEmailExisted(String email, String username) {

        LOG.info(new StringBuilder("[isEmailExisted] Start: email = ").append(email));

        AdminAccount account = adminAccountDAO.getByEmail(email);
        if (account != null && !account.getUsername().equals(username)) {
            LOG.info("[isEmailExisted] End");
            return true;
        }
        LOG.info("[isEmailExisted] End");
        return false;
    }
}
