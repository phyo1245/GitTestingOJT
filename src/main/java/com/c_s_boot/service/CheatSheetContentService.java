package com.c_s_boot.service;

import com.c_s_boot.DTO.CheatSheetContentDTO;
import com.c_s_boot.mapper.CheatSheetContentMapper;
import com.c_s_boot.models.CheatSheet;
import com.c_s_boot.models.CheatSheetContent;
import com.c_s_boot.repository.CheatSheetContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheatSheetContentService {

    private final CheatSheetContentRepository cheatSheetContentRepository;
    private final CheatSheetContentMapper cheatSheetContentMapper;

    public CheatSheetContentService(CheatSheetContentRepository cheatSheetContentRepository,
                                    CheatSheetContentMapper cheatSheetContentMapper) {
        this.cheatSheetContentRepository = cheatSheetContentRepository;
        this.cheatSheetContentMapper = cheatSheetContentMapper;
    }

    public List<CheatSheetContentDTO> getAllContents() {
        return cheatSheetContentRepository.findAll().stream()
                .map(cheatSheetContentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CheatSheetContentDTO getContentById(Integer id) {
        return cheatSheetContentRepository.findById(id)
                .map(cheatSheetContentMapper::toDTO)
                .orElse(null);
    }

    public CheatSheetContentDTO createCheatSheetContent(CheatSheetContentDTO dto) {
        CheatSheetContent cheatSheetContent = cheatSheetContentMapper.toEntity(dto);
        // Manually set the CheatSheet object using cheatSheetId
        CheatSheet cheatSheet = new CheatSheet();
        cheatSheet.setId(dto.getCheatSheetId());
        cheatSheetContent.setCheatSheet(cheatSheet);

        // Save to the repository
        cheatSheetContent = cheatSheetContentRepository.save(cheatSheetContent);
        return cheatSheetContentMapper.toDTO(cheatSheetContent);
    }

//    public CheatSheetContentDTO updateContent(Integer id, CheatSheetContentDTO cheatSheetContentDTO) {
//        CheatSheetContent existingContent = cheatSheetContentRepository.findById(id).orElse(null);
//        if (existingContent != null) {
//            existingContent.setContent(cheatSheetContentDTO.getContent());
//            existingContent.setCheatSheet(cheatSheetContentMapper.toEntity(cheatSheetContentDTO).getCheatSheet());
//            cheatSheetContentRepository.save(existingContent);
//            return cheatSheetContentMapper.toDTO(existingContent);
//        }
//        return null;
//    }

        public void deleteContent(Integer id){
            cheatSheetContentRepository.deleteById(id);
        }
    }


