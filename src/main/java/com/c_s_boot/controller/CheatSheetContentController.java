package com.c_s_boot.controller;

import com.c_s_boot.DTO.CheatSheetContentDTO;
import com.c_s_boot.service.CheatSheetContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cheatSheetContents")
@CrossOrigin
public class CheatSheetContentController {

    private final CheatSheetContentService cheatSheetContentService;

    public CheatSheetContentController(CheatSheetContentService cheatSheetContentService) {
        this.cheatSheetContentService = cheatSheetContentService;
    }

    @GetMapping
    public List<CheatSheetContentDTO> getAllContents() {
        return cheatSheetContentService.getAllContents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheatSheetContentDTO> getContentById(@PathVariable Integer id) {
        CheatSheetContentDTO contentDTO = cheatSheetContentService.getContentById(id);
        return contentDTO != null ? ResponseEntity.ok(contentDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public CheatSheetContentDTO createContent(@RequestBody CheatSheetContentDTO cheatSheetContentDTO) {
        return cheatSheetContentService.createCheatSheetContent(cheatSheetContentDTO);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<CheatSheetContentDTO> updateContent(
//            @PathVariable Integer id,
//            @RequestBody CheatSheetContentDTO cheatSheetContentDTO) {
//        CheatSheetContentDTO updatedContent = cheatSheetContentService.updateContent(id, cheatSheetContentDTO);
//        return updatedContent != null ? ResponseEntity.ok(updatedContent) : ResponseEntity.notFound().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Integer id) {
        cheatSheetContentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}
