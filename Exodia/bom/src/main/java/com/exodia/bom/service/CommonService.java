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
public class CommonService {

    private static final Logger LOG = Logger.getLogger(CommonService.class);

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    public AdminAccount getAdminAccountByUsername(String username) {
        LOG.info(new StringBuilder("[getAdminAccount] Start: username = ").append(username));
        LOG.info("[getAdminAccount] End");
        return adminAccountDAO.getByUsername(username);
    }
}
