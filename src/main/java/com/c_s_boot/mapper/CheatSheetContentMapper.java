package com.c_s_boot.mapper;

import com.c_s_boot.DTO.CheatSheetContentDTO;
import com.c_s_boot.models.CheatSheetContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheatSheetContentMapper {

    // Map CheatSheet ID from the CheatSheet entity
    @Mapping(source = "cheatSheet.id", target = "cheatSheetId")
    CheatSheetContentDTO toDTO(CheatSheetContent cheatSheetContent);

    // Create CheatSheet object manually in the service layer from cheatSheetId
    @Mapping(target = "cheatSheet.id", source = "cheatSheetId")
    CheatSheetContent toEntity(CheatSheetContentDTO cheatSheetContentDTO);
}
