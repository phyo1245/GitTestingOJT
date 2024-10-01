package com.c_s_boot.service;

import com.c_s_boot.DTO.subsection.SubSectionCreateDTO;
import com.c_s_boot.DTO.subsection.SubSectionResponseDTO;
import com.c_s_boot.DTO.subsection.SubSectionUpdateDTO;
import com.c_s_boot.mapper.SubSectionMapper;
import com.c_s_boot.models.SubSection;
import com.c_s_boot.repository.SubSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubSectionService {

    @Autowired
    private SubSectionRepository subSectionRepository;

    private final SubSectionMapper subSectionMapper = SubSectionMapper.INSTANCE;

    // Create new SubSection
    public SubSectionResponseDTO createSubSection(SubSectionCreateDTO createDTO) {
        SubSection subSection = subSectionMapper.toEntity(createDTO);
        SubSection savedSubSection = subSectionRepository.save(subSection);
        return subSectionMapper.toResponseDTO(savedSubSection);
    }

    // Update existing SubSection
    public SubSectionResponseDTO updateSubSection(Integer id, SubSectionUpdateDTO updateDTO) {
        SubSection existingSubSection = subSectionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SubSection not found"));
        SubSection updatedSubSection = subSectionMapper.toEntity(updateDTO);
        updatedSubSection.setId(existingSubSection.getId());
        updatedSubSection = subSectionRepository.save(updatedSubSection);
        return subSectionMapper.toResponseDTO(updatedSubSection);
    }

    // Get all SubSections
    public List<SubSectionResponseDTO> getAllSubSections() {
        List<SubSection> subSections = subSectionRepository.findAll();
        return subSections.stream()
                .map(subSectionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Get SubSection by ID
    public SubSectionResponseDTO getSubSectionById(Integer id) {
        SubSection subSection = subSectionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SubSection not found"));
        return subSectionMapper.toResponseDTO(subSection);
    }

    // Delete SubSection by ID
    public void deleteSubSection(Integer id) {
        SubSection subSection = subSectionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SubSection not found"));
        subSectionRepository.delete(subSection);
    }

    public List<SubSectionResponseDTO> findBySectionId(Integer sectionId) {
        List<SubSection> subsections = subSectionRepository.findBySectionId(sectionId);
        return subsections.stream()
                .map(subSectionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
