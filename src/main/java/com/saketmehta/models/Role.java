package com.saketmehta.models;

/**
 * User: Saket
 * Date: 25/02/17
 * Time: 12:12 PM
 */
public enum Role {
    ADMIN, USER;

    public String authority() {
        return "ROLE_" + this.name();
    }
}