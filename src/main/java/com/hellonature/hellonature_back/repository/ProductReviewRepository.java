package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.Member;
import com.hellonature.hellonature_back.model.entity.Product;
import com.hellonature.hellonature_back.model.entity.ProductReview;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
        Optional<ProductReview> findById(Long id);
        List<ProductReview> findAllByProduct(Product product);
        List<ProductReview> findAllByMemberAndRegdateBetweenAndContentIsNullOrderByIdx(Member member, LocalDateTime dateStart, LocalDateTime dateEnd);
        List<ProductReview> findAllByMemberAndRegdateBetweenAndContentIsNotNullOrderByIdx(Member member, LocalDateTime dateStart, LocalDateTime dateEnd);
        Long countAllByAnsFlag(Flag flag);
        Long countAllByAnsFlagAndRegdateBetween(Flag flag, LocalDateTime start, LocalDateTime end);
        List<ProductReview> findTop4ByContentIsNotNullOrderByIdxDesc();
}