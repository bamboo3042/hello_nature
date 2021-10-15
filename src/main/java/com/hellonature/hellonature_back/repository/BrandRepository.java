package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
