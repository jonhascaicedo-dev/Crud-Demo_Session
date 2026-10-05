package cruddemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class ServiceApp {

    private static final String SELECT_USER_BY_ID = "select id,nombre,pwd,email from login where id =?";
    private static final String SELECT_USER_ = "select * from login where nombre=? AND pwd=?";
    //"Select * from users WHERE username = ? AND password = ?"
    /*
        INSERT INTO `login` (`id`, `nombre`, `pwd`, `email`) VALUES (NULL, 'admintest', 'root1', 'adminemail@')
     */
    //private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcURL = "jdbc:mysql://localhost:3306/logindemo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    protected boolean closeConnection(Connection connection) {

        try {
            connection.close();
        } catch (SQLException sQLException) {

            return false;
        }

        return true;
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    protected Connection customConnection(String database, String username, String password) {
        
        Connection connection = null;
        try {
            
            jdbcURL = "jdbc:mysql://localhost:3306/"+database+"?useSSL=false";
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public ServiceApp() {

    }

}
