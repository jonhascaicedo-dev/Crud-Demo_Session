package entity;

import cruddemo.SessionService;
import err.Err_login;
import java.util.Date;
import java.sql.Connection;

/**
 *
 * @author USUARIO
 */
public class Session {

    //protected SessionService sessionService;
    protected Err_login err_login;
    protected Error apperror;
    protected User user;

    public Session(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public String geterrLogin() {
        return err_login.getErr();
    }

    public void setError(Error error) {
        this.apperror = error;
    }

    public void setError(Err_login error) {
        this.err_login = error;
    }

   
}
