package Models.forms;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Created by eku on 29.04.17.
 */
public class RegisterForm {

    @NotNull
    @Pattern(regexp="^[А-яёЁ]{3,16}$", message = "От 3 до 16 символов кириллицы")
    public String name;
    @NotNull
    @Pattern(regexp="^[A-z0-9]{3,16}$", message = "От 3 до 16 символов латиницы или цифры")
    public String login;
    @NotNull
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    public String password1;
    @NotNull
    @Size(min = 3, max = 16, message = "От 3 до 16 символов")
    public String password2;

    @AssertTrue(message = "Пароли должны совпадать")
    public boolean hasNotEqualPass() {
        return password1.equals(password2);
    }

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
