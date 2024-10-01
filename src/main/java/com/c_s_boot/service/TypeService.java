package com.c_s_boot.service;

import com.c_s_boot.DTO.type.TypeCreateDTO;
import com.c_s_boot.DTO.type.TypeResponseDTO;
import com.c_s_boot.DTO.type.TypeUpdateDTO;
import com.c_s_boot.mapper.TypeMapper;
import com.c_s_boot.models.Type;
import com.c_s_boot.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    private final TypeMapper typeMapper = TypeMapper.INSTANCE;

    // Create new Section
    public TypeResponseDTO createType(TypeCreateDTO createDTO) {
        Type type = typeMapper.toEntity(createDTO);
        Type savedType = typeRepository.save(type);
        return typeMapper.toResponseDTO(savedType);
    }

    // Update existing Section
    public TypeResponseDTO updateType(Integer id, TypeUpdateDTO updateDTO) {
        Type existingType = typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type not found"));
        Type updatedType = typeMapper.toEntity(updateDTO);
        updatedType.setId(existingType.getId());
        updatedType = typeRepository.save(updatedType);
        return typeMapper.toResponseDTO(updatedType);
    }

    // Get all Sections
    public List<TypeResponseDTO> getAllTypes() {
        List<Type> types = typeRepository.findAll();
        return types.stream()
                .map(typeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Get Section by ID
    public TypeResponseDTO getTypeById(Integer id) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type not found"));
        return typeMapper.toResponseDTO(type);
    }

    // Delete Section by ID
    public void deleteType(Integer id) {
        Type type = typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Type not found"));
        typeRepository.delete(type);
    }
}
