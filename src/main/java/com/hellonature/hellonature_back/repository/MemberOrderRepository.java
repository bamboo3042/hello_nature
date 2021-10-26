package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.MemberOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface MemberOrderRepository extends JpaRepository<MemberOrder, Long> {
    Optional<MemberOrder>findById(Long id);
    Long countAllByStateAndRegdate(Integer state, LocalDate localDate);
    Long countAllByStateOrStateOrState(Integer state1, Integer state2, Integer state3);
    Long countAllByStateOrStateOrStateAndRegdate(Integer state1, Integer state2, Integer state3, LocalDate date);
    Long countAllByRegdateAndStateIsGreaterThanEqual(Date date, Integer integer);
    Long countAllByRegdateAndStateIsLessThanEqual(Date date, Integer integer);
    Long countAllByRegdate(LocalDate localDate);
}
