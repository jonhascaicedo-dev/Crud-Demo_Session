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

    public void setSession(Session s) {
        this.session = s;
    }

    /**
     * *
     *
     *
     * @param user usuario que realiza el query
     * @param date fecha en la que hace el query
     * @param movement Tipo de query
     * @return 
     *
     *
     */
    public boolean setQuery(User user, Date date, String movement) {

        try {

            Connection con = getConection("auditoryapp", "root", "root");
            String query = "INSERT INTO movements(user, date, movement)"
                    + " VALUES (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, user.getUser());
            stmt.setString(2, date.toString());
            stmt.setString(3, movement);
            stmt.execute();
            stmt.close();

        } catch (SQLException error) {

            JOptionPane.showMessageDialog(null, "Error:!" + error);

        }

        return false;

    }

    public SessionService() {

    }

    public static Connection getConection(String url, String usr, String pwd) {

        ServiceApp serviceApp = new ServiceApp();

        return serviceApp.customConnection(url, usr, pwd);
    }

    public static Connection getConection() {

        ServiceApp serviceApp = new ServiceApp();

        return serviceApp.getConnection();
    }

    public boolean checkSession(Session session) {

        boolean x1 = false;

        if (session == null) {
            x1 = true;
            throw new WrongThreadException("Error cross script exception");
        }
        return x1;
    }

}
