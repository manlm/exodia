package com.exodia.bom.controller;

import com.exodia.bom.service.AccountService;
import com.exodia.bom.service.CSVService;
import com.exodia.bom.service.CommonService;
import com.exodia.common.util.PasswordUtil;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.dao.UserRolesDAO;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.UserRoles;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by manlm1 on 9/20/2015.
 */
@Controller
public class AccountController {

    private static final Logger LOG = Logger.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/viewAdminAccount", method = RequestMethod.GET)
    public ModelAndView viewAdminAccount(@ModelAttribute(value = "sendEmailSuccess") String sendEmailSuccess,
                                         @ModelAttribute(value = "deleteSuccess") String deleteSuccess) {
        LOG.info("[viewAdminAccount] Start");
        ModelAndView model = new ModelAndView("viewAdminAccount");
        List<AdminAccount> list = accountService.getAllAdminAccount();
        model.addObject("accountList", list);

        if (!sendEmailSuccess.equals("")) {
            model.addObject("popup", "sendEmailSuccess");
        }

        if (!deleteSuccess.equals("")) {
            model.addObject("popup", "deleteSuccess");
        }

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
    public ModelAndView viewAddAdminAccount(@ModelAttribute(value = "success") String success) {
        LOG.info("[viewAddAdminAccount] Start");
        ModelAndView model = new ModelAndView("addAdminAccount");
        List<UserRoles> list = accountService.getAllRole();
        model.addObject("roleList", list);
        if (success != null) {
            model.addObject(success);
        }
        LOG.info("[viewAddAdminAccount] End");
        return model;
    }

    @RequestMapping(value = "/addAdminAccount", method = RequestMethod.POST)
    public ModelAndView addAdminAccount(@RequestParam(name = "username") String username,
                                        @RequestParam(name = "email") String email,
                                        @RequestParam(name = "role") String role,
                                        RedirectAttributes redirectAttributes) {
        LOG.info("[addAdminAccount] Start");
        ModelAndView model = new ModelAndView("redirect:viewAddAdminAccount");
        if (accountService.addAdminAccount(username, email, role)) {
            redirectAttributes.addFlashAttribute("success", true);
        }
        LOG.info("[addAdminAccount] End");
        return model;
    }

    @RequestMapping(value = "/resendEmail", method = RequestMethod.POST)
    public ModelAndView resendEmail(@RequestParam(name = "username") String username,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView model = new ModelAndView("redirect:viewAdminAccount");
        if (accountService.resendEmail(username)) {
            redirectAttributes.addFlashAttribute("sendEmailSuccess", true);
        }
        return model;
    }

    @RequestMapping(value = "/viewEditAdminAccount", method = RequestMethod.GET)
    public ModelAndView viewEditAdminAccount(@RequestParam(name = "username") String username) {
        LOG.info("[viewEditAdminAccount] Start");
        ModelAndView model = new ModelAndView("viewEditAdminAccount");
        AdminAccount account = commonService.getAdminAccountByUsername(username);
        model.addObject("account", account);
        LOG.info("[viewEditAdminAccount] End");
        return model;
    }

    @RequestMapping(value = "/deleteAdminAccount", method = RequestMethod.POST)
    public ModelAndView deleteAdminAccount(@RequestParam(name = "username") String username,
                                           RedirectAttributes redirectAttributes) {
        LOG.info("[deleteAdminAccount] Start");
        ModelAndView model = new ModelAndView("redirect:viewAdminAccount");
        if (accountService.deleteAdminAccount(username)) {
            redirectAttributes.addFlashAttribute("deleteSuccess", true);
        }
        LOG.info("[deleteAdminAccount] End");
        return model;
    }
}
