package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.NonMemberPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NonMemberPaymentRepository extends JpaRepository<NonMemberPayment, Long> {
    void deleteAllByIdxIn(List<Long> idx);
}
