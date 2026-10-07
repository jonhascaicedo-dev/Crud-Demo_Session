package cruddemo;

import entity.Session;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author johans caicedo
 */
public class SessionService extends ServiceApp {

    protected Session session;

    /**
     * *
     *
     *
     * @param s Session
     * @return Session instancia de la session inicializada
     *
     **
     */
    public Session getSession(Session s) {

        if (s == null) {
            //Inicializacion del objeto to prevent cross site scripting
            User user = new User();
            user.setId(0);
            user.setUser("");
            this.session = new Session(user);
        } else {
            this.session = s;
        }

        return this.session;
    }

    /**
     * *
     *
     *
     * @param s Sesssion instancia a setear 
     *
     */
    public void setSession(Session s) {

        if (s != null) {
            this.session = s;
        } else {
            User usuario = new User();
            session = new Session(usuario);
        }

    }

    /**
     * *
     *
     *
     * @param user
     * <p>
     * Usuario que realiza el query</p>
     * @param date
     * <p>
     * Fecha en la que hace el query</p>
     * @param movement
     * <p>
     * Tipo de query</p>
     * @return boolean
     * <p>
     * Tipo de query</p> *
     */
    public boolean setQuery(User user, Date date, String movement) {

        boolean resultado = false;
        
        try {

            Connection con = getConection("auditoryapp", "root", "root");
            String query = "INSERT INTO movements(user, date, movement)"
                    + " VALUES (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, user.getUser());
            stmt.setString(2, date.toString());
            stmt.setString(3, movement);
            resultado = stmt.execute();
            stmt.close();

        } catch (SQLException error) {

         

        }

        return resultado;
    }

    /**
     * *
     *
     *
     * @param url
     * <p>
     * Dominio del servidor de base de datos</p>
     * @param usr
     * <p>
     * Usuario de la base de datos</p>
     * @param pwd
     * <p>
     * Contraseña de la basde datos</p>
     * @return Connection *
     */
    public static Connection getConection(String url, String usr, String pwd) {

        ServiceApp serviceApp = new ServiceApp();

        return serviceApp.customConnection(url, usr, pwd);
    }

    /**
     * *
     *
     *
     * @return Connection *
     */
    public static Connection getConection() {

        ServiceApp serviceApp = new ServiceApp();

        return serviceApp.getConnection();
    }

    /**
     * *
     *
     *
     * @param session
     * @return boolean *
     */
    public boolean checkSession(Session session) {

        boolean x1 = false;

        if (session == null) {
            x1 = true;
        }
        return x1;
    }

}
