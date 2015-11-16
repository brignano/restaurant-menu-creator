
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abrignano
 */
public class Register implements Serializable {
    private String user, pwd, c_pwd, msg;
    
    public String getPwd(){
        return pwd;
    }
    
    public void setPwd(String pwd){
        this.pwd = pwd;
    }
    
    public String getMsg(){
        return msg;
    }
    
    public void setMsg(String msg){
        this.msg = msg;
    }
    
    public String getUser(){
        return user;
    }
    
    public void setUser(String user){
        this.user = user;
    }
    
    public String getConfirmPwd(){
        return c_pwd;
    }
    
    public void setConfirmPwd(String user){
        this.c_pwd = c_pwd;
    }
    
    public String registerUser(){
        // try to add user to database using RegisterDAO.register() method
        boolean register = RegisterDAO.register(user, pwd);
        if(register){
            // if registration succeeded, return admin page for said new user
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Incorrect Username and Password",
                "Please enter correct Usernamd and Password"));
            return "login";
        }
    }
    
}
