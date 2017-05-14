package Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Controllers.UserController;
import Exceptions.QuizInternalException;
import Models.UserSession;
import Models.pojo.User;
import Services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by eku on 10.05.17.
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    public CustomAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String login = authentication.getName();
        final String password = authentication.getCredentials().toString();
        User user = null; //userService.auth(login, password);
        if (user != null) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(user.getRole()));
            final UserSession principal = new UserSession(user);
            final UserDetails userDetails = new org.springframework.security.core.userdetails.User(login, password,grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, password, grantedAuths);
            return auth;
        }
        return null;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}