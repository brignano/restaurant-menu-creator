package controllers;

import java.util.ArrayList;
import java.util.List;

public class Submenu {

    private String subMenuTitle;
    private List<MenuItem> menuItems;

    public Submenu() {
        subMenuTitle = "";
        menuItems = new ArrayList();
    }

    public Submenu(String subMenuTitle) {
        this.subMenuTitle = subMenuTitle;
        menuItems = new ArrayList();
    }

    public String getSubMenuTitle() {
        return subMenuTitle;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public void setSubMenuTitle(String subMenuTitle) {
        this.subMenuTitle = subMenuTitle;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public String buildString() {
        StringBuilder elementString = new StringBuilder(45);
        elementString.append("*\"");
        elementString.append(this.subMenuTitle);
        elementString.append("\" ");

        for (MenuItem item : menuItems) {
            elementString.append(item.buildString());
            elementString.append(' ');
        }

        return elementString.toString();
    }

}
