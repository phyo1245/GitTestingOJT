package com.c_s_boot.controller;

import com.c_s_boot.DTO.cheatSheet.CheatSheetCreateDTO;
import com.c_s_boot.DTO.cheatSheet.CheatSheetResponseDTO;
import com.c_s_boot.DTO.cheatSheet.CheatSheetUpdateDTO;
import com.c_s_boot.service.CheatSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cheatSheets")
@CrossOrigin(origins = "http://localhost:4200") // Replace with your Angular app URL
public class CheatSheetController {
    @Autowired
    private CheatSheetService cheatSheetService;

    // Create new Section
    @PostMapping
    public ResponseEntity<CheatSheetResponseDTO> createCheatSheet(@RequestBody CheatSheetCreateDTO createDTO) {
        CheatSheetResponseDTO responseDTO = cheatSheetService.createCheatSheet(createDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Update Section
    @PutMapping("/{id}")
    public ResponseEntity<CheatSheetResponseDTO> updateCheatSheet(@PathVariable Integer id, @RequestBody CheatSheetUpdateDTO updateDTO) {
        CheatSheetResponseDTO responseDTO = cheatSheetService.updateCheatSheet(id, updateDTO);
        return ResponseEntity.ok(responseDTO);
    }

//    // Get all Sections
//    @GetMapping
//    public ResponseEntity<List<CheatSheetResponseDTO>> getAllCheatSheets() {
//        List<CheatSheetResponseDTO> responseDTOs = cheatSheetService.getAllCheatSheets();
//        return ResponseEntity.ok(responseDTOs);
//    }
//
//    // Get Section by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<CheatSheetResponseDTO> getCheatSheetById(@PathVariable Integer id) {
//        CheatSheetResponseDTO responseDTO = cheatSheetService.getCheatSheetById(id);
//        return ResponseEntity.ok(responseDTO);
//    }

    // Get all CheatSheets with relations
    @GetMapping
    public ResponseEntity<List<CheatSheetResponseDTO>> getAllCheatSheets() {
        List<CheatSheetResponseDTO> responseDTOs = cheatSheetService.getAllCheatSheetsWithRelations();
        return ResponseEntity.ok(responseDTOs);
    }

    // Get CheatSheet by ID with relations
    @GetMapping("/{id}")
    public ResponseEntity<CheatSheetResponseDTO> getCheatSheetById(@PathVariable Integer id) {
        CheatSheetResponseDTO responseDTO = cheatSheetService.getCheatSheetByIdWithRelations(id);
        return ResponseEntity.ok(responseDTO);
    }

    // Delete Section by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheatSheet(@PathVariable Integer id) {
        cheatSheetService.deleteCheatSheet(id);
        return ResponseEntity.noContent().build();
    }
}

