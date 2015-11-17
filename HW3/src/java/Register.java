
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author abrignano
 */

@ManagedBean
@SessionScoped
public class Register implements Serializable {
    private String pwd, msg, user;
    private String c_pwd;
    
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
    
    public void setConfirmPwd(String c_pwd){
        this.c_pwd = c_pwd;
    }
    
    public String registerUser() throws SQLException{
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
                "Incorrect Username or Password",
                "Please enter correct Username and Password"));
            return "register";
        }
    }
    
}
