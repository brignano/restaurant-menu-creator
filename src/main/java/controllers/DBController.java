/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Paolo & Anthony Brignano
 */
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DBController {

    private static final Logger logger = LoggerFactory.getLogger(DBController.class);

    @Autowired
    private DirectoryService directoryService;

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

    @RequestMapping(value = "/checkusername", method = RequestMethod.GET)
    public @ResponseBody
    String processAJAXRequest(@RequestParam("username") String username) {
        String response = "";
        if(directoryService.availableUsername(username)) {
            return "Available";
        }
        else
            return "Not Available";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Validated UserClass passedUser, HttpServletRequest request, Model model) {
        logger.info("Login called- POST");
        if (passedUser != null) {
            logger.info("UserClass: " + passedUser);
        }

        UserClass existingUser = directoryService.verifyLogin(passedUser);

        if (existingUser != null) {
//            model.addAttribute("user", passedUser);
            request.getSession().setAttribute("user", passedUser);
            return "home";
        } else {
            model.addAttribute("error", "Login unsuccessful");
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
