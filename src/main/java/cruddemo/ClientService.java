package cruddemo;

import entity.Client;
import form.AdminForm;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;



/**
 *
 * @author johans caicedo
 */
public class ClientService extends ServiceApp {

     private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminForm.class.getName());
   
    protected Connection connection;

    public ClientService() {
        
        connection = super.getConnection();
    }

    /***
     * 
     * 
     * 
     * @param client <p> Client Clase modelo</p>
     * @return boolean <p>return true si guarda el registro, false cuando hay error sql</p>
     ***/
    public boolean createClient(Client client) {

        boolean create = true;
        try {

            String sql = "INSERT INTO cliente (nombre, pwd, correo, saldo)"
                    + " VALUES (?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getPassword());
            stmt.setString(3, client.getCorreo());
            stmt.setInt(4, client.getSaldo());

            create = stmt.execute();
            stmt.close();

        } catch (SQLException error) {

           logger.log(Level.SEVERE, error.getMessage());
            return create;
        }
        return create;
    }

    /**
     * 
     * @return List
    **
     */
    public List<Client> listClients() {

        try {

            List<Client> list = new ArrayList<>();

            String sql = "SELECT * FROM cliente";

            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client client = new Client();

                client.setId(rs.getInt("id"));
                client.setName(rs.getString("nombre"));
                client.setPassword(rs.getString("pwd"));
                client.setCorreo(rs.getString("correo"));
                client.setSaldo(rs.getInt("saldo"));

                list.add(client);

            }

            return list;

        } catch (SQLException error) {
            
            return null;
        }
    }

    /**
     * TODO buscar por una llave unique
     *
     * @param nombre
     * @return 
    **
     */
    public List<Client> findClient(String nombre) {

        try {

            List<Client> list = new ArrayList<>();

            String sql = "SELECT * FROM cliente WHERE nombre = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client client = new Client();

                client.setId(rs.getInt("id"));
                client.setName(rs.getString("nombre"));
                client.setPassword(rs.getString("pwd"));
                client.setCorreo(rs.getString("correo"));
                client.setSaldo(rs.getInt("saldo"));

                list.add(client);

            }

            return list;

        } catch (SQLException error) {
           
            return null;
        }
    }
}
