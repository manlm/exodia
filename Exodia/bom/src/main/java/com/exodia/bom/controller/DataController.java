package com.exodia.bom.controller;

import com.exodia.bom.config.Properties;
import com.exodia.bom.service.CommonService;
import com.exodia.bom.service.DataService;
import com.exodia.database.entity.PlayerAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by manlm1 on 10/6/2015.
 */
@Controller
public class DataController {

    private static final Logger LOG = Logger.getLogger(DataController.class);

    @Autowired
    private Properties properties;

    @Autowired
    private DataService dataService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/viewPlayerAccount", method = RequestMethod.GET)
    public ModelAndView viewPlayerAccount() {
        LOG.info("[viewPlayerAccount] Start");
        ModelAndView model = new ModelAndView("viewPlayerAccount");
        List<PlayerAccount> list = dataService.getAllPlayerAccount();
        model.addObject("accountList", list);
        LOG.info("[viewPlayerAccount] End");
        return model;
    }

    /**
     * Export CSV
     *
     * @param email
     * @param status
     * @param response
     */
    @RequestMapping(value = "/exportPlayer", method = RequestMethod.POST)
    @ResponseBody
    public void exportPlayer(@RequestParam(name = "txtSearchEmail") String email,
                            @RequestParam(name = "txtSearchStatus") String status,
                            HttpServletResponse response) {
        LOG.info("[exportAdmin] Start");
        dataService.exportPlayer(email, status, response);

        commonService.saveAccessLog(String.valueOf(new StringBuilder(properties.getProperty("log_export_player_account"))
                .append(", email = ").append(email)
                .append(", status = ").append(status)));

        LOG.info("[exportAdmin] End");
    }
}
