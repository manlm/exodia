package com.exodia.bom.controller;

import com.exodia.bom.service.DataService;
import com.exodia.database.entity.PlayerAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by manlm1 on 10/6/2015.
 */
@Controller
public class DataController {

    private static final Logger LOG = Logger.getLogger(DataController.class);

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/viewPlayerAccount", method = RequestMethod.GET)
    public ModelAndView viewPlayerAccount() {
        LOG.info("[viewPlayerAccount] Start");
        ModelAndView model = new ModelAndView("viewPlayerAccount");
        List<PlayerAccount> list = dataService.getAllPlayerAccount();
        model.addObject("accountList", list);
        LOG.info("[viewPlayerAccount] End");
        return model;
    }
}
