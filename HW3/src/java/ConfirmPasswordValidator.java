
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author abrignano
 */

@FacesValidator("confirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;
        String confirm = (String) component.getAttributes().get("confirm");

        if (password == null || confirm == null) {
            return; // Just ignore and let required="true" do its job.
        }

        if (!password.equals(confirm)) 
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, " Error: Passwords do not match.", null));
//        else
//            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_INFO, " Passwords match.", null));
    }

}

