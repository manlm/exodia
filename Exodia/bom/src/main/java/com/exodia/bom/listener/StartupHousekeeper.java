package com.exodia.bom.listener;

import com.exodia.bom.config.Properties;
import com.exodia.bom.service.AccountService;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.common.util.MD5Util;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.dao.UserRolesDAO;
import com.exodia.database.dao.UserStatusDAO;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.UserRoles;
import com.exodia.database.entity.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Created by manlm1 on 10/14/2015.
 */
@Service
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Properties properties;

    @Autowired
    private UserStatusDAO userStatusDAO;

    @Autowired
    private UserRolesDAO userRolesDAO;

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    @Autowired
    private AccountService accountService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
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

        UserRoles rolesAccount = new UserRoles();
        rolesAccount.setId(Constant.ADMIN_ROLE_ID.ACCOUNT_MANAGER.getValue());
        rolesAccount.setRole(Constant.ADMIN_ROLE.ACCOUNT_MANAGER.getValue());
        userRolesDAO.saveOrUpdate(rolesAccount);

        UserRoles rolesData = new UserRoles();
        rolesData.setId(Constant.ADMIN_ROLE_ID.DATA_MANAGER.getValue());
        rolesData.setRole(Constant.ADMIN_ROLE.DATA_MANAGER.getValue());
        userRolesDAO.saveOrUpdate(rolesData);


        accountService.addAdminAccount(properties.getProperty("initial_username"),
                properties.getProperty("initial_email"), String.valueOf(Constant.ADMIN_ROLE_ID.ACCOUNT_MANAGER.getValue()));

//        AdminAccount account = adminAccountDAO.getByUsername(properties.getProperty("initial_username"));
//        if (account == null) {
//            account = new AdminAccount();
//            account.setUsername(properties.getProperty("initial_username"));
//            account.setEmail(properties.getProperty("initial_email"));
//            account.setCreationTime(DateTimeUtil.getCurUTCInMilliseconds());
//            account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
//            account.setRole(rolesAccount);
//            account.setStatus(statusActive);
//            account.setPassword(MD5Util.stringToMD5("Superbuu1803#"));
//            adminAccountDAO.save(account);
//        } else {
//            account.setUsername(properties.getProperty("initial_username"));
//            account.setEmail(properties.getProperty("initial_email"));
//            account.setCreationTime(DateTimeUtil.getCurUTCInMilliseconds());
//            account.setLastUpdate(DateTimeUtil.getCurUTCInMilliseconds());
//            account.setRole(rolesAccount);
//            account.setStatus(statusActive);
//            account.setPassword(MD5Util.stringToMD5("Superbuu1803#"));
//            adminAccountDAO.update(account);
//        }

    }
}