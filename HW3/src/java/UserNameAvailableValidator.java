
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

@FacesValidator("userNameAvailableValidator")
public class UserNameAvailableValidator implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String userName = (String) value;
        Connection connection = null;
        PreparedStatement ps = null;
        
        try{
            connection = DataConnect.getConnection();
            ps = connection.prepareStatement("SELECT username FROM Users where username=?");
            ps.setString(1, userName);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, " Error: Username " + userName + " already exists.", null));
//            else
//                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_INFO, " Username " + userName + " is available.", null));
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DataConnect.close(connection);
        }
    }

}
