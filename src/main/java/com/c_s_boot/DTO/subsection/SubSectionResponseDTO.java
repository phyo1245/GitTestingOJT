package com.c_s_boot.DTO.subsection;

import com.c_s_boot.DTO.section.SectionResponseDTO;
import lombok.Data;

@Data
public class SubSectionResponseDTO {
    private Integer id;

    private String sub_section;

    private Integer sectionId;  // Embedded DTO for the associated section
}
