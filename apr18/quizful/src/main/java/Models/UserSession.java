package Models;

import Models.pojo.User;

/**
 * Created by eku on 28.04.17.
 */
public class UserSession {
    public String login;
    public String name;
    public Boolean isAdmin;

    public UserSession(String login, String name, Boolean admin) {
        this.login = login;
        this.name = name;
        this.isAdmin = admin;
    }

    public UserSession(User user) {
        this.name = user.getName();
        this.login = user.getLogin();
        this.isAdmin = user.getAdmin();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
