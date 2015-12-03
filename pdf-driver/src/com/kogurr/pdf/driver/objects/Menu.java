package com.kogurr.pdf.driver.objects;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String menuTitle;
    private String logoPath;

    private List<Submenu> submenus;

    public Menu() {
        menuTitle = "";
        logoPath  = "";
        submenus  = new ArrayList<>(3);
    }

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
        logoPath       = "";
        submenus       = new ArrayList<>(3);
    }

    public Menu(String menuTitle, String logoPath) {
        this.menuTitle = menuTitle;
        this.logoPath  = logoPath;
        submenus       = new ArrayList<>(3);
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void addSubmenu(Submenu submenu) {
        submenus.add(submenu);
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public void setSubmenus(List<Submenu> submenus) {
        this.submenus = submenus;
    }

    public String buildString() {
        StringBuilder menuString = new StringBuilder(300);
        menuString.append("--menu_title ");
        menuString.append("\"");
        menuString.append(getMenuTitle());
        menuString.append("\" ");

        if (!logoPath.isEmpty()) {
            menuString.append("--image ");
            menuString.append("\"");
            menuString.append(logoPath);
            menuString.append("\" ");
        }

        menuString.append("--content ");

        for (Submenu submenu : submenus) {
            menuString.append(submenu.buildString());
        }

        return menuString.toString();
    }
}
