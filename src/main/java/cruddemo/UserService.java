package cruddemo;

import static com.mysql.cj.conf.PropertyKey.logger;
import entity.User;
import form.AdminForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author johans caicedo
 */
public class UserService extends ServiceApp {

    private static final java.util.logging.Logger logger
            = java.util.logging.Logger.getLogger(AdminForm.class.getName());
    protected Connection connection;

    public UserService() {

        connection = super.getConnection();
    }

    /**
     * *
     *
     *
     * @param user
     * @return 
     *
     */
    public boolean createUser(User user) {

        boolean create = true;
        try {

            String sql = "INSERT INTO user (rol, nombre, pwd, email)"
                    + " VALUES (?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getRol());
            stmt.setString(2, user.getUser());
            stmt.setString(3, user.getPwd());
            stmt.setString(4, user.getEmail());

            create = stmt.execute();
            stmt.close();

        } catch (SQLException error) {

            logger.log(Level.SEVERE, error.getMessage());
            return false;
        }
        return create;

    }

    /**
     * *
     *
     *
     * @return user List
     **
     */
    public List<User> listUsers() {

        List<User> list;
        try {

            list = new ArrayList<>();

            String sql = "SELECT * FROM login";

            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User client = new User();

                client.setId(rs.getInt("id"));
                client.setUser(rs.getString("nombre"));
                client.setPwd(rs.getString("pwd"));
                client.setEmail(rs.getString("email"));

                list.add(client);

            }
            return list;

        } catch (SQLException error) {

            return null;
        }
    }
}
