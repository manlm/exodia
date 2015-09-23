package com.exodia.bom.controller;

import com.exodia.bom.config.Properties;
import com.exodia.bom.service.MailService;
import com.exodia.common.constant.Constant;
import com.exodia.common.util.MemcachedClient;
import com.exodia.database.dao.AdminAccountDAO;
import com.exodia.database.entity.AdminAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    private static final Logger LOG = Logger.getLogger(IndexController.class);

    @Autowired
    private AdminAccountDAO adminAccountDAO;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private Properties properties;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(name = "loggedUsername", required = false) String loggedUsername,
                              @RequestParam(name = "error", required = false) String error) {
        LOG.info("[login] Start");
        ModelAndView model = new ModelAndView("login");

        if (error != null) {
            model.addObject("error", "error");
        }
        if (loggedUsername != null) {
            model.addObject("loggedUsername", loggedUsername);
        }

        LOG.info("[login] End");
        return model;
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public ModelAndView showForgorPassword() {
        LOG.info("[showForgorPassword] Start");
        ModelAndView model = new ModelAndView("forgotPassword");
        LOG.info("[showForgorPassword] End");
        return model;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (String.valueOf(user.getAuthorities().iterator().next()).equals(String.valueOf(Constant.ADMIN_ROLE.ACCOUNT_MANAGER))) {
            return "redirect:viewAdminAccount";
        }
        return "redirect:login";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView model = new ModelAndView("hello");
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("message", "Hello world!");

        for (int i = 101; i <= 200; i++) {
            System.out.println(i);
            AdminAccount adminAccount = new AdminAccount();
            adminAccount.setUsername("test" + i);
            adminAccount.setPassword("c4f4652fb4cfcc7756e3b2b97019955a");
            adminAccount.setRole(1);
            adminAccount.setCreationTime(0);
            adminAccount.setLastUpdate(0);
            adminAccount.setStatus(1);
            adminAccountDAO.save(adminAccount);
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