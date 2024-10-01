package com.c_s_boot.service;

import com.c_s_boot.DTO.cheatSheet.CheatSheetCreateDTO;
import com.c_s_boot.DTO.cheatSheet.CheatSheetResponseDTO;
import com.c_s_boot.DTO.cheatSheet.CheatSheetUpdateDTO;
import com.c_s_boot.mapper.CheatSheetMapper;
import com.c_s_boot.models.CheatSheet;
import com.c_s_boot.models.Section;
import com.c_s_boot.models.SubSection;
import com.c_s_boot.models.Type;
import com.c_s_boot.models.User;
import com.c_s_boot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheatSheetService {

    @Autowired
    private CheatSheetRepository cheatSheetRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubSectionRepository subSectionRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private UserRepository userRepository;

    private final CheatSheetMapper cheatSheetMapper = CheatSheetMapper.INSTANCE;

    // Create new CheatSheet
    public CheatSheetResponseDTO createCheatSheet(CheatSheetCreateDTO createDTO) {
        // Fetch related entities by IDs
        Section section = sectionRepository.findById(createDTO.getSectionId())
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));
        SubSection subSection = subSectionRepository.findById(createDTO.getSubsectionId())
                .orElseThrow(() -> new IllegalArgumentException("SubSection not found"));
        Type type = typeRepository.findById(createDTO.getTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Type not found"));
        User user = userRepository.findById(createDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Convert DTO to entity and set related entities
        CheatSheet cheatSheet = cheatSheetMapper.toEntity(createDTO);
        cheatSheet.setSection(section);
        cheatSheet.setSubsection(subSection);
        cheatSheet.setType(type);
        cheatSheet.setUser(user);

        // Save the CheatSheet entity
        CheatSheet savedCheatSheet = cheatSheetRepository.save(cheatSheet);
        return cheatSheetMapper.toResponseDTO(savedCheatSheet);
    }

    // Update existing CheatSheet
    public CheatSheetResponseDTO updateCheatSheet(Integer id, CheatSheetUpdateDTO updateDTO) {
        // Find existing CheatSheet
        CheatSheet existingCheatSheet = cheatSheetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cheat Sheet not found"));

        // Fetch related entities by IDs
        Section section = sectionRepository.findById(updateDTO.getSectionId())
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));
        SubSection subSection = subSectionRepository.findById(updateDTO.getSubsectionId())
                .orElseThrow(() -> new IllegalArgumentException("SubSection not found"));
        Type type = typeRepository.findById(updateDTO.getTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Type not found"));

        // Convert DTO to entity and update the fields
        CheatSheet updatedCheatSheet = cheatSheetMapper.toEntity(updateDTO);
        updatedCheatSheet.setId(existingCheatSheet.getId());
        updatedCheatSheet.setSection(section);
        updatedCheatSheet.setSubsection(subSection);
        updatedCheatSheet.setType(type);
        updatedCheatSheet.setUser(existingCheatSheet.getUser()); // Retain original user

        // Save the updated CheatSheet
        updatedCheatSheet = cheatSheetRepository.save(updatedCheatSheet);
        return cheatSheetMapper.toResponseDTO(updatedCheatSheet);
    }

    // Get all CheatSheets
    public List<CheatSheetResponseDTO> getAllCheatSheets() {
        List<CheatSheet> cheatSheets = cheatSheetRepository.findAll();
        return cheatSheets.stream()
                .map(cheatSheetMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Get CheatSheet by ID
    public CheatSheetResponseDTO getCheatSheetById(Integer id) {
        CheatSheet cheatSheet = cheatSheetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cheat Sheet not found"));
        return cheatSheetMapper.toResponseDTO(cheatSheet);
    }

    // Delete CheatSheet by ID
    public void deleteCheatSheet(Integer id) {
        CheatSheet cheatSheet = cheatSheetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cheat Sheet not found"));
        cheatSheetRepository.delete(cheatSheet);
    }

    // Get all CheatSheets with relations using JOIN FETCH
    public List<CheatSheetResponseDTO> getAllCheatSheetsWithRelations() {
        List<CheatSheet> cheatSheets = cheatSheetRepository.findAllWithAllRelations();
        return cheatSheets.stream()
                .map(cheatSheetMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Get CheatSheet by ID with relations using JOIN FETCH
    public CheatSheetResponseDTO getCheatSheetByIdWithRelations(Integer id) {
        CheatSheet cheatSheet = cheatSheetRepository.findByIdWithAllRelations(id);
        return cheatSheetMapper.toResponseDTO(cheatSheet);
    }

}
