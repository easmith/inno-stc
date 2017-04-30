package Controllers;

import Models.UserSession;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by eku on 27.04.17.
 */
@Controller
@SessionAttributes(types = UserSession.class)
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    public UserServiceInterface userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage(Model model) {
        model.addAttribute("menuItem", "index");
        return "landing";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("menuItem", "login");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("login") String login,
                              @RequestParam("password") String password) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");

        User user = null;
        try {
            user = userService.auth(login, password);
        } catch (QuizInternalException e) {
            mav.addObject("fatalError", QuizInternalException.commonMessage);
            return mav;
        }

        if (user != null) {
            mav.addObject("userSession", new UserSession(user));
            if (user.getAdmin()) {
                mav.setViewName("redirect:/admin");
            } else {
                mav.setViewName("redirect:/user");
            }
        } else {
            mav.addObject("error", "Неправильный логин или пароль");
        }

        return mav;
    }

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
//                    redirectAttributes.addFlashAttribute("error_login", "Пользователь с таким логином уже существует");
                    User user = userService.auth("asdasd", "asdasd");
                    redirectAttributes.addAttribute("userSession", new UserSession(user));
//                    mav.addObject("userSession", new UserSession(user));
                    page = "redirect:/register";
                } else {
                    User user = new User(0, registerForm.getName(), registerForm.getLogin(), registerForm.getPassword1(),
                            "admin".equals(registerForm.getLogin()));
                    userService.addUser(user);
//                    mav.addObject("userSession", new UserSession(user));
//                    mav.setViewName("redirect:/" + (user.getAdmin() ? "admin" : "user"));
                }
            } catch (QuizInternalException e) {
                redirectAttributes.addFlashAttribute("fatalError", QuizInternalException.commonMessage);
            }
        }

//        User user = null;
//        try {
//            user = userService.auth("asdasd", "");
//        } catch (QuizInternalException e) {
//            e.printStackTrace();
//        }
//        mav.addObject("userSession", new UserSession(user));
//        mav.setViewName("redirect:/" + (user.getAdmin() ? "admin" : "user"));

        LOGGER.info(page);
        return page;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(SessionStatus status) {

        ModelAndView mav = new ModelAndView();
        status.setComplete();
        mav.setViewName("redirect:/");

        return mav;
    }
}
