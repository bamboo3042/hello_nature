package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    Optional<Magazine> findById(Long id);


}