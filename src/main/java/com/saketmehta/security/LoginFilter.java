package com.saketmehta.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Saket
 * Date: 28/02/17
 * Time: 8:23 PM
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private final LoginSuccessHandler loginSuccessHandler;

    LoginFilter(AntPathRequestMatcher authMatcher) {
        super(authMatcher);
        loginSuccessHandler = new LoginSuccessHandler();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null) {
            email = "";
        }

        if (password == null) {
            password = "";
        }

        email = email.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        loginSuccessHandler.onAuthenticationSuccess(request, response, authResult);
    }
}
