package com.saketmehta.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Saket
 * Date: 26/02/17
 * Time: 6:33 PM
 */
@RestController
public class AuthenticationResource {
    @GetMapping("api/auth/login")
    public String login() {
        return "Logging in...";
    }
}
