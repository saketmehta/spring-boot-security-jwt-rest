package com.saketmehta.security.login;

import com.google.gson.Gson;
import com.saketmehta.security.jwt.JwtUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Saket
 * Date: 28/02/17
 * Time: 8:34 PM
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String token = JwtUtils.generateToken((User) authentication.getPrincipal());
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        response.getWriter().write(new Gson().toJson(result));
    }
}
