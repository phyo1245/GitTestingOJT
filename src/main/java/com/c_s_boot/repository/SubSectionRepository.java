package com.c_s_boot.repository;

import com.c_s_boot.models.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubSectionRepository extends JpaRepository<SubSection, Integer> {
    List<SubSection> findBySectionId(Integer sectionId);
}
