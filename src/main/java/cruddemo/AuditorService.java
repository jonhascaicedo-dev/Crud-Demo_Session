package cruddemo;

import entity.Movement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author johans caicedo
 */
public class AuditorService extends ServiceApp {

    protected Connection connection;

    public AuditorService() {
        connection = super.customConnection("auditoryapp", "root", "root");
    }

     /***
     * 
     * @return List<Movement>
     * @see Get movements from auditor layer
     ***/
    public List<Movement> listLogin() {
        
        return null;
        
    }
    
    /***
     * 
     * @return List<Movement>
     * @see Get movements from auditor layer
     ***/
    public List<Movement> listMovements() {

        try {

            List<Movement> result=  new ArrayList<>();

            String sql = "SELECT * FROM movements";

            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movement movement = new Movement();

                movement.setId(rs.getInt("id"));
                movement.setNmUser(rs.getString("user"));
                movement.setDate(rs.getString("date"));
                movement.setMovement(rs.getString("movement"));
                

                result.add(movement);
            }

            return result;

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error" + error);
            return null;
        }
    }

}
