package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndPassword(String email, String password);
    Optional<Member> findByEmail(String email);
    Long countAllByRegdateBetween(LocalDateTime start, LocalDateTime end);
}
