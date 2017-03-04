package com.saketmehta.security;

import com.saketmehta.models.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * User: Saket
 * Date: 25/02/17
 * Time: 3:35 PM
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String token = (String) authentication.getCredentials();

        User user = JwtUtils.parseToken(token);

        if (user == null) {
            throw new BadCredentialsException("Authentication Failed. JWT is not valid.");
        }

        JwtAuthenticationToken result = new JwtAuthenticationToken(user, token, user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
