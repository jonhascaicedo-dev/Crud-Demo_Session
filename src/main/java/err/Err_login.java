package err;

/**
 *
 * @author johans caicedo
 */
public class Err_login {

    protected String err_;
    protected static int counter;

    public Err_login() {

        this.err_ = "";
        Err_login.counter = 0;
    }

    public void setErr(String str) {
        this.err_ = str;
        //Err_login.counter = Err_login.counter += 1;
    }

    public int getCounter() {
        return Err_login.counter;
    }

    public String getErr() {
        return this.err_;
    }
}
