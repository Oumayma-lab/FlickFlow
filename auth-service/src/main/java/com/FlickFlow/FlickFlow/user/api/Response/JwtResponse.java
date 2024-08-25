package com.FlickFlow.FlickFlow.user.api.Response;


public class JwtResponse {
    private final String jwt;
    private final String username;
    public JwtResponse(String jwt, String username) {
        this.jwt = jwt;
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }}
