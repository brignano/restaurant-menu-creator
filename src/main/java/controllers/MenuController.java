//Author: John Madsen
package controllers;

import com.kogurr.pdf.driver.objects.MenuItem;
import com.kogurr.pdf.driver.objects.Menu;
import com.kogurr.pdf.driver.objects.Submenu;
import com.kogurr.pdf.driver.ScriptProcessDriver;

import static java.lang.Integer.parseInt;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

    @Autowired
    private DirectoryService directoryService;

    /**
     *
     * @param allRequestParams Request parameters that are generated from
     * dynamic input elements on MenuCreation.jsp
     * @param restaurantInfo Request
     * @return returns model and view with model as current Model and
     * "showMessage" as the current view(can be changed below in order to route
     * somewhere else).
     */
    @RequestMapping(value = "/menusave", method = RequestMethod.POST)
    public ModelAndView bindMenu(@RequestParam MultiValueMap<String, String> allRequestParams,
            @ModelAttribute("RestaurantInfo") RestaurantInfo restaurantInfo) {
        Menu menu = new Menu(); //complete menu object
        List<Submenu> submenus = new ArrayList<Submenu>(); //sub menu to initialize then add to menu object.
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        List<String> name = new ArrayList(); //for holding list of item names from each sub menu
        List<String> price = new ArrayList(); //for holding list of item prices from each sub menu
        List<String> description = new ArrayList(); //for holding list of item descriptions from each sub menu

        Submenu subMenu;
        MenuItem menuItem;
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
                menuItem = new MenuItem(name.get(j), description.get(j), price.get(j));
                menuItem.setSubmenu(subMenu);
                menuItems.add(menuItem);
            }
            subMenu.setMenuItems(menuItems);
            submenus.add(subMenu);
            subMenu.setMenu(menu);
        }
        menu.setSubmenus(submenus);

        getDirectoryService().addMenu(menu);
//
//        for (Submenu s : submenus) {
//            getDirectoryService().addSubmenu(s);
//            for (MenuItem m : s.getMenuItems()) {
//                getDirectoryService().addMenuItem(m);
//            }
//        }

        System.out.println("");

        ModelAndView mav = new ModelAndView("showMessage");
        mav.addObject(menu);
//        mav.addObject(restaurantInfo);
//        getDirectoryService().addMenu(menu);

        String redirectPath = "redirect:";
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            sha.update(menu.buildString().getBytes());
            byte byteData[] = sha.digest();

            StringBuilder uniqueId = new StringBuilder();
            for (int z = 0; z < byteData.length; z++) {
                uniqueId.append(Integer.toString((byteData[z] & 0xff) + 0x100, 16).substring(1));
            }
            String truncUniqueId = uniqueId.toString();
            String test = truncUniqueId.substring(0, Math.min(15, truncUniqueId.length())) + ".pdf";

            redirectPath += "resources/pdf/" + ScriptProcessDriver.INSTANCE.makeMenu("template-1", test, menu);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        mav.addObject(redirectPath);

        return mav;
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

    //currently broken get requestmethod controller. not needed but leaving in for now in case we change how this works.
    //    @RequestMapping(value = "/menuCreation.jsp", method = RequestMethod.GET)
//    public String MenuCreation(Model model) {
//        model.addAttribute("restaurantInfo", new RestaurantInfo());
//        return "MenuCreation";
//    }
}
