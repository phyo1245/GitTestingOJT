package com.c_s_boot.mapper;

import com.c_s_boot.DTO.section.SectionCreateDTO;
import com.c_s_boot.DTO.section.SectionResponseDTO;
import com.c_s_boot.DTO.section.SectionUpdateDTO;
import com.c_s_boot.models.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SectionMapper {
    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);

    // Mapping from DTO to Entity
    @Mapping(target = "id", ignore = true)  // Ignoring id for CreateDTO
    Section toEntity(SectionCreateDTO sectionCreateDTO);

    @Mapping(target = "id", source = "id")  // Mapping id for UpdateDTO
    Section toEntity(SectionUpdateDTO sectionUpdateDTO);

    // Mapping from Entity to DTO
    SectionResponseDTO toResponseDTO(Section section);
}
