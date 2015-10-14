package com.exodia.bom.listener;

import com.exodia.bom.config.Properties;
import com.exodia.bom.service.AccountService;
import com.exodia.bom.service.CommonService;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.common.util.MD5Util;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.dao.UserRolesDAO;
import com.exodia.database.dao.UserStatusDAO;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.UserRoles;
import com.exodia.database.entity.UserStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Created by manlm1 on 10/14/2015.
 */
@Service
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = Logger.getLogger(StartupHousekeeper.class);

    @Autowired
    private CommonService commonService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        LOG.info("[onApplicationEvent] Start");

        commonService.initialUserStatus();
        commonService.initialUserRoles();
        commonService.initial1stAccount();

        LOG.info("[onApplicationEvent] End");
    }
}