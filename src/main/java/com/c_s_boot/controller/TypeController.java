package com.c_s_boot.controller;

import com.c_s_boot.DTO.type.TypeCreateDTO;
import com.c_s_boot.DTO.type.TypeResponseDTO;
import com.c_s_boot.DTO.type.TypeUpdateDTO;
import com.c_s_boot.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
@CrossOrigin
public class TypeController {

    @Autowired
    private TypeService typeService;

    // Create new Section
    @PostMapping
    public ResponseEntity<TypeResponseDTO> createType(@RequestBody TypeCreateDTO createDTO) {
        TypeResponseDTO responseDTO = typeService.createType(createDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Update Section
    @PutMapping("/{id}")
    public ResponseEntity<TypeResponseDTO> updateType(@PathVariable Integer id, @RequestBody TypeUpdateDTO updateDTO) {
        TypeResponseDTO responseDTO = typeService.updateType(id, updateDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Get all Sections
    @GetMapping
    public ResponseEntity<List<TypeResponseDTO>> getAllSections() {
        List<TypeResponseDTO> responseDTOs = typeService.getAllTypes();
        return ResponseEntity.ok(responseDTOs);
    }

    // Get Section by ID
    @GetMapping("/{id}")
    public ResponseEntity<TypeResponseDTO> getTypeById(@PathVariable Integer id) {
        TypeResponseDTO responseDTO = typeService.getTypeById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // Delete Section by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer id) {
        typeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}
