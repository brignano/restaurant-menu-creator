package controllers;

import java.io.Serializable;

public class RestaurantInfo implements Serializable{

    private String restName, restPhone, restStreet, restCity, restState, restZip, logoPath, sections;

    public void setSections(String sections) {
        this.sections = sections;
    }

    public String getSections() {
        return sections;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public void setRestPhone(String restPhone) {
        this.restPhone = restPhone;
    }

    public void setRestStreet(String restStreet) {
        this.restStreet = restStreet;
    }

    public void setRestCity(String restCity) {
        this.restCity = restCity;
    }

    public void setRestState(String restState) {
        this.restState = restState;
    }

    public void setRestZip(String restZip) {
        this.restZip = restZip;
    }
    
    public String getLogoPath() {
        return logoPath;
    }

    public String getRestName() {
        return restName;
    }

    public String getRestPhone() {
        return restPhone;
    }

    public String getRestStreet() {
        return restStreet;
    }

    public String getRestCity() {
        return restCity;
    }

    public String getRestState() {
        return restState;
    }

    public String getRestZip() {
        return restZip;
    }
}
