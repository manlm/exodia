package com.exodia.bom.service;

import com.exodia.bom.config.Properties;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.common.util.MD5Util;
import com.exodia.common.util.PasswordUtil;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.dao.UserRolesDAO;
import com.exodia.database.entity.AdminAccessLog;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.UserRoles;
import com.exodia.database.entity.UserStatus;
import com.exodia.mail.service.MailService;
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

    @Autowired
    private MailService mailService;

    @Autowired
    private ValidService validService;

    /**
     * Get all Admin Account
     *
     * @return
     */
    public List<AdminAccount> getAllAdminAccount() {
        LOG.info("[getAllAdminAccount] Start");
        LOG.info("[getAllAdminAccount] End");
        return adminAccountDAO.getAll();
    }

    /**
     * Get all User Role
     *
     * @return
     */
    public List<UserRoles> getAllRole() {
        LOG.info("[getAllRole] Start");
        LOG.info("[getAllRole] End");
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

        StringBuilder header = new StringBuilder(properties.getProperty("header_no")).append(",")
                .append(properties.getProperty("header_username")).append(",")
                .append(properties.getProperty("header_email")).append(",")
                .append(properties.getProperty("header_role")).append(",")
                .append(properties.getProperty("header_status")).append(",")
                .append(properties.getProperty("header_creation_time")).append(",")
                .append(properties.getProperty("header_last_update")).append(",")
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

        csvService.exportCSV(properties.getProperty("file_admin_account"), header, content, response);
        LOG.info("[exportAdmin] End");
    }

    /**
     * Add a new Admin Account
     *
     * @param username
     * @param email
     * @param role
     * @return 2 Email existed, 1 add success, 0 add failed
     */
    public int addAdminAccount(String username, String email, String role) {
        LOG.info(new StringBuilder("[addAdminAccount] Start: username = ").append(username)
                .append(", email = ").append(email).append(", role = ").append(role));

        if (validService.isEmailExisted(email.toLowerCase(), username)) {
            LOG.info("[addAdminAccount] End");
            return 2;
        }

        AdminAccount account = new AdminAccount();
        UserRoles roles = new UserRoles();
        roles.setId(Integer.valueOf(role));
        UserStatus status = new UserStatus();
        status.setId(Constant.STATUS_ID.INACTIVE.getValue());

        account.setUsername(username);
        account.setEmail(email.toLowerCase());
        account.setPassword(MD5Util.stringToMD5(String.valueOf(PasswordUtil.generatePswd())));
        account.setCreationTime(DateTimeUtil.getCurUTCInMilliseconds());
        account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
        account.setRole(roles);
        account.setStatus(status);
        if (adminAccountDAO.save(account) != null) {
            if (resendEmail(account.getUsername())) {
                LOG.info("[addAdminAccount] End");
                return 1;
            }
        }
        LOG.info("[addAdminAccount] End");
        return 0;
    }

    /**
     * Resend active email for a Admin Account
     *
     * @param username
     * @return
     */
    public boolean resendEmail(String username) {

        LOG.info(new StringBuilder("[resendEmail] Start: username = ").append(username));

        AdminAccount account = adminAccountDAO.getByUsername(username);
        boolean result = true;
        if (account != null) {
            String password = resetPassword(account);
            result = mailService.sendMail(account.getEmail(), properties.getProperty("template_resend_active")
                    , properties.getProperty("subject_admin_active"), account.getUsername(), password);
        }

        LOG.info("[resendEmail] Ennd");
        return result;
    }

    /**
     * Delete an Admin Account
     *
     * @param username
     * @return
     */
    public boolean deleteAdminAccount(String username) {

        LOG.info(new StringBuilder("[deleteAdminAccount] Start: username = ").append(username));

        AdminAccount account = adminAccountDAO.getByUsername(username);
        UserStatus status = new UserStatus();
        status.setId(Constant.STATUS_ID.DELETED.getValue());
        account.setStatus(status);
        account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
        account = adminAccountDAO.update(account);
        if (account != null) {
            LOG.info("[deleteAdminAccount] End");
            return true;
        }
        LOG.info("[deleteAdminAccount] End");
        return false;
    }

    /**
     * Update admin account
     *
     * @param username
     * @param email
     * @return
     */
    public int updateAdminAccount(String username, String email) {

        LOG.info(new StringBuilder("[updateAdminAccount] Start: username = ").append(username)
                .append(", email = ").append(email));

        if (validService.isEmailExisted(email.toLowerCase(), username)) {
            LOG.info("[updateAdminAccount] End");
            return 2;
        }

        AdminAccount account = adminAccountDAO.getByUsername(username);
        account.setEmail(email);
        account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
        account = adminAccountDAO.update(account);

        if (account != null) {
            LOG.info("[updateAdminAccount] End");
            return 1;
        }

        LOG.info("[updateAdminAccount] End");
        return 0;
    }

    /**
     * Export Access Log of an Admin Account
     *
     * @param username
     */
    public void exportAccessLog(String username, HttpServletResponse response) {

        LOG.info(new StringBuilder("[exportAccessLog] Start: username = ").append(username));

        AdminAccount account = adminAccountDAO.getByUsername(username);
        List<AdminAccessLog> list = account.getAdminAccessLogList();

        StringBuilder header = new StringBuilder(properties.getProperty("header_no")).append(",")
                .append(properties.getProperty("header_time")).append(",")
                .append(properties.getProperty("header_action")).append(",")
                .append("\n");

        String filename = String.valueOf(new StringBuilder(username).append("-")
                .append(properties.getProperty("file_admin_access_log")));

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            AdminAccessLog accessLog = list.get(i);

            content.append(i + 1).append(",");

            content.append(DateTimeUtil.getDate(accessLog.getTime(), properties.getProperty("date_time_format")))
                    .append(",");

            content.append(accessLog.getAction()).append(",");

            content.append("\n");
        }

        csvService.exportCSV(filename, header, content, response);
        LOG.info("[exportAccessLog] End");
    }
}
