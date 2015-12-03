package com.kogurr.pdf.driver;

import com.kogurr.pdf.driver.objects.Menu;
import com.kogurr.pdf.driver.objects.MenuItem;
import com.kogurr.pdf.driver.objects.Submenu;

import java.util.ArrayList;
import java.util.List;

public class MockMenuTester {

    public static void main(String[] args) {
        Menu sandwichMenu = new Menu();
        sandwichMenu.setMenuTitle("Nathan's Sandwiches");

        // Creating submenu using object methods
        Submenu lunchMenu = new Submenu();
        lunchMenu.setSubMenuTitle("Lunch");

        // Building submenu using object methods
        MenuItem salamiMelt = new MenuItem();
        salamiMelt.setName("Grilled Salami Melt");
        salamiMelt.setDescription("It's filled with grilled meat and cheese. What more could you want?");
        lunchMenu.addMenuItem(salamiMelt);

        // Building submenu using the MenuItem constructor
        lunchMenu.addMenuItem(new MenuItem(
                "Bacon Lettuce & Tomato Wrap",
                "A classic BLT."
        ));
        lunchMenu.addMenuItem(new MenuItem(
                "Fishy Sandwich",
                "A tartar sauce covered slab of Scrod sitting atop a fresh piece of toast."
        ));

        // Building submenu using a plain List
        List<MenuItem> dinnerItems = new ArrayList<>(5);
        dinnerItems.add(new MenuItem(
                "Midnight Meat Train",
                "Our biggest sandwich, stacked high with roast beef, turkey, chicken, and pigeon."

        ));
        dinnerItems.add(new MenuItem(
                "Grilled Cheese",
                "Using blue cheese of course."
        ));
        dinnerItems.add(new MenuItem(
                "Pretzel Roll",
                "Fine pretzel bread sandwich, your choice of toppings."
        ));

        // Creating submenu using constructor
        Submenu dinnerMenu = new Submenu("Dinner");
        dinnerMenu.setMenuItems(dinnerItems);

        Submenu drinksMenu = new Submenu("Drinks");
        drinksMenu.addMenuItem(new MenuItem("Coke"));
        drinksMenu.addMenuItem(new MenuItem("Sprite"));
        drinksMenu.addMenuItem(new MenuItem("Water"));
        drinksMenu.addMenuItem(new MenuItem("Coffee"));
        drinksMenu.addMenuItem(new MenuItem("Orange Juice"));

        // Adding the submenus to the menu object
        sandwichMenu.addSubmenu(lunchMenu);
        sandwichMenu.addSubmenu(dinnerMenu);
        sandwichMenu.addSubmenu(drinksMenu);

        // Calling the class to run the script and create the pdf file
        ScriptProcessDriver.INSTANCE.makeMenu("template-1", "sandwich shop.pdf", sandwichMenu);
    }

}
