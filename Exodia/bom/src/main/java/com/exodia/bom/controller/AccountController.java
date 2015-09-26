package com.exodia.bom.controller;

import com.exodia.bom.service.AccountService;
import com.exodia.bom.service.CSVService;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.entity.AdminAccount;
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
    public ModelAndView exportAdmin(@RequestParam(name = "txtSearchUsername") String username
            , @RequestParam(name = "txtSearchEmail") String email
            , @RequestParam(name = "txtSearchRole") String role
            , @RequestParam(name = "txtSearchStatus") String status
            , HttpServletResponse response) {
        LOG.info("[exportAdmin] Start");
        ModelAndView model = new ModelAndView("viewAdminAccount");
        accountService.exportAdmin(username, email, role, status, response);
        LOG.info("[exportAdmin] End");
        return model;
    }
}
