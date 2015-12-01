//Author: John Madsen
package controllers;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("MenuCreation.html")
public class MenuController {

    /**
     * how returning ArrayList is structured:
     * ArrayList.get(0) = subArrayList of restaurant info.
     * subArrayList.get(0) = String[size 3] of restaurant name,  phone,  street.
     * subArrayList.get(1) = String[size 3] of city, state, zip.
     * 
     * ArrayList.get(1) = subArrayList of first menu section.
     * subArrayList.get(0) = String[size 3] of Menu section description, null, null.
     * subArrayList.get(1) = string[size 3] of first menu Item name, price, description.
     * subArrayList.get(2..*) = string[size 3] of next menu Item name, price, description.
     * 
     * ArrayList.get(2) = subArrayList of second menu section.
     * Structure of second menu same as first for subArrayList.

     * @param request
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ArrayList<ArrayList<String[]>> getAttributes(HttpServletRequest request) {
        ArrayList<ArrayList<String[]>> info = new ArrayList(); //complete menu data
        ArrayList<String[]> section = new ArrayList(); //section of menu, first section is restaurand info, the rest are different menu sections.
        String[] subSection = new String[3]; //subsection for holding menu items, [0] = name of item, [1] = price, [2] = description.
        String[] name = new String[50]; //for holding list of item names from each section
        String[] price = new String[50]; //for holding list of item prices from each section
        String[] description = new String[50]; //for holding list of item descriptions from each section

        /**
         * first section holds restaurant information.
         * 
         */
        subSection[0] = request.getParameter("restName");
        subSection[1] = request.getParameter("restPhone");
        subSection[2] = request.getParameter("restStreet");
        section.add(subSection);
        subSection[0] = request.getParameter("restCity");
        subSection[1] = request.getParameter("restState");
        subSection[2] = request.getParameter("restZip");
        section.add(subSection);
        info.add(section); //first section contains restaurant info in the first and second arrays.

        int menuSections = parseInt(request.getParameter("hiddenId")); //number of menu sections
        for (int i = 1; i < menuSections; i++) { //cycles through menu sections
            subSection[0] = request.getParameter("section" + i); //gets menu section description/title
            section.add(subSection); //adds description/title to the menu section arraylist
            name = request.getParameterValues("name" + i);
            price = request.getParameterValues("price" + i);
            description = request.getParameterValues("description" + i);

            for (int j = 0; j < name.length; j++) {
                subSection[0] = name[j];
                subSection[1] = price[j];
                subSection[2] = description[j];
                section.add(subSection);
            }
            info.add(section);
        }
        return info;
    }

}
