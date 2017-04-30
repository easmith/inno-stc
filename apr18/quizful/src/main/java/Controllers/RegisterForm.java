package Controllers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by eku on 29.04.17.
 */
public class RegisterForm {

    @NotNull
    @Pattern(regexp="^[А-яёЁ]{3,16}$", message = "От 3 до 16 символов кириллицы")
    public String name;
    @NotNull
    @Pattern(regexp="^[A-z]{3,16}$", message = "От 3 до 16 символов латиницы")
    public String login;
    @NotNull
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    public String password1;
    @NotNull
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    public String password2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
