package com.c_s_boot.DTO.cheatSheet;

import lombok.Data;

@Data
public class CheatSheetUpdateDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer typeId;
    private Integer sectionId;
    private Integer subsectionId;
    private String language;
}
