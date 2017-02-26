package com.saketmehta.security;

import com.saketmehta.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * User: Saket
 * Date: 25/02/17
 * Time: 3:35 PM
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;

    @Autowired
    public JwtAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String token = (String) authentication.getCredentials();

        String email = JwtUtils.parseToken(token);
        UserDetails user = userService.loadUserByUsername(email);

        if (!user.getPassword().equals(user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (user.getAuthorities() == null) throw new InsufficientAuthenticationException("User has no roles assigned");

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(email, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO: 25/02/17 make own class for jwt auth token which implements Authentication
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
