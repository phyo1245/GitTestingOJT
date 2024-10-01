package com.c_s_boot.mapper;

import com.c_s_boot.DTO.section.SectionCreateDTO;
import com.c_s_boot.DTO.section.SectionResponseDTO;
import com.c_s_boot.DTO.section.SectionUpdateDTO;
import com.c_s_boot.DTO.type.TypeCreateDTO;
import com.c_s_boot.DTO.type.TypeResponseDTO;
import com.c_s_boot.DTO.type.TypeUpdateDTO;
import com.c_s_boot.models.Section;
import com.c_s_boot.models.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeMapper {
    TypeMapper INSTANCE = Mappers.getMapper(TypeMapper.class);

    // Mapping from DTO to Entity
    @Mapping(target = "id", ignore = true)  // Ignoring id for CreateDTO
    Type toEntity(TypeCreateDTO typeCreateDTO);

    @Mapping(target = "id", source = "id")  // Mapping id for UpdateDTO
    Type toEntity(TypeUpdateDTO typeUpdateDTO);

    // Mapping from Entity to DTO
    TypeResponseDTO toResponseDTO(Type type);
}
