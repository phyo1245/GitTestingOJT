package com.c_s_boot.controller;

import com.c_s_boot.DTO.subsection.SubSectionCreateDTO;
import com.c_s_boot.DTO.subsection.SubSectionResponseDTO;
import com.c_s_boot.DTO.subsection.SubSectionUpdateDTO;
import com.c_s_boot.service.SubSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subsections")
@CrossOrigin
public class SubSectionController {

    @Autowired
    private SubSectionService subSectionService;

    // Create new SubSection
    @PostMapping
    public ResponseEntity<SubSectionResponseDTO> createSubSection(@RequestBody SubSectionCreateDTO createDTO) {
        SubSectionResponseDTO responseDTO = subSectionService.createSubSection(createDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Update SubSection
    @PutMapping("/{id}")
    public ResponseEntity<SubSectionResponseDTO> updateSubSection(@PathVariable Integer id, @RequestBody SubSectionUpdateDTO updateDTO) {
        SubSectionResponseDTO responseDTO = subSectionService.updateSubSection(id, updateDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Get all SubSections
    @GetMapping
    public ResponseEntity<List<SubSectionResponseDTO>> getAllSubSections() {
        List<SubSectionResponseDTO> responseDTOs = subSectionService.getAllSubSections();
        return ResponseEntity.ok(responseDTOs);
    }

    // Get SubSection by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubSectionResponseDTO> getSubSectionById(@PathVariable Integer id) {
        SubSectionResponseDTO responseDTO = subSectionService.getSubSectionById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // Delete SubSection by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubSection(@PathVariable Integer id) {
        subSectionService.deleteSubSection(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<SubSectionResponseDTO>> getSubsectionsBySectionId(@PathVariable Integer sectionId) {
        List<SubSectionResponseDTO> responseDTOs = subSectionService.findBySectionId(sectionId);
        return ResponseEntity.ok(responseDTOs);
    }

}
