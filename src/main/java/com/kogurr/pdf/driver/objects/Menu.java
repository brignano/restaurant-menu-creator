package com.kogurr.pdf.driver.objects;

import controllers.UserClass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD:restaurant-menu-creator/src/main/java/com/kogurr/pdf/driver/objects/Menu.java
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
=======
import javax.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "MENU")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
>>>>>>> insert-ajax:src/main/java/com/kogurr/pdf/driver/objects/Menu.java

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
<<<<<<< HEAD:restaurant-menu-creator/src/main/java/com/kogurr/pdf/driver/objects/Menu.java
    
    @Column(name = "logo")
    private String logoPath;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="MENU_ID")
=======

    private String logoPath;
    
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = false, mappedBy = "menu", cascade = CascadeType.ALL)
>>>>>>> insert-ajax:src/main/java/com/kogurr/pdf/driver/objects/Menu.java
    private List<Submenu> submenus;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private UserClass userClass;

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
    

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public UserClass getUserClass() {
        return userClass;
    }

    public void setUserClass(UserClass userClass) {
        this.userClass = userClass;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean equals(Menu menu) {
        return (this.id == menu.id);
    }
}
