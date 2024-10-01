package com.c_s_boot.mapper;

import com.c_s_boot.DTO.subsection.SubSectionCreateDTO;
import com.c_s_boot.DTO.subsection.SubSectionResponseDTO;
import com.c_s_boot.DTO.subsection.SubSectionUpdateDTO;
import com.c_s_boot.models.SubSection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubSectionMapper {
    SubSectionMapper INSTANCE = Mappers.getMapper(SubSectionMapper.class);

    @Mapping(source = "sectionId", target = "section.id")
    SubSection toEntity(SubSectionCreateDTO subSectionCreateDTO);

    // Convert from SubSectionUpdateDTO to SubSection entity
    @Mapping(source = "sectionId", target = "section.id")
    SubSection toEntity(SubSectionUpdateDTO subSectionUpdateDTO);

    // Convert from SubSection entity to SubSectionResponseDTO
    @Mapping(source = "section.id", target = "sectionId")
    SubSectionResponseDTO toResponseDTO(SubSection subSection);
}
