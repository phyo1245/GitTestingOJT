package com.c_s_boot.DTO.cheatSheet;

import lombok.Data;

@Data
public class CheatSheetResponseDTO {
        private Integer id;
        private String title;
        private String description;
        private String type;
        private String section;
        private String subsection;
        private String language;
        private String username;
    }

