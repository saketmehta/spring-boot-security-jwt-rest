package com.saketmehta.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.saketmehta.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;

/**
 * User: Saket
 * Date: 25/02/17
 * Time: 1:01 PM
 */
class JwtUtils {
    private static final String secret = "ytftyfuggcr3w!56";
    private static final Gson gson = new Gson();

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    static User parseToken(String token) throws IOException {
        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return gson.fromJson((String) body.get("user"), User.class);
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param user the user for which the token will be generated
     * @return the JWT token
     */
    static String generateToken(User user) throws JsonProcessingException {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("user", gson.toJson(user));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
