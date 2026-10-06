package cruddemo;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author johans caicedo
 */
public class UserService extends ServiceApp {

    protected Connection connection;

    public UserService() {
        
        connection = super.getConnection();
    }
    
    /***
     * 
     * 
     * @param user
     * @return 
     **/
    public boolean createUser(User user){
        return false;
    }

    /***
     *
     * 
     * @return user List
     ***/
    public List<User> listUsers() {

        List<User> list;
        try {

            list  = new ArrayList<>();

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
