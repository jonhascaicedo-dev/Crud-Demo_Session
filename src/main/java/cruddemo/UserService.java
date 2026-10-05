package cruddemo;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class UserService extends ServiceApp {

    Connection connection;

    public UserService() {
        //super();
        connection = super.getConnection();
    }
    
    public boolean createUser(User user){
        return false;
    }

    public List<User> listUsers() {

        try {

            List<User> list = new ArrayList<>();

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
            JOptionPane.showMessageDialog(null, "Error" + error);
            return null;
        }
    }
}
