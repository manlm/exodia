package com.exodia.bom.controller;

import com.exodia.bom.config.Properties;
import com.exodia.bom.service.CommonService;
import com.exodia.bom.service.DataService;
import com.exodia.common.util.DateTimeUtil;
import com.exodia.database.entity.Highscore;
import com.exodia.database.entity.PlayerAccount;
import com.exodia.database.entity.PlayerScore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
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

    /**
     * View detail of a player account
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/viewDetailPlayerAccount", method = RequestMethod.GET)
    public ModelAndView viewDetailPlayerAccount(@RequestParam(name = "email") String email) {

        LOG.info("[viewDetailPlayerAccount] Start");

        ModelAndView model = new ModelAndView("viewDetailPlayerAccount");
        PlayerAccount account = dataService.getByEmail(email);
        List<PlayerScore> scoreList = account.getPlayerScoreList();
        Collections.sort(scoreList);
        model.addObject("account", account);
        model.addObject("scoreList", scoreList);

        LOG.info("[viewDetailPlayerAccount] End");
        return model;
    }

    @RequestMapping(value = "/viewSummaryData", method = RequestMethod.GET)
    public ModelAndView viewSummaryData() {

        LOG.info("[viewSummaryData] Start");

        ModelAndView model = new ModelAndView("viewSummaryData");

        int curYear = DateTimeUtil.getCurrentYear();
        List<Long> list1st = dataService.getAllMonthTotalPlay(curYear);
        List<Long> list2nd = dataService.getAllMonthTotalPlay(curYear - 1);
        List<Long> list3rd = dataService.getAllMonthTotalPlay(curYear - 2);

        model.addObject("curYear", curYear);
        model.addObject("list1st", list1st);
        model.addObject("list2nd", list2nd);
        model.addObject("list3rd", list3rd);

        LOG.info("[viewSummaryData] End");
        return model;
    }

    @RequestMapping(value = "/viewDetailByMonth", method = RequestMethod.GET)
    public ModelAndView viewDetailByMonth(@RequestParam(name = "month") String month,
                                          @RequestParam(name = "year") String year) {

        LOG.info("[viewDetailByMonth] Start");

        ModelAndView model = new ModelAndView("viewDetailByMonth");

        List<Highscore> scoreList = dataService.getHighScoreOfMonth(month, year);

        if (Integer.valueOf(month) < 10) {
            month = "0" + month;
        }

        model.addObject("month", month);
        model.addObject("year", year);
        model.addObject("scoreList", scoreList);

        LOG.info("[viewDetailByMonth] End");
        return model;
    }
}
