package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.Like;
import com.hellonature.hellonature_back.model.entity.Magazine;
import com.hellonature.hellonature_back.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByMemberAndProductIsNotNull(Member member);
    List<Like> findAllByMemberAndMagazineIsNotNull(Member member);
    Optional<Like> findByMember(Member member);
    Long countByMagazine(Magazine magazine);
    Optional<Like> findByMagazine(Magazine magazine);
}
