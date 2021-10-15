package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.Basket;
import com.hellonature.hellonature_back.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findAllByMember(Member member);
}
