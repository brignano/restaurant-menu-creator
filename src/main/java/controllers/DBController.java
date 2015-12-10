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
import com.kogurr.pdf.driver.objects.Menu;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

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
        if (directoryService.availableUsername(username)) {
            return "Available";
        } else {
            return "Not Available";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Validated UserClass passedUser, HttpServletRequest request, Model model) {
        logger.info("Login called- POST");
        if (passedUser != null) {
            logger.info("UserClass: " + passedUser);
        }

        UserClass existingUser = directoryService.verifyLogin(passedUser);

        if (existingUser != null) {
            List<Menu> menus = getDirectoryService().getMenus(existingUser);
            request.getSession().setAttribute("user", existingUser);

            if (menus != null) {
                model.addAttribute(menus);
                return "home";
            } else {
                return "menucreation";
            }

        } else {
            model.addAttribute("error", "Login unsuccessful");
            return "login";
        }
    }

    @RequestMapping(value = "/getmenus", method = RequestMethod.GET)
    public ModelAndView getMenus(HttpServletRequest request, Model model) {
        return new ModelAndView("menuCreation");
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

    private String ModelAndView(String redirectmyURL) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
