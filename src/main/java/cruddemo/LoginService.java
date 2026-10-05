package cruddemo;

import entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class LoginService {

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

    public User login(String strn1, String strn2) {

        User  user = new User();
                // Step 1: Establishing a Connection
        try (Connection connection = getConnection(); 
                 // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_);) {

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

        }
        return user;
    }

    public static void main(String[] args) {
        LoginService service = new LoginService();
        service.getConnection();
    }
}
