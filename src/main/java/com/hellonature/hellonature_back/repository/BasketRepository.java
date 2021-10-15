package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
