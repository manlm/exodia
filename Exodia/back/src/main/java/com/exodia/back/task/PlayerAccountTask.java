package com.exodia.back.task;

import com.exodia.back.config.Properties;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.database.dao.PlayerAccountDAO;
import com.exodia.database.entity.PlayerAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by manlm1 on 10/9/2015.
 */
@Service
public class PlayerAccountTask {

    private static final Logger LOG = Logger.getLogger(PlayerAccountTask.class);

    @Autowired
    private PlayerAccountDAO playerAccountDAO;

    private Properties properties = new Properties();

    public void doDeletePlayer() {

        LOG.info("[doDeletePlayer] Start");

        List<PlayerAccount> list = playerAccountDAO.getByStatus(Constant.STATUS_ID.DELETED.getValue());

        long dateRange = Long.valueOf(properties.getProperty("admin_date_range"));

        for (int i = 0; i < list.size(); i++) {
            PlayerAccount account = list.get(i);
            if (DateTimeUtil.getCurUTCInMilliseconds() - account.getLastUpdate() >= dateRange) {
                playerAccountDAO.remove(account);
            }
        }

        LOG.info("[doDeletePlayer] End");
    }
}
