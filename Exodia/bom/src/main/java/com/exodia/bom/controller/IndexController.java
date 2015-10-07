package com.exodia.bom.controller;

import com.exodia.bom.config.Properties;
import com.exodia.bom.service.CommonService;
import com.exodia.bom.service.IndexService;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.MemcachedClient;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.dao.PlayerAccountDAO;
import com.exodia.database.entity.AdminAccount;
import com.exodia.database.entity.PlayerAccount;
import com.exodia.database.entity.UserRoles;
import com.exodia.database.entity.UserStatus;
import com.exodia.mail.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.MapsId;
import java.security.Principal;

@Controller
public class IndexController {

    private static final Logger LOG = Logger.getLogger(IndexController.class);

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    @Autowired
    private PlayerAccountDAO playerAccountDAO;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private Properties properties;

    @Autowired
    private MailService mailService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(name = "loggedUsername", required = false) String loggedUsername,
                              @RequestParam(name = "error", required = false) String error,
                              Principal principal) {
        LOG.info("[login] Start");
        ModelAndView model = new ModelAndView("login");

        if (principal != null) {
            model = new ModelAndView("redirect:main");
            return model;
        }

        if (error != null) {
            model.addObject("error", true);
        }

        if (loggedUsername != null) {
            model.addObject("loggedUsername", loggedUsername);
        }

        LOG.info("[login] End");
        return model;
    }

    @RequestMapping(value = "/showForgotPassword", method = RequestMethod.GET)
    public ModelAndView showForgotPassword(@ModelAttribute(value = "success") String success) {
        LOG.info("[showForgotPassword] Start");
        ModelAndView model = new ModelAndView("forgotPassword");
        model.addObject(success);
        LOG.info("[showForgotPassword] End");
        return model;
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPassword(@RequestParam(name = "email") String email,
                                 RedirectAttributes redirectAttributes) {
        LOG.info("[forgotPassword] Start");
        if (indexService.forgotPassword(email)) {
            redirectAttributes.addFlashAttribute("success", true);
        }
        LOG.info("[forgotPassword] End");
        return "redirect:showForgotPassword";
    }

    @RequestMapping(value = "/viewFirstLogin", method = RequestMethod.GET)
    public ModelAndView viewFirstLogin(@RequestParam(name = "username") String username,
                                       @ModelAttribute(value = "updateResult") String updateResult) {

        LOG.info("[viewFirstLogin] Start");

        ModelAndView model = new ModelAndView("firstLogin");
        AdminAccount account = indexService.getByUsername(username);
        model.addObject("account", account);
        model.addObject(updateResult);

        LOG.info("[viewFirstLogin] End");
        return model;
    }

    @RequestMapping(value = "/updateFirstLogin", method = RequestMethod.POST)
    public ModelAndView updateFirstLogin(@RequestParam(name = "username") String username,
                                         @RequestParam(name = "oldPassword") String password,
                                         @RequestParam(name = "newPassword") String newPassword,
                                         @RequestParam(name = "confirmPassword") String confirmPassword,
                                         RedirectAttributes redirectAttributes) {

        LOG.info("[updateFirstLogin] Start");

        ModelAndView model = new ModelAndView("redirect:viewFirstLogin?username=" + username);

        int result = indexService.updateFirstLogin(username, password, newPassword, confirmPassword);

        if (result == 2) {
            redirectAttributes.addFlashAttribute("updateResult", "wrongPassword");
            return model;
        }

        if (result == 1) {
            redirectAttributes.addFlashAttribute("updateResult", "success");
            return model;
        }

        LOG.info("[updateFirstLogin] End");
        return model;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {

        LOG.info("[main] Start");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = user.getUsername();
        int status = adminAccountDAO.getByUsername(username).getStatus().getId();

        if (status == Constant.STATUS_ID.INACTIVE.getValue()) {
            LOG.info("[main] End: Inactive");
            return "redirect:viewFirstLogin?username=" + username;
        }

        String role = String.valueOf(user.getAuthorities().iterator().next());
        if (role.equals(String.valueOf(Constant.ADMIN_ROLE.ACCOUNT_MANAGER))) {
            LOG.info(new StringBuilder("[main] End: role = ").append(role));
            return "redirect:viewAdminAccount";
        } else if (role.equals(String.valueOf(Constant.ADMIN_ROLE.DATA_MANAGER))) {
            LOG.info(new StringBuilder("[main] End: role = ").append(role));
            return "redirect:viewPlayerAccount";
        }
        LOG.info(new StringBuilder("[main] End: role = ").append(role));
        return "redirect:login";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView model = new ModelAndView("hello");
        return model;
    }

    @RequestMapping(value = "/viewMyProfile", method = RequestMethod.GET)
    public ModelAndView viewMyProfile(@RequestParam(name = "username") String username,
                                      @ModelAttribute(value = "emailExisted") String emailExisted,
                                      @ModelAttribute(value = "updateResult") String updateResult) {
        LOG.info("[viewMyProfile] Start");
        ModelAndView model = new ModelAndView("myProfile");
        AdminAccount account = commonService.getAdminAccountByUsername(username);
        model.addObject("account", account);

        if (!emailExisted.equals("")) {
            model.addObject(emailExisted);
            LOG.info("[viewMyProfile] End");
            return model;
        }

        model.addObject(updateResult);
        LOG.info("[viewMyProfile] End");
        return model;
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public ModelAndView updateProfile(@RequestParam(name = "username") String username,
                                      @RequestParam(name = "email") String email,
                                      @RequestParam(name = "oldPassword") String password,
                                      @RequestParam(name = "newPassword", required = false) String newPassword,
                                      @RequestParam(name = "confirmPassword", required = false) String confirmPassword,
                                      RedirectAttributes redirectAttributes) {
        LOG.info("[updateProfile] Start");
        ModelAndView model = new ModelAndView("redirect:viewMyProfile?username=" + username);
        int result = indexService.updateProfile(username, email, password, newPassword, confirmPassword);

        if (result == 3) {
            redirectAttributes.addFlashAttribute("emailExisted", email);
            LOG.info("[updateProfile] End");
            return model;
        }

        if (result == 2) {
            redirectAttributes.addFlashAttribute("updateResult", "wrongPassword");
            LOG.info("[updateProfile] End");
            return model;
        }

        if (result == 1) {
            redirectAttributes.addFlashAttribute("updateResult", "success");
            LOG.info("[updateProfile] End");
            return model;
        }

        LOG.info("[updateProfile] End");
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("message", "Hello world!");

        UserRoles roles = new UserRoles();
        roles.setId(1);

        UserStatus status = new UserStatus();
        status.setId(1);

        for (int i = 101; i <= 200; i++) {
            System.out.println(i);
            AdminAccount adminAccount = new AdminAccount();
            adminAccount.setUsername("test" + i);
            adminAccount.setPassword("c4f4652fb4cfcc7756e3b2b97019955a");
            adminAccount.setRole(roles);
            adminAccount.setCreationTime(0);
            adminAccount.setLastUpdate(0);
            adminAccount.setStatus(status);
            adminAccountDAO.save(adminAccount);
        }

        return "hello";
    }

    @RequestMapping(value = "/addPlayer", method = RequestMethod.GET)
    public String addPlayer(ModelMap model) {
        model.addAttribute("message", "Hello world!");

        UserStatus status = new UserStatus();
        status.setId(1);

        for (int i = 101; i <= 200; i++) {
            System.out.println(i);
            PlayerAccount account = new PlayerAccount();

            account.setPassword("c4f4652fb4cfcc7756e3b2b97019955a");
            account.setCreationTime(0);
            account.setLastUpdate(0);
            account.setStatus(status);
            playerAccountDAO.save(account);
        }

        return "hello";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setUsername("test");
        adminAccount.setPassword("test123");
        adminAccountDAO.update(adminAccount);
        return "hello";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setUsername("test");
        adminAccountDAO.remove(adminAccount);
        return "hello";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find() {
        ModelAndView model = new ModelAndView("hello");
        model.addObject("message", "Hello world!");
        AdminAccount adminAccount = adminAccountDAO.getByUsername("test");
        model.addObject("username", adminAccount.getUsername());
        return model;
    }

    @RequestMapping(value = "/setCache", method = RequestMethod.GET)
    public ModelAndView setCache() {
        ModelAndView model = new ModelAndView("hello");
        model.addObject("message", "Hello world!");
        memcachedClient.set("testcache", "test cache", 1800);
        return model;
    }

    @RequestMapping(value = "/getCache", method = RequestMethod.GET)
    public ModelAndView getCache() {
        ModelAndView model = new ModelAndView("hello");
        model.addObject("message", "Hello world!");
        System.out.println(memcachedClient.get("testcache"));
        return model;
    }


    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public ModelAndView sendMail() {
        ModelAndView model = new ModelAndView("hello");
        mailService.sendMail("manlmse61239@fpt.edu.vn", "admin-activate.vm", "Reset Password", "manlm", "new password");
        return model;
    }
}