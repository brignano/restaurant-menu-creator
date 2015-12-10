/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author abrignano
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(DBController.class);

    @Autowired
    private DirectoryService directoryService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerpage(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        return "register";
    }

    @RequestMapping(value = "/menucreation", method = RequestMethod.GET)
    public String menuCreate(@Validated UserClass passedUser, Model model) {
        logger.info("Redirecting to menuCreation");
        model.addAttribute("user", passedUser);
        return "menucreation";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@Validated UserClass passedUser, Model model) {
        logger.info("Login called- GET");
        if (passedUser != null) {
            logger.info("UserClass: " + passedUser);
        }

        if (getDirectoryService().verifyLogin(passedUser) != null) {
            model.addAttribute("user", passedUser);
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
