package com.c_s_boot.models;

import com.c_s_boot.models.CheatSheet;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CheatSheetContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "cheat_sheet_id")
    private CheatSheet cheatSheet;

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;
}
