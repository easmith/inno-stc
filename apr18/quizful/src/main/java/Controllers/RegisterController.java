package Controllers;

import Models.UserSession;
import Models.forms.RegisterForm;
import Models.pojo.User;
import Services.UserServiceInterface;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by eku on 01.05.17.
 */
@Controller
public class RegisterController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    public UserServiceInterface userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("menuItem", "register");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid RegisterForm registerForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("registerForm", registerForm);

        String page = "redirect:/register";

        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                FieldError fieldError = (FieldError) objectError;
                LOGGER.info("error_" + fieldError.getField() + " = " + fieldError.getDefaultMessage());
                redirectAttributes.addFlashAttribute("error_" + fieldError.getField(), fieldError.getDefaultMessage());
            }
        } else {
            try {
                if (!registerForm.getPassword1().equals(registerForm.getPassword2())) {
                    redirectAttributes.addFlashAttribute("error_password2", "Пароли не совпали");
                } else if (userService.existUser(registerForm.getLogin())) {
                    redirectAttributes.addFlashAttribute("registerError", "Пользователь с таким логином уже существует");
                } else {
                    User user = new User(0, registerForm.getName(), registerForm.getLogin(), registerForm.getPassword1(),
                            "admin".equals(registerForm.getLogin()));
                    userService.addUser(user);
                    redirectAttributes.addFlashAttribute("registerMessage", "Вы успешно зарегистрированы");
                    page = "redirect:/login";
                }
            } catch (QuizInternalException e) {
                redirectAttributes.addFlashAttribute("fatalError", QuizInternalException.commonMessage);
            }
        }

        LOGGER.info(page);
        return page;
    }

}
