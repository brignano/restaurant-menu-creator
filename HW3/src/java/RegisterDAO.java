
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author abrignano
 */
public class RegisterDAO {
    
    public static boolean register(String user, String password){
        Connection connection = null;
        PreparedStatement ps = null;
        
        try {
            connection = DataConnect.getConnection();
            ps = connection.prepareStatement("insert into Users (username,password) values (?,?)");
            ps.setString(1, user);
            ps.setString(2, password);
            int recordsAffected = ps.executeUpdate();
            if(recordsAffected == 0)
                // indicates an error in the database insert
                return false;
            else
                // insert completed, means user is now in database
                return true;
            
        } catch (SQLException e){
            System.out.println("Registration error -->" + e.getMessage());
            return false;
        } finally {
            DataConnect.close(connection);
        }
    }
    
}
