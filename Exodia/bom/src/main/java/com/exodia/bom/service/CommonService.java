package com.exodia.bom.service;

import com.exodia.bom.config.Properties;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.database.dao.AdminAccessLogDAO;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.dao.UserRolesDAO;
import com.exodia.database.dao.UserStatusDAO;
import com.exodia.database.entity.AdminAccessLog;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.UserRoles;
import com.exodia.database.entity.UserStatus;
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
    private Properties properties;

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    @Autowired
    private AdminAccessLogDAO adminAccessLogDAO;

    @Autowired
    private UserStatusDAO userStatusDAO;

    @Autowired
    private UserRolesDAO userRolesDAO;

    @Autowired
    private AccountService accountService;

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

    /**
     * Initial User Status
     */
    public void initialUserStatus() {

        LOG.info("[initialUserStatus] Start");

        UserStatus statusActive = new UserStatus();
        statusActive.setId(Constant.STATUS_ID.ACTIVE.getValue());
        statusActive.setStatus(Constant.STATUS.ACTIVE.getValue());
        userStatusDAO.saveOrUpdate(statusActive);

        UserStatus statusInactive = new UserStatus();
        statusInactive.setId(Constant.STATUS_ID.INACTIVE.getValue());
        statusInactive.setStatus(Constant.STATUS.INACTIVE.getValue());
        userStatusDAO.saveOrUpdate(statusInactive);

        UserStatus statusDeleted = new UserStatus();
        statusDeleted.setId(Constant.STATUS_ID.DELETED.getValue());
        statusDeleted.setStatus(Constant.STATUS.DELETED.getValue());
        userStatusDAO.saveOrUpdate(statusDeleted);

        LOG.info("[initialUserStatus] End");
    }

    public void initialUserRoles() {

        LOG.info("[initialUserRoles] Start");

        UserRoles rolesAccount = new UserRoles();
        rolesAccount.setId(Constant.ADMIN_ROLE_ID.ACCOUNT_MANAGER.getValue());
        rolesAccount.setRole(Constant.ADMIN_ROLE.ACCOUNT_MANAGER.getValue());
        userRolesDAO.saveOrUpdate(rolesAccount);

        UserRoles rolesData = new UserRoles();
        rolesData.setId(Constant.ADMIN_ROLE_ID.DATA_MANAGER.getValue());
        rolesData.setRole(Constant.ADMIN_ROLE.DATA_MANAGER.getValue());
        userRolesDAO.saveOrUpdate(rolesData);

        LOG.info("[initialUserRoles] End");
    }

    /**
     * Initial 1st Account Manager
     */
    public void initial1stAccount() {

        LOG.info("[initial1stAccount] Start");

        AdminAccount account = adminAccountDAO.getByUsername(properties.getProperty("initial_username"));
        if (account == null) {
            accountService.addAdminAccount(properties.getProperty("initial_username"),
                    properties.getProperty("initial_email"),
                    String.valueOf(Constant.ADMIN_ROLE_ID.ACCOUNT_MANAGER.getValue()));
        }

        LOG.info("[initial1stAccount] End");
    }
}
