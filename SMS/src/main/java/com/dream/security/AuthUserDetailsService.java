package com.dream.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dream.model.User;
import com.dream.service.UserServiceImpl;

/**
 * The AuthUserDetailsService class
 *
 * @author Dileep
 * @version 1.0
 * Date 20/05/2019.
 */
@Service
public class AuthUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserDetailsService.class);

    @Autowired
    private UserServiceImpl userService;

    private org.springframework.security.core.userdetails.User springUser;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        User user = getUserDetail(username);
        if (user != null) {
            springUser = new org.springframework.security.core.userdetails.User(user.getName(),
                    user.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(user.getRole())
            );
            return springUser;
        } else {
            springUser = new org.springframework.security.core.userdetails.User("empty",
                    "empty",
                    false,
                    true,
                    true,
                    false,
                    getAuthorities(1)
            );
            return springUser;
        }
    }

    /**
     * To list out the roles allocates to the user.
     * @param role
     * @return List of Authorities
     */
    public List<GrantedAuthority> getAuthorities(Integer role) {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if (role == 1) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (role == 2) {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authList;
    }

    /**
     * To returns the User details based on user name.
     * @param username
     * @return User Object
     */
    private User getUserDetail(String username) {

        User user = userService.findByName(username);
        if (user == null) {
            logger.warn("user '" + username + "' on null!");
        } else {
            logger.info(user.toString());
        }
        return user;
    }
}
