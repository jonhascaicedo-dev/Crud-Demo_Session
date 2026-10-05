package entity;

/**
 *
 * @author USUARIO
 */
public class Movement {

    protected int id;
    String nmUser;
    String date;
    String movement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNmUser() {
        return nmUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    @Override
    public String toString() {
        return "Movement{" + "nmUser=" + nmUser + ", date=" + date + ", movement=" + movement + '}';
    }
        
}
