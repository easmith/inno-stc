package models.POJO;

/**
 * Created by eku on 20.04.17.
 */
public class User {

    private int id;
    private String login;
    private String password;

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    private Boolean isBlocked;

    public User(int id, String login, String password, Boolean isBlocked) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.isBlocked = isBlocked;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
