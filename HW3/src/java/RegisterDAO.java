
import database.Users;
import database.UsersJpaController;
import database.exceptions.PreexistingEntityException;
import database.exceptions.RollbackFailureException;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;


/**
 *
 * @author abrignano
 */
public class RegisterDAO {
    
    @PersistenceUnit(unitName = "HW3PU")
    EntityManagerFactory emf;
    @Resource
    UserTransaction utx;
    
    private String shortError = "none";
    private String longError = "none";
    
    public boolean register(String user, String password) {
        Users registerUser = new Users(); 
        registerUser.setUsername(user);
        registerUser.setPassword(password);
        
        UsersJpaController ujc = new UsersJpaController(utx, emf);
        try {
            ujc.create(registerUser);
        }
        catch (RollbackFailureException roll) {
            shortError = "Rollback error";
            longError = roll.getMessage();
            return false;
        }
        catch (PreexistingEntityException pre) {
            shortError = "Username already in use";
            longError = "Sorry, that username is already in use. Please try another.";
            return false;
        }
        catch (Exception ex) {
            shortError = "Unknown exception";
            longError = ex.getMessage();
            return false;
        }
        
        return true;
    }
    
    public String getShortError() {
        return shortError;
    }
    
    public String getLongError() {
        return longError;
    }
    
}
