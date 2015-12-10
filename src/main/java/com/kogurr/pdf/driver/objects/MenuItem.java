package com.kogurr.pdf.driver.objects;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "MENUITEM")
public class MenuItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "submenuId")
    private Submenu submenu;

    public MenuItem() {
    }

    public MenuItem(String name, String description) {
        this.name = name;
        this.description = description;
        price = "";
    }

    public MenuItem(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Submenu getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Submenu submenu) {
        this.submenu = submenu;
    }
    
    

    public String buildString() {
        String itemString = "^\"" + this.name + "\"";

        if (!this.description.isEmpty()) {
            itemString += " !\"" + this.description + "\"";
        }

        if (!this.price.isEmpty()) {
            itemString += " $\"" + this.price + "\"";
        }

        return itemString;
    }

}
