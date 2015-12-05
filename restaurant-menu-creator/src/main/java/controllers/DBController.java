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
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DBController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String redirectRequestToRegistrationPage(@RequestParam Map<String,String> allRequestParams, Model model) {
        model.addAttribute("user", allRequestParams.get("username"));
        String username = allRequestParams.get("username");
        String password = allRequestParams.get("password");
        
        
        return "home";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showLoginPage(@RequestParam Map<String,String> allRequestParams, Model model) {
        model.addAttribute("user", allRequestParams.get("username"));
        String username = allRequestParams.get("username");
        String password = allRequestParams.get("password");
        String sqlStatement = "SELECT password FROM USERS WHERE username = '" + username + "'";
        SqlCommand LoginCmd = new SqlCommand();
        // if username & pwd correct return "home"
        // if (username != NULL && password != NULL)
            return "home";
        // else return "login"
        // return "login";
    }
}
