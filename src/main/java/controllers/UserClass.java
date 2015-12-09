/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Paolo
 */
@Entity
public class UserClass extends AbstractPersistable<Long>{
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    
    public UserClass(){
        
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setUserName(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}