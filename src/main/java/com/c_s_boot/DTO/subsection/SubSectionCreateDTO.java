package com.c_s_boot.DTO.subsection;

import lombok.Data;

@Data
public class SubSectionCreateDTO {
    private String sub_section;
    private Integer sectionId;  // Foreign key to the Section entity
}
