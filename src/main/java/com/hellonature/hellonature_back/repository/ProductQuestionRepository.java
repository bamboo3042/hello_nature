package com.hellonature.hellonature_back.repository;

import com.hellonature.hellonature_back.model.entity.Product;
import com.hellonature.hellonature_back.model.entity.ProductQuestion;
import com.hellonature.hellonature_back.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductQuestionRepository extends JpaRepository<ProductQuestion, Long> {
    Optional<ProductQuestion> findById(Long id);
    List<ProductQuestion> findAllByProduct(Product product);
}
