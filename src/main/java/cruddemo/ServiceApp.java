package cruddemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author johans caicedo
 */
public class ServiceApp {

    private static final String SELECT_USER_BY_ID = "select id,nombre,pwd,email from login where id =?";
    private static final String SELECT_USER_ = "select * from login where nombre=? AND pwd=?";

    private String jdbcURL = "jdbc:mysql://localhost:3306/logindemo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    /***
     * 
     * 
     * @param connection
     * @return boolean
     ***/
    protected boolean closeConnection(Connection connection) {

        try {
            connection.close();
        } catch (SQLException sQLException) {

            return false;
        }

        return true;
    }

    /***
     * 
     * @return Connection
     **/
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

    /***
     * 
     * 
     * @param database
     * @param username
     * @param password
     * @return 
     **/
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



}
