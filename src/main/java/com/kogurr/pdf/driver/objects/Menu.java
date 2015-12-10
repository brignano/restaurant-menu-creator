package com.kogurr.pdf.driver.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "MENU")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String menuTitle;

    @Column
    private String logoPath;

    @OneToMany(mappedBy = "menu", orphanRemoval=true, cascade=CascadeType.ALL)
    private List<Submenu> submenus;
    


    /**
     * we need something like this on the user entity bean:
     *
     * @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
     * @JoinColumn(name="USER_ID") private List menus;
     *
     */
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
    public boolean equals(Menu menu){
        return (this.id == menu.id);
    }
}
