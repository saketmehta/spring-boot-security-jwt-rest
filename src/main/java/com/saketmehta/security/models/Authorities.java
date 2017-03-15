package com.saketmehta.security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * User: Saket
 * Date: 25/02/17
 * Time: 12:12 PM
 */
public class Authorities {
    public static final GrantedAuthority USER  = new SimpleGrantedAuthority("ROLE_USER");
    public static final GrantedAuthority ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");
}