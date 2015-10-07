package com.exodia.bom.controller;

import com.exodia.bom.config.Properties;
import com.exodia.bom.service.AccountService;
import com.exodia.bom.service.CommonService;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.UserRoles;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    private Properties properties;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommonService commonService;

    /**
     * View Admin Account Page
     *
     * @param sendEmailSuccess
     * @param deleteSuccess
     * @return
     */
    @RequestMapping(value = "/viewAdminAccount", method = RequestMethod.GET)
    public ModelAndView viewAdminAccount(@ModelAttribute(value = "sendEmailSuccess") String sendEmailSuccess,
                                         @ModelAttribute(value = "deleteSuccess") String deleteSuccess) {
        LOG.info("[viewAdminAccount] Start");
        ModelAndView model = new ModelAndView("viewAdminAccount");
        List<AdminAccount> list = accountService.getAllAdminAccount();
        model.addObject("accountList", list);

        // Send activation email success
        if (!sendEmailSuccess.equals("")) {
            model.addObject("popup", "sendEmailSuccess");
        }

        // Delete account success
        if (!deleteSuccess.equals("")) {
            model.addObject("popup", "deleteSuccess");
        }

        LOG.info("[viewAdminAccount] End");
        return model;
    }

    /**
     * Export CSV
     *
     * @param username
     * @param email
     * @param role
     * @param status
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportAdmin", method = RequestMethod.POST)
    @ResponseBody
    public void exportAdmin(@RequestParam(name = "txtSearchUsername") String username,
                            @RequestParam(name = "txtSearchEmail") String email,
                            @RequestParam(name = "txtSearchRole") String role,
                            @RequestParam(name = "txtSearchStatus") String status,
                            HttpServletResponse response) {
        LOG.info("[exportAdmin] Start");
        accountService.exportAdmin(username, email, role, status, response);

        commonService.saveAccessLog(String.valueOf(new StringBuilder(properties.getProperty("log_export_admin_account"))
                .append(" username = ").append(username)
                .append(", email = ").append(email)
                .append(", role = ").append(role)
                .append(", status = ").append(status)));

        LOG.info("[exportAdmin] End");
    }

    /**
     * View Add Admin Account Page
     *
     * @param success
     * @param emailExisted
     * @param enteredUsername
     * @return
     */
    @RequestMapping(value = "/viewAddAdminAccount", method = RequestMethod.GET)
    public ModelAndView viewAddAdminAccount(@ModelAttribute(value = "success") String success,
                                            @ModelAttribute(value = "emailExisted") String emailExisted,
                                            @ModelAttribute(value = "enteredUsername") String enteredUsername) {
        LOG.info("[viewAddAdminAccount] Start");
        ModelAndView model = new ModelAndView("addAdminAccount");
        List<UserRoles> list = accountService.getAllRole();
        model.addObject("roleList", list);

        // Add success
        if (!success.equals("")) {
            model.addObject(success);
        }

        // Email existed
        if (!emailExisted.equals("")) {
            model.addObject(emailExisted);
        }

        // Save entered email for display
        if (!enteredUsername.equals("")) {
            model.addObject(enteredUsername);
        }

        LOG.info("[viewAddAdminAccount] End");
        return model;
    }

    /**
     * Add Admin Account
     *
     * @param username
     * @param email
     * @param role
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/addAdminAccount", method = RequestMethod.POST)
    public ModelAndView addAdminAccount(@RequestParam(name = "username") String username,
                                        @RequestParam(name = "email") String email,
                                        @RequestParam(name = "role") String role,
                                        RedirectAttributes redirectAttributes) {
        LOG.info("[addAdminAccount] Start");
        ModelAndView model = new ModelAndView("redirect:viewAddAdminAccount");

        int result = accountService.addAdminAccount(username, email, role);

        // Email existed
        if (result == 2) {
            redirectAttributes.addFlashAttribute("emailExisted", email);
            redirectAttributes.addFlashAttribute("enteredUsername", username);
            return model;
        }

        // Add success
        if (result == 1) {
            commonService.saveAccessLog(String.valueOf(new StringBuilder(properties.getProperty("log_add_admin_account"))
                    .append(", username = ").append(username)));
            redirectAttributes.addFlashAttribute("success", true);
            return model;
        }

        LOG.info("[addAdminAccount] End");
        return model;
    }

    /**
     * Resend activation email for an Admin Account
     *
     * @param username
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/resendEmail", method = RequestMethod.POST)
    public ModelAndView resendEmail(@RequestParam(name = "username") String username,
                                    RedirectAttributes redirectAttributes) {

        LOG.info("[resendEmail] Start");

        ModelAndView model = new ModelAndView("redirect:viewAdminAccount");

        // Send success
        if (accountService.resendEmail(username)) {
            commonService.saveAccessLog(String.valueOf(new StringBuilder(properties.getProperty("log_resend_email"))
                    .append(", username = ").append(username)));
            redirectAttributes.addFlashAttribute("sendEmailSuccess", true);
        }

        LOG.info("[resendEmail] End");
        return model;
    }

    /**
     * View Edit Page of an Admin Account
     *
     * @param username
     * @param emailExisted
     * @param success
     * @return
     */
    @RequestMapping(value = "/viewEditAdminAccount", method = RequestMethod.GET)
    public ModelAndView viewEditAdminAccount(@RequestParam(name = "username") String username,
                                             @ModelAttribute(value = "emailExisted") String emailExisted,
                                             @ModelAttribute(value = "success") String success) {
        LOG.info("[viewEditAdminAccount] Start");
        ModelAndView model = new ModelAndView("viewEditAdminAccount");
        AdminAccount account = commonService.getAdminAccountByUsername(username);

        model.addObject("account", account);

        // Email existed
        if (!emailExisted.equals("")) {
            model.addObject(emailExisted);
        }

        // Update success
        if (!success.equals("")) {
            model.addObject(success);
        }

        LOG.info("[viewEditAdminAccount] End");
        return model;
    }

    /**
     * Update an Admin Account
     *
     * @param username
     * @param email
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/updateAdminAccount", method = RequestMethod.POST)
    public ModelAndView updateAdminAccount(@RequestParam(name = "username") String username,
                                           @RequestParam(name = "email") String email,
                                           RedirectAttributes redirectAttributes) {
        LOG.info("[updateAdminAccount] Start");
        ModelAndView model = new ModelAndView("redirect:viewEditAdminAccount?username=" + username);

        int result = accountService.updateAdminAccount(username, email);

        // Email existed
        if (result == 2) {
            redirectAttributes.addFlashAttribute("emailExisted", email);
            return model;
        }

        // Update success
        if (result == 1) {
            commonService.saveAccessLog(String.valueOf(new StringBuilder(properties.getProperty("log_update_admin_account"))
                    .append(", username = ").append(username)));
            redirectAttributes.addFlashAttribute("success", true);
            return model;
        }

        LOG.info("[updateAdminAccount] End");
        return model;
    }

    /**
     * Delete an Admin Account
     *
     * @param username
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/deleteAdminAccount", method = RequestMethod.POST)
    public ModelAndView deleteAdminAccount(@RequestParam(name = "username") String username,
                                           RedirectAttributes redirectAttributes) {
        LOG.info("[deleteAdminAccount] Start");
        ModelAndView model = new ModelAndView("redirect:viewAdminAccount");

        // Delete success
        if (accountService.deleteAdminAccount(username)) {
            commonService.saveAccessLog(String.valueOf(new StringBuilder(properties.getProperty("log_delete_admin_account"))
                    .append(", username = ").append(username)));
            redirectAttributes.addFlashAttribute("deleteSuccess", true);
        }

        LOG.info("[deleteAdminAccount] End");
        return model;
    }

    /**
     * Download Access Log of an Admin Account
     *
     * @param username
     */
    @RequestMapping(value = "/downloadAccessLog", method = RequestMethod.POST)
    @ResponseBody
    public void downloadAccessLog(@RequestParam(name = "username") String username,
                                  HttpServletResponse response) {

        LOG.info("[downloadAccessLog] Start");

        accountService.exportAccessLog(username, response);

        commonService.saveAccessLog(String.valueOf(new StringBuilder(properties.getProperty("log_access_log_admin_account"))
                .append(", username = ").append(username)));
        LOG.info("[downloadAccessLog] End");
    }
}
