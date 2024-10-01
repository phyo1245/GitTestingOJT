package com.c_s_boot.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;
}
