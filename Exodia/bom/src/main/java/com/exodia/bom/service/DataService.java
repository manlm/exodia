package com.exodia.bom.service;

import com.exodia.bom.config.Properties;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.database.dao.PlayerAccountDAO;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.PlayerAccount;
import com.exodia.database.entity.PlayerScore;
import com.exodia.database.entity.UserStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by manlm1 on 10/6/2015.
 */
@Service
public class DataService {

    private static final Logger LOG = Logger.getLogger(DataService.class);

    @Autowired
    private Properties properties;

    @Autowired
    private PlayerAccountDAO playerAccountDAO;

    @Autowired
    private CSVService csvService;

    /**
     * Get all Player Account
     *
     * @return
     */
    public List<PlayerAccount> getAllPlayerAccount() {
        LOG.info("[getAllPlayerAccount] Start");
        LOG.info("[getAllPlayerAccount] End");
        return playerAccountDAO.getAll();
    }

    /**
     * Export CSV
     *
     * @param email
     * @param status
     * @param response
     */
    public void exportPlayer(String email, String status, HttpServletResponse response) {

        LOG.info(new StringBuilder("[exportPlayer] Start: email = ").append(email)
                .append(", status = ").append(status));

        List<PlayerAccount> list = playerAccountDAO.getByConditions(email, status);

        StringBuilder header = new StringBuilder(properties.getProperty("header_no")).append(",")
                .append(properties.getProperty("header_email")).append(",")
                .append(properties.getProperty("header_status")).append(",")
                .append(properties.getProperty("header_creation_time")).append(",")
                .append(properties.getProperty("header_last_update")).append(",")
                .append("\n");

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            PlayerAccount account = list.get(i);

            content.append(i + 1).append(",");

            content.append(account.getEmail()).append(",");

            int accountStatus = account.getStatus().getId();
            if (accountStatus == Constant.STATUS_ID.ACTIVE.getValue()) {
                content.append(Constant.STATUS.ACTIVE.getValue()).append(",");
            } else if (accountStatus == Constant.STATUS_ID.DELETED.getValue()) {
                content.append(Constant.STATUS.DELETED.getValue()).append(",");
            } else {
                content.append("").append(",");
            }

            content.append(DateTimeUtil.getDate(account.getCreationTime()
                    , properties.getProperty("date_time_format"))).append(",");

            content.append(DateTimeUtil.getDate(account.getLastUpdate()
                    , properties.getProperty("date_time_format"))).append(",");

            content.append("\n");
        }

        csvService.exportCSV(properties.getProperty("file_player_account"), header, content, response);
        LOG.info("[exportPlayer] End");
    }

    /**
     * Delete Player Account
     *
     * @param email
     * @return
     */
    public boolean deletePlayerAccount(String email) {

        LOG.info(new StringBuilder("[deleteAdminAccount] Start: email = ").append(email));

        PlayerAccount account = playerAccountDAO.getByEmail(email);
        UserStatus status = new UserStatus();
        status.setId(Constant.STATUS_ID.DELETED.getValue());
        account.setStatus(status);
        account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
        account = playerAccountDAO.update(account);
        if (account != null) {
            LOG.info("[deleteAdminAccount] End");
            return true;
        }
        LOG.info("[deletePlayerAccount] End");
        return false;
    }

    /**
     * Get player account by email
     *
     * @param email
     * @return
     */
    public PlayerAccount getByEmail(String email) {
        LOG.info(new StringBuilder("[getByEmail] Start: email = ").append(email));
        LOG.info("getByEmail End");
        return playerAccountDAO.getByEmail(email);
    }
}
