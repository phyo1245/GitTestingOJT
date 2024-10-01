package com.c_s_boot.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "section_name")
    private String section;

}
