import database.Users;
import database.UsersJpaController;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author abrignano
 */

@ManagedBean
@SessionScoped
public class Login implements Serializable {
    
    @PersistenceUnit(unitName = "HW3PU")
    EntityManagerFactory emf;
    @Resource
    UserTransaction utx;
    
    private static final long serialVersionUID = 1094801825228386363L;
    private String pwd, msg, user;
    
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
    
    public String validateUsernamePassword(){
        UsersJpaController ujc = new UsersJpaController(utx, emf);
        Users userObject = ujc.verifyUsers(user, pwd);
         
        if(userObject != null){
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
            return "admin";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Incorrect Username and Password",
                "Please enter correct Username and Password"));
            return "login";
        }
    }
    
    public String logout(){
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }
}
