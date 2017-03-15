package com.saketmehta.security.services;

import com.saketmehta.security.models.Authorities;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * User: Saket
 * Date: 25/02/17
 * Time: 12:09 PM
 */
@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User("user", "user", Collections.singletonList(Authorities.USER));
        User admin = new User("user", "user", Collections.singletonList(Authorities.ADMIN));

        switch (username) {
            case "admin":
                return admin;
            case "user":
                return user;
            default:
                throw new UsernameNotFoundException(username);
        }
    }
}
