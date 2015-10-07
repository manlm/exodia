package com.exodia.bom.controller;

import com.exodia.bom.config.Properties;
import com.exodia.bom.service.CommonService;
import com.exodia.bom.service.DataService;
import com.exodia.database.entity.PlayerAccount;
import com.exodia.database.entity.PlayerScore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView viewPlayerAccount(@ModelAttribute(value = "deleteSuccess") String deleteSuccess) {
        LOG.info("[viewPlayerAccount] Start");
        ModelAndView model = new ModelAndView("viewPlayerAccount");
        List<PlayerAccount> list = dataService.getAllPlayerAccount();
        model.addObject("accountList", list);

        // Delete account success
        if (!deleteSuccess.equals("")) {
            model.addObject("popup", "deleteSuccess");
        }

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

    /**
     * Delete player account
     *
     * @param email
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/deletePlayerAccount", method = RequestMethod.POST)
    public ModelAndView deletePlayerAccount(@RequestParam(name = "email") String email,
                                            RedirectAttributes redirectAttributes) {
        LOG.info("[deletePlayerAccount] Start");
        ModelAndView model = new ModelAndView("redirect:viewPlayerAccount");

        // Delete success
        if (dataService.deletePlayerAccount(email)) {
            commonService.saveAccessLog(String.valueOf(new StringBuilder(properties.getProperty("log_delete_player_account"))
                    .append(", email = ").append(email)));
            redirectAttributes.addFlashAttribute("deleteSuccess", true);
        }

        LOG.info("[deletePlayerAccount] End");
        return model;
    }

    @RequestMapping(value = "/viewDetailPlayerAccount", method = RequestMethod.GET)
    public ModelAndView viewDetailPlayerAccount(@RequestParam(name = "email") String email) {

        LOG.info("[viewDetailPlayerAccount] Start");

        ModelAndView model = new ModelAndView("viewDetailPlayerAccount");
        PlayerAccount account = dataService.getByEmail(email);
        List<PlayerScore> scoreList = account.getPlayerScoreList();

        model.addObject("account", account);
        model.addObject("scoreList", scoreList);

        LOG.info("[viewDetailPlayerAccount] End");
        return model;
    }
}
