package controllers;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "RESTAURANTINFO")
public class RestaurantInfo extends AbstractPersistable<Long> {
    
    @Id
    private Long id;

    private String restName;
    private String restPhone;
    private String restStreet;
    private String restCity;
    private String restState;
    private String restZip;
    private String logoPath;
    private String sections;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
