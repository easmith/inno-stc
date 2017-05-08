package Controllers.Handlers;

/**
 * Created by eku on 08.05.17.
 */

import Models.UserSession;
import Services.UserService;
import exceptions.QuizInternalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;


public class RoleBasedUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final Logger LOGGER = Logger.getLogger(RoleBasedUrlAuthenticationSuccessHandler.class);

    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);

        clearAuthenticationAttributes(request);

        HttpSession session = request.getSession(true);

        User principal = (User) authentication.getPrincipal();

        try {
            Models.pojo.User user = userService.findUserByLogin(principal.getUsername());
            session.setAttribute("userSession", new UserSession(user));
        } catch (QuizInternalException e) {
            LOGGER.error(e);
            throw new IllegalStateException();
        }

        LOGGER.info("Redirecting to: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String authString = authority.getAuthority();
            if ("ROLE_USER".equals(authString)) {
                return "/user";
            } else if ("ROLE_ADMIN".equals(authString)) {
                return "/admin";
            }
        }
        throw new IllegalStateException();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
