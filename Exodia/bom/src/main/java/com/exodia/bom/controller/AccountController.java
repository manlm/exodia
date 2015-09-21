package com.exodia.bom.controller;

import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.entity.AdminAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by manlm1 on 9/20/2015.
 */
@Controller
public class AccountController {

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    @RequestMapping(value = "/viewAdminAccount", method = RequestMethod.GET)
    public ModelAndView viewAdminAccount() {
        ModelAndView model = new ModelAndView("viewAdminAccount");
        List<AdminAccount> list = adminAccountDAO.getAll();
        model.addObject("accountList", list);
        return model;
    }
}
