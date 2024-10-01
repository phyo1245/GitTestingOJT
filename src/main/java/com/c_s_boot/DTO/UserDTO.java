package com.c_s_boot.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String password; // Include this only if needed for registration
}

