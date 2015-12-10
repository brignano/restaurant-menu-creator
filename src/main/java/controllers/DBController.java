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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@Validated UserClass passedUser, Model model){
        logger.info("Login called- GET");
        if(passedUser != null){
            logger.info("UserClass: " + passedUser);
        }

        UserClass existingUser=directoryService.verifyLogin(passedUser);
        
        if(existingUser != null){
            model.addAttribute("user",passedUser);
            return "home";
        }
        
        if(getDirectoryService().verifyLogin(passedUser) != null){
            model.addAttribute("user",passedUser);
            return "home";
        }
        else{
            return "login";
        }
    }
    
@RequestMapping(value = "/checkusername", method = RequestMethod.GET)
public @ResponseBody String processAJAXRequest(@RequestParam("username") String username){
		String response = "";
                
		// Process the request
		// Prepare the response string
		return response;
	}
    
//    @RequestMapping(value="/availability", method=RequestMethod.GET)
//public @ResponseBody AvailabilityStatus getAvailability(@RequestParam String username) {
//    for (UserClass user : users.values()) {
//        if (user.getUsername().equals(username)) {
//            return AvailabilityStatus.notAvailable(username);
//        }
//    }
//    return AvailabilityStatus.available();
//}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Validated UserClass passedUser, Model model){
        logger.info("Login called- POST");
        if(passedUser != null){
            logger.info("UserClass: " + passedUser);
        }

        UserClass existingUser=directoryService.verifyLogin(passedUser);
        
        if(existingUser != null){
            model.addAttribute("user",passedUser);
            return "home";
        }
        else{
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
