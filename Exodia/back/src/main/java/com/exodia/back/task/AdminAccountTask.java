package com.exodia.back.task;

import com.exodia.back.config.Properties;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.entity.AdminAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminAccountTask {

    private static final Logger LOG = Logger.getLogger(AdminAccountTask.class);

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    private Properties properties = new Properties();

    public void doDeleteAdmin() {

        LOG.info("[doDeleteAdmin] Start");

        List<AdminAccount> list = adminAccountDAO.getByStatus(Constant.STATUS_ID.DELETED.getValue());

        long dateRange = Long.valueOf(properties.getProperty("date_range"));

        for (int i = 0; i < list.size(); i++) {
            AdminAccount account = list.get(i);
            if (DateTimeUtil.getCurUTCInMilliseconds() - account.getLastUpdate() >= dateRange) {
                adminAccountDAO.remove(account);
            }
        }

        LOG.info("[doDeleteAdmin] End");
    }
}