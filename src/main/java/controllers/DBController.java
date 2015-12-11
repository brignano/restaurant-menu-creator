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
        if(directoryService.verifyLogin(passedUser)==null){
        directoryService.saveUser(passedUser);
        model.addAttribute("user", passedUser);
        return "login";
        }
        else {
            model.addAttribute("error","Please choose a different username.");
            return "register";
        }
    }

    @RequestMapping(value = "/checkusername", method = RequestMethod.GET)
    public @ResponseBody
    String processAJAXRequest(@RequestParam("username") String passedUsername, Model model) {
        String result = "";
        if(!directoryService.availableUsername(passedUsername)) {
            model.addAttribute("passedUsername",passedUsername);
            result = "Username already exists ";
        }
           return result; 
        }
   
    @RequestMapping("/register")
  public ModelAndView AjaxView() {
    return new ModelAndView("register", "message", "AJAX test");
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
                model.addAttribute("menus", menus);
                model.addAttribute("menu", new Menu());
                return "home";
            } else {
                model.addAttribute("nomenu", "You have no saved menus!");

                return "menucreation";
            }

        } else {
            model.addAttribute("error", "Login unsuccessful");
            return "login";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(HttpServletRequest request, Model model) {

        UserClass passedUser = (UserClass) request.getSession().getAttribute("user");

        if (passedUser != null) {
            logger.info("UserClass: " + passedUser);
        }

        UserClass existingUser = directoryService.verifyLogin(passedUser);

        if (existingUser != null) {
            List<Menu> menus = getDirectoryService().getMenus(existingUser);
            request.getSession().setAttribute("user", existingUser);

            if (menus != null) {
                model.addAttribute("menus", menus);
                model.addAttribute("menu", new Menu());
                return "home";
            } else {
                model.addAttribute("nomenu", "<h3>No Menus Detected On Your Account.</h3>\n" +
"                    <h4>You Were Forwarded To The Menu Creation Page.</h4>\n" +
"                    <h4>Please Create A Menu!</h4>");

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
