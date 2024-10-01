package com.c_s_boot.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cheat_sheets")
public class CheatSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "subsection_id")
    private SubSection subsection;

    @Column(name = "languages")
    private String language;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Joining the User entity

    @Column(name = "is_draft")
    private boolean draft;

}
