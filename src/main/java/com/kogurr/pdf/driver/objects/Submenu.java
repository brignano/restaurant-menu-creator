package com.kogurr.pdf.driver.objects;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "SUBMENU")
public class Submenu implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;
    
    @Column(name = "TITLE")
    private String subMenuTitle;
    
    @OneToMany(mappedBy = "submenu", orphanRemoval=true, cascade=CascadeType.ALL)
    private List<MenuItem> menuItems;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "menuId")
    private Menu menu;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
