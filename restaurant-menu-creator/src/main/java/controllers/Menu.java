package controllers;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String menuTitle;
    private String logoPath;

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    private List<Submenu> submenus;

    public List<Submenu> getSubmenus() {
        return submenus;
    }

    public Menu() {
        menuTitle = "";
        logoPath = "";
        submenus = new ArrayList();
    }

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
        logoPath = "";
        submenus = new ArrayList();
    }

    public Menu(String menuTitle, String logoPath) {
        this.menuTitle = menuTitle;
        this.logoPath = logoPath;
        submenus = new ArrayList();
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
