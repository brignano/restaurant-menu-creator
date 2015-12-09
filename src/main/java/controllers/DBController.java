/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Paolo
 */
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DBController {

    private static final Logger logger = LoggerFactory.getLogger(DBController.class);

    @Autowired
    private DirectoryService directoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerpage(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        return "register";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String adduser(Locale locale, Model model) {
        logger.info("Registration called");
        return "adduser";
    }

    @RequestMapping(value = "/usersaved", method = RequestMethod.POST)
    public String register(@Validated UserClass passedUser, Model model) {
        logger.info("Registration called");
        if (passedUser != null) {
            logger.info("UserClass: " + passedUser);
        }
        getDirectoryService().saveUser(passedUser);
        model.addAttribute("user", passedUser);
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Validated UserClass passedUser, Model model) {
        logger.info("Login called");
        if (passedUser != null) {
            logger.info("UserClass: " + passedUser);
        }
        model.addAttribute("user", passedUser);
        if (getDirectoryService().login(passedUser) == true) {
            return "home";
        } else {
            return "login";
        }
    }

    /**
     * @return the directoryService
     */
    public DirectoryService getDirectoryService() {
        return directoryService;
    }

    /**
     * @param directoryService the directoryService to set
     */
    public void setDirectoryService(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }
}
