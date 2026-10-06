package err;

/**
 *
 * @author johans caicedo
 */
public class Error extends Exception {

    protected String codeerror;
    
    public Error(String message) {
        this.codeerror = message;
    }
    public String getCodeerror(){
        
        String errorcode = "";
        errorcode = switch (this.codeerror) {
            case "user null" -> "el usuario es nullo";
            case "login null" -> "error de login";
            case "colita" -> "colita pedorra";
            default -> "error inesperado";
        };
        return errorcode;
    }
}
