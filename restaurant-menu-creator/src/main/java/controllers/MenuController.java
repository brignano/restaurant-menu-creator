//Author: John Madsen
package controllers;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

    /**
     *
     * @param allRequestParams Request parameters that are generated from dynamic input elements on MenuCreation.jsp
     * @param restaurantInfo Request 
     * @return returns model and view with model as current Model and "showMessage" as the current view(can be changed below in order to route somewhere else).
     */
    @RequestMapping(value = "/menusave", method = RequestMethod.POST)
    public ModelAndView bindMenu(@RequestParam MultiValueMap<String, String> allRequestParams,
           @ModelAttribute("RestaurantInfo") RestaurantInfo restaurantInfo) {
        
        ModelAndView model = new ModelAndView("showMessage"); //change "showMessage" to a different page or request mapping object to route differently.
        
        model.addObject("headerMessage", "Page Header");   //example header 
        model.addObject("restaurantInfo",restaurantInfo);//only needed for showmessage.jsp page and not for where cj needs this controller to.

        Menu menu = new Menu(); //complete menu object
        Submenu subMenu = new Submenu(); //sub menu to initialize then add to menu object.
        List<String> name = new ArrayList(); //for holding list of item names from each sub menu
        List<String> price = new ArrayList(); //for holding list of item prices from each sub menu
        List<String> description = new ArrayList(); //for holding list of item descriptions from each sub menu

        //add restaurant name and logo path to menu from model attribute RestaurantInfo from MenuCreation.jsp
        menu.setMenuTitle(restaurantInfo.getRestName());
        menu.setLogoPath(restaurantInfo.getLogoPath());

        int menuSections = parseInt(restaurantInfo.getSections()); //number of menu sections
        //Builds menu from request parameters.
        for (int i = 1; i <= menuSections; i++) {//cycles through menu sections
            System.out.println("section" + i);
            subMenu = new Submenu();
            subMenu.setSubMenuTitle(allRequestParams.getFirst("section" + i)); //gets menu section description/title
            name = allRequestParams.get("name" + i); 
            price = allRequestParams.get("price" + i);
            description = allRequestParams.get("description" + i);

            for (int j = 0; j < name.size(); j++) {
                subMenu.addMenuItem(new MenuItem(name.get(j), description.get(j), price.get(j)));
            }
            menu.addSubmenu(subMenu);
        }
        model.addObject("menu", menu); //adds menu object to the model in order to be accessed on the next page
        return model;
    }
    //currently broken get requestmethod controller. not needed but leaving in for now in case we change how this works.
    //    @RequestMapping(value = "/menuCreation.jsp", method = RequestMethod.GET)
//    public String MenuCreation(Model model) {
//        model.addAttribute("restaurantInfo", new RestaurantInfo());
//        return "MenuCreation";
//    }
}

 
