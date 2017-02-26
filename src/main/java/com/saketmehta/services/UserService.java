package com.saketmehta.services;

import com.google.common.collect.Lists;
import com.saketmehta.models.Role;
import com.saketmehta.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 25/02/17
 * Time: 12:09 PM
 */
@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setName("Saket");
        user.setUserId(1000L);
        user.setEmail("saket.m17@outlook.com");
        user.setPassword("saket");
        user.setRoles(Lists.newArrayList(Role.ADMIN));
        return user;
    }
}
