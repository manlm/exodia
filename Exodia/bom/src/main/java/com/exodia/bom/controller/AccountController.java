package com.exodia.bom.controller;

import com.exodia.bom.service.AccountService;
import com.exodia.bom.service.CSVService;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.dao.UserRolesDAO;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.UserRoles;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by manlm1 on 9/20/2015.
 */
@Controller
public class AccountController {

    private static final Logger LOG = Logger.getLogger(AccountController.class);

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CSVService csvService;

    @Autowired
    private UserRolesDAO userRolesDAO;

    @RequestMapping(value = "/viewAdminAccount", method = RequestMethod.GET)
    public ModelAndView viewAdminAccount() {
        LOG.info("[viewAdminAccount] Start");
        ModelAndView model = new ModelAndView("viewAdminAccount");
        List<AdminAccount> list = adminAccountDAO.getAll();
        model.addObject("accountList", list);
        LOG.info("[viewAdminAccount] End");
        return model;
    }

    @RequestMapping(value = "/exportAdmin", method = RequestMethod.POST)
    public ModelAndView exportAdmin(@RequestParam(name = "txtSearchUsername") String username,
                                    @RequestParam(name = "txtSearchEmail") String email,
                                    @RequestParam(name = "txtSearchRole") String role,
                                    @RequestParam(name = "txtSearchStatus") String status,
                                    HttpServletResponse response) {
        LOG.info("[exportAdmin] Start");
        ModelAndView model = new ModelAndView("viewAdminAccount");
        accountService.exportAdmin(username, email, role, status, response);
        LOG.info("[exportAdmin] End");
        return model;
    }

    @RequestMapping(value = "/viewAddAdminAccount", method = RequestMethod.GET)
    public ModelAndView viewAddAdminAccount() {
        LOG.info("[viewAddAdminAccount] Start");
        ModelAndView model = new ModelAndView("addAdminAccount");
        List<UserRoles> list = userRolesDAO.getAll();
        model.addObject("roleList", list);
        LOG.info("[viewAddAdminAccount] End");
        return model;
    }

    @RequestMapping(value = "/addAdminAccount", method = RequestMethod.POST)
    public ModelAndView addAdminAccount(@RequestParam(name = "username") String username,
                                        @RequestParam(name = "email") String email,
                                        @RequestParam(name = "password") String password,
                                        @RequestParam(name = "confirmPassword") String confirmPassword,
                                        @RequestParam(name = "role") String role) {
        LOG.info("[addAdminAccount] Start");
        ModelAndView model = new ModelAndView("addAdminAccount");

        LOG.info("[addAdminAccount] End");
        return model;
    }
}
