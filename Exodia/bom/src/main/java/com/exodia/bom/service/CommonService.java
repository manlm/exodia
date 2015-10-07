package com.exodia.bom.service;

import com.exodia.common.util.DateTimeUtil;
import com.exodia.database.dao.AdminAccessLogDAO;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.entity.AdminAccessLog;
import com.exodia.database.entity.AdminAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * Created by manlm1 on 10/6/2015.
 */
@Service
public class CommonService {

    private static final Logger LOG = Logger.getLogger(CommonService.class);

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    @Autowired
    private AdminAccessLogDAO adminAccessLogDAO;

    /**
     * Get admin account by username
     *
     * @param username
     * @return
     */
    public AdminAccount getAdminAccountByUsername(String username) {
        LOG.info(new StringBuilder("[getAdminAccount] Start: username = ").append(username));
        LOG.info("[getAdminAccount] End");
        return adminAccountDAO.getByUsername(username);
    }

    /**
     * Save access log of admin account
     *
     * @param action
     */
    public void saveAccessLog(String action) {

        LOG.info(new StringBuilder("[saveAccessLog] Start: action = ").append(action));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminAccount account = getAdminAccountByUsername(user.getUsername());
        AdminAccessLog accessLog = new AdminAccessLog();
        accessLog.setAdminAccountAccessLog(account);
        accessLog.setAction(action);
        accessLog.setTime(DateTimeUtil.getCurUTCInMilliseconds());
        adminAccessLogDAO.save(accessLog);

        LOG.info("[saveAccessLog] End");
    }
}
