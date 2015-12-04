package controllers;

public class MenuItem {

    private String name;
    private String description;
    private String price;

//    public MenuItem() {
//        name        = "";
//        description = "";
//        price       = "";
//    }

    public MenuItem(String name) {
        this.name   = name;
        description = "";
        price       = "";
    }

    public MenuItem(String name, String description) {
        this.name        = name;
        this.description = description;
        price            = "";
    }

    public MenuItem(String name, String description, String price) {
        this.name        = name;
        this.description = description;
        this.price       = price;
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
