package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.Member;
import com.hellonature.hellonature_back.model.entity.Product;
import com.hellonature.hellonature_back.model.entity.ProductReview;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
        Optional<ProductReview> findById(Long id);
        List<ProductReview> findAllByProduct(Product product);
        List<ProductReview> findAllByAnsFlagAndMemberOrderByIdxDesc(Flag flag, Member member);
}