package com.c_s_boot.repository;

import com.c_s_boot.models.CheatSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheatSheetRepository extends JpaRepository<CheatSheet,Integer> {
    @Query("SELECT c FROM CheatSheet c " +
            "JOIN FETCH c.type " +
            "JOIN FETCH c.section " +
            "JOIN FETCH c.subsection " +
            "JOIN FETCH c.user " +
            "WHERE c.id = :id")
    CheatSheet findByIdWithAllRelations(Integer id);

    @Query("SELECT c FROM CheatSheet c " +
            "JOIN FETCH c.type " +
            "JOIN FETCH c.section " +
            "JOIN FETCH c.subsection " +
            "JOIN FETCH c.user")
    List<CheatSheet> findAllWithAllRelations();
}
