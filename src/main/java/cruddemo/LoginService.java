package cruddemo;

import entity.User;
import form.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author johans caicedo
 */
public class LoginService {

    private static final String SELECT_USER_
            = "select * from login where nombre=? AND pwd=?";

    private String jdbcURL = "jdbc:mysql://localhost:3306/logindemo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";
    private static Logger logger = null;

    /**
     * *
     *
     *
     * @return Connection
     */
    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.
                    getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {

            logger
                    = Logger.getLogger(Login.class.getName());
        }
        return connection;
    }

    /**
     *
     * @param strn1 usuario
     * @param strn2 contraseña
     * @return User *
     */
    public User login(String strn1, String strn2) {

        User user = new User();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection(); // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement
                = connection.prepareStatement(SELECT_USER_);) {

            preparedStatement.setString(1, strn1);
            preparedStatement.setString(2, strn2);

            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String name = rs.getString("nombre");
                String email = rs.getString("email");
                String clave = rs.getString("pwd");
                String rol = rs.getString("rol");

                user.setUser(name);
                user.setPwd(clave);
                user.setEmail(email);
                user.setRol(rol);
            }
        } catch (SQLException e) {
            Logger.getLogger("").log(null, e.getMessage());
        }
        return user;
    }

}
