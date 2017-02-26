package com.saketmehta.models;

/**
 * User: Saket
 * Date: 25/02/17
 * Time: 11:35 AM
 */
public class Greeting {
    private String message;

    public Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
