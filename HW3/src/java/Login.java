import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author abrignano
 */

@ManagedBean
@SessionScoped
public class Login implements Serializable {
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
        boolean valid = LoginDAO.validate(user, pwd);
        if(valid){
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
            return "admin";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Incorrect Username and Passowrd",
                "Please enter correct username and Password"));
            return "login";
        }
    }
        
        public String logout(){
            HttpSession session = SessionBean.getSession();
            session.invalidate();
            return "login";
        }
    }
