package com.saketmehta.api;

import com.saketmehta.models.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Saket
 * Date: 25/02/17
 * Time: 11:34 AM
 */
@RestController
public class Hello {

    @GetMapping("api/hello")
    public Greeting getGreeting() {
        return new Greeting("Hello World!");
    }

    @GetMapping("api/secret/hello")
    public Greeting getSecretGreeting() {
        return new Greeting("You have reached a secret place!");
    }

}
