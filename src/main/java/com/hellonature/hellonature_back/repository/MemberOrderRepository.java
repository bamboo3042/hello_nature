package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.MemberOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberOrderRepository extends JpaRepository<MemberOrder, Long> {
    Optional<MemberOrder>findById(Long id);
}
