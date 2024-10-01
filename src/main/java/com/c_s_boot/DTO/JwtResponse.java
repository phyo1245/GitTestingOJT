package com.c_s_boot.DTO;

import lombok.Data;

import java.util.Collection;

@Data
public class JwtResponse {
    private String token;
    private String email;
    private Integer id;
    private Collection<?> authorities;

    public JwtResponse(String token, Integer id, String email, Collection<?> authorities) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.authorities = authorities;
    }

}

