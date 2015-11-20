
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author abrignano
 */
public class DataConnect {
    
    public static Connection getConnection(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            Connection con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/HW3DB", "APP", "pass");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error --> "
                    + ex.getMessage());
            return null;
        }
    }
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
