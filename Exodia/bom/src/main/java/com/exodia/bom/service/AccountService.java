package com.exodia.bom.service;

import com.exodia.bom.config.Properties;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.common.util.PasswordUtil;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.entity.AdminAccount;
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

    /**
     * Reset password of an account
     *
     * @param email
     * @return
     */
    public void resetPassword(String email) {
        LOG.info(new StringBuilder("[resetPassword] Start: email = ").append(email));

        AdminAccount adminAccount = adminAccountDAO.getByEmail(email);
        adminAccount.setPassword(String.valueOf(PasswordUtil.generatePswd()));
        adminAccount.setLastUpdate(System.currentTimeMillis());
        adminAccountDAO.update(adminAccount);

        LOG.info("[resetPassword] End");
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

            int accountRole = adminAccount.getRole();
            if (accountRole == Constant.ADMIN_ROLE_ID.ACCOUNT_MANAGER.getValue()) {
                content.append(Constant.ADMIN_ROLE.ACCOUNT_MANAGER.getValue()).append(",");
            } else if (accountRole == Constant.ADMIN_ROLE_ID.DATA_MANAGER.getValue()) {
                content.append(Constant.ADMIN_ROLE.DATA_MANAGER.getValue()).append(",");
            } else {
                content.append("").append(",");
            }

            int accountStatus = adminAccount.getStatus();
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
}
