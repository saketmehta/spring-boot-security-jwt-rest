package com.saketmehta.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Saket
 * Date: 26/02/17
 * Time: 12:13 AM
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            throw new InsufficientAuthenticationException("No 'Authorization' header present in request.");
        }
        if (!authHeader.startsWith("Bearer")) {
            throw new BadCredentialsException("Invalid format for 'Authorization' header.");
        }
        String jwt = authHeader.substring(7);
        return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(jwt));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
