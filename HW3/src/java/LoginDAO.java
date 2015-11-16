
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author abrignano
 */
public class LoginDAO {
    
    public static boolean validate(String user, String password){
        Connection connection = null;
        PreparedStatement ps = null;
        
        try {
            connection = DataConnect.getConnection();
            ps = connection.prepareStatement("select username, password from Users where username = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                // result found, means valid inputs
                return true;
            }
        } catch (SQLException e){
            System.out.println("Login error -->" + e.getMessage());
            return false;
        } finally {
            DataConnect.close(connection);
        }
        return false;
    }
    
}
