package com.c_s_boot.controller;

import com.c_s_boot.DTO.section.SectionCreateDTO;
import com.c_s_boot.DTO.section.SectionResponseDTO;
import com.c_s_boot.DTO.section.SectionUpdateDTO;
import com.c_s_boot.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
@CrossOrigin
public class SectionController {

    @Autowired
    private SectionService sectionService;

    // Create new Section
    @PostMapping
    public ResponseEntity<SectionResponseDTO> createSection(@RequestBody SectionCreateDTO createDTO) {
        SectionResponseDTO responseDTO = sectionService.createSection(createDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Update Section
    @PutMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> updateSection(@PathVariable Integer id, @RequestBody SectionUpdateDTO updateDTO) {
        SectionResponseDTO responseDTO = sectionService.updateSection(id, updateDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Get all Sections
    @GetMapping
    public ResponseEntity<List<SectionResponseDTO>> getAllSections() {
        List<SectionResponseDTO> responseDTOs = sectionService.getAllSections();
        return ResponseEntity.ok(responseDTOs);
    }

    // Get Section by ID
    @GetMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> getSectionById(@PathVariable Integer id) {
        SectionResponseDTO responseDTO = sectionService.getSectionById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // Delete Section by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}
