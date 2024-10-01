package com.c_s_boot.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sub_sections")
public class SubSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sub_section_name")
    private String sub_section;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}
