
import database.UsersJpaController;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;


/**
 *
 * @author abrignano
 */

@FacesValidator("userNameAvailableValidator")
public class UserNameAvailableValidator implements Validator {
    
    @PersistenceUnit(unitName = "HW3PU")
    EntityManagerFactory emf;
    @Resource
    UserTransaction utx;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String userName = (String) value;
        UsersJpaController ujc = new UsersJpaController(utx, emf);
        
        if(ujc.checkAvailability(userName)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    " Error: Username " + userName + " already exists.", null));
        }
    }

}
