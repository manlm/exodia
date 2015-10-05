package com.exodia.bom.service;

import com.exodia.bom.config.Properties;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.common.util.MD5Util;
import com.exodia.common.util.PasswordUtil;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.dao.UserRolesDAO;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.UserRoles;
import com.exodia.database.entity.UserStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by manlm1 on 9/21/2015.
 */

@Service
public class AccountService {

    private static final Logger LOG = Logger.getLogger(AccountService.class);

    @Autowired
    private Properties properties;

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    @Autowired
    private CSVService csvService;

    @Autowired
    private UserRolesDAO userRolesDAO;

    /**
     * Get all Admin Account
     *
     * @return
     */
    public List<AdminAccount> getAllAdminAccount() {
        return adminAccountDAO.getAll();
    }

    /**
     * Get all User Role
     *
     * @return
     */
    public List<UserRoles> getAllRole() {
        return userRolesDAO.getAll();
    }

    /**
     * Reset password of an account
     *
     * @param adminAccount
     * @return
     */
    public String resetPassword(AdminAccount adminAccount) {
        LOG.info(new StringBuilder("[resetPassword] Start: adminAccount username = ").append(adminAccount.getUsername()));
        String password = String.valueOf(PasswordUtil.generatePswd());

        adminAccount.setPassword(MD5Util.stringToMD5(password));
        adminAccount.setLastUpdate(System.currentTimeMillis());
        adminAccountDAO.update(adminAccount);

        LOG.info("[resetPassword] End");
        return password;
    }

    /**
     * Export CSV file
     *
     * @param username
     * @param email
     * @param role
     * @param status
     */
    public void exportAdmin(String username, String email, String role, String status, HttpServletResponse response) {

        LOG.info(new StringBuilder("[exportAdmin] Start: username = ").append(username)
                .append(", email = ").append(email)
                .append(", role = ").append(role)
                .append(", status = ").append(status));

        List<AdminAccount> list = adminAccountDAO.getByConditions(username, email, role, status);

        StringBuilder header = new StringBuilder("No").append(",")
                .append("Username").append(",")
                .append("Email").append(",")
                .append("Role").append(",")
                .append("Status").append(",")
                .append("Creation Time").append(",")
                .append("Last Update").append(",")
                .append("\n");

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            AdminAccount adminAccount = list.get(i);

            content.append(i + 1).append(",");

            content.append(adminAccount.getUsername()).append(",");

            content.append(adminAccount.getEmail()).append(",");

            int accountRole = adminAccount.getRole().getId();
            if (accountRole == Constant.ADMIN_ROLE_ID.ACCOUNT_MANAGER.getValue()) {
                content.append(Constant.ADMIN_ROLE.ACCOUNT_MANAGER.getValue()).append(",");
            } else if (accountRole == Constant.ADMIN_ROLE_ID.DATA_MANAGER.getValue()) {
                content.append(Constant.ADMIN_ROLE.DATA_MANAGER.getValue()).append(",");
            } else {
                content.append("").append(",");
            }

            int accountStatus = adminAccount.getStatus().getId();
            if (accountStatus == Constant.STATUS_ID.ACTIVE.getValue()) {
                content.append(Constant.STATUS.ACTIVE.getValue()).append(",");
            } else if (accountStatus == Constant.STATUS_ID.INACTIVE.getValue()) {
                content.append(Constant.STATUS.INACTIVE.getValue()).append(",");
            } else if (accountStatus == Constant.STATUS_ID.DELETED.getValue()) {
                content.append(Constant.STATUS.DELETED.getValue()).append(",");
            } else {
                content.append("").append(",");
            }

            content.append(DateTimeUtil.getDate(adminAccount.getCreationTime()
                    , properties.getProperty("date_time_format"))).append(",");

            content.append(DateTimeUtil.getDate(adminAccount.getLastUpdate()
                    , properties.getProperty("date_time_format"))).append(",");

            content.append("\n");
        }

        csvService.exportCSV("admin_account", header, content, response);
        LOG.info("[exportAdmin] End");
    }

    /**
     * Add a new Admin Account
     *
     * @param username
     * @param email
     * @param password
     * @param confirmPassword
     * @param role
     * @return
     */
    public boolean addAdminAccount(String username, String email, String password, String confirmPassword, String role) {
        LOG.info(new StringBuilder("[addAdminAccount] Start: username = ").append(username)
                .append(", email = ").append(email).append(", role = ").append(role));

        AdminAccount account = new AdminAccount();
        UserRoles roles = new UserRoles();
        roles.setId(Integer.valueOf(role));
        UserStatus status = new UserStatus();
        status.setId(Constant.STATUS_ID.INACTIVE.getValue());

        account.setUsername(username);
        account.setEmail(email);
        account.setPassword(password);
        account.setCreationTime(DateTimeUtil.getCurUTCInMilliseconds());
        account.setRole(roles);
        account.setStatus(status);
        if (adminAccountDAO.save(account) != null) {
            LOG.info("[addAdminAccount] End");
            return true;
        }
        LOG.info("[addAdminAccount] End");
        return false;
    }
}
