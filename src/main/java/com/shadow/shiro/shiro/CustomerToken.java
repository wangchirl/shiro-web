package com.shadow.shiro.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class CustomerToken implements AuthenticationToken {

    private String token;

    public CustomerToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
