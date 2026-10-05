package err;

/**
 *
 * @author USUARIO
 */
public class Error extends Exception {

    String codeerror;
    
    public Error(String message) {
        this.codeerror = message;
    }
    public String getCodeerror(){
        
        String errorcode = "";
        switch (this.codeerror) {
            case "user null":
                errorcode = "el usuario es nullo";
                break;
            case "login null":
                errorcode = "error de login";
                break;
            case "colita":
                errorcode = "colita pedorra";
                break;
            default:
                errorcode = "error inesperado";
        }
        return errorcode;
    }
}
