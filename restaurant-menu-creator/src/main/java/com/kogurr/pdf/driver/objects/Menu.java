package com.kogurr.pdf.driver.objects;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
@Entity
@Table(name = "MENUS")
public class Menu {

    @Id
    @GeneratedValue
    @ManyToOne(cascade=CascadeType.ALL)
    @Column(name = "ID")
    private long id;
    
    /**
     * we need something like this on the user entity bean:
     * @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
     * @JoinColumn(name="USER_ID")
     * private List menus;
     */
    
    @Column(name = "title")
    private String menuTitle;
    
    @Column(name = "logo")
    private String logoPath;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="MENU_ID")
    private List<Submenu> submenus;



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
    
    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
    
    public List<Submenu> getSubmenus() {
        return submenus;
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
    
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
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
