package com.c_s_boot.service;

import com.c_s_boot.DTO.section.SectionCreateDTO;
import com.c_s_boot.DTO.section.SectionResponseDTO;
import com.c_s_boot.DTO.section.SectionUpdateDTO;
import com.c_s_boot.mapper.SectionMapper;
import com.c_s_boot.models.Section;
import com.c_s_boot.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    private final SectionMapper sectionMapper = SectionMapper.INSTANCE;

    // Create new Section
    public SectionResponseDTO createSection(SectionCreateDTO createDTO) {
        Section section = sectionMapper.toEntity(createDTO);
        Section savedSection = sectionRepository.save(section);
        return sectionMapper.toResponseDTO(savedSection);
    }

    // Update existing Section
    public SectionResponseDTO updateSection(Integer id, SectionUpdateDTO updateDTO) {
        Section existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));
        Section updatedSection = sectionMapper.toEntity(updateDTO);
        updatedSection.setId(existingSection.getId());
        updatedSection = sectionRepository.save(updatedSection);
        return sectionMapper.toResponseDTO(updatedSection);
    }

    // Get all Sections
    public List<SectionResponseDTO> getAllSections() {
        List<Section> sections = sectionRepository.findAll();
        return sections.stream()
                .map(sectionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Get Section by ID
    public SectionResponseDTO getSectionById(Integer id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));
        return sectionMapper.toResponseDTO(section);
    }

    // Delete Section by ID
    public void deleteSection(Integer id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Section not found"));
        sectionRepository.delete(section);
    }
}
