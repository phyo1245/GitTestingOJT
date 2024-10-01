package com.c_s_boot.mapper;

import com.c_s_boot.DTO.cheatSheet.CheatSheetCreateDTO;
import com.c_s_boot.DTO.cheatSheet.CheatSheetResponseDTO;
import com.c_s_boot.DTO.cheatSheet.CheatSheetUpdateDTO;
import com.c_s_boot.models.CheatSheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CheatSheetMapper {
    CheatSheetMapper INSTANCE = Mappers.getMapper(CheatSheetMapper.class);

    // Mapping from CreateDTO to Entity (with related entities already set in the service)
    @Mapping(target = "id", ignore = true)  // Ignoring id for CreateDTO
    CheatSheet toEntity(CheatSheetCreateDTO cheatSheetCreateDTO);

    // Mapping from UpdateDTO to Entity (with related entities already set in the service)
            @Mapping(target = "id", ignore = true)  // Keep original ID from the entity
    CheatSheet toEntity(CheatSheetUpdateDTO cheatSheetUpdateDTO);

    // Mapping from Entity to ResponseDTO
    @Mappings({
            @Mapping(source = "type.type", target = "type"),  // Map Type to String
            @Mapping(source = "section.section", target = "section"),  // Map Section to String
            @Mapping(source = "subsection.sub_section", target = "subsection"),  // Map SubSection to String
            @Mapping(source = "user.username", target = "username")  // Map User to username
    })
    CheatSheetResponseDTO toResponseDTO(CheatSheet cheatSheet);
}
