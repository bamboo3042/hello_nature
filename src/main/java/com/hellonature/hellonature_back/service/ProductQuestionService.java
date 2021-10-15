package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.ProductQuestion;

import com.hellonature.hellonature_back.model.enumclass.Flag;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.Pagination;
import com.hellonature.hellonature_back.model.network.request.ProductQuestionApiRequest;
import com.hellonature.hellonature_back.model.network.response.ProductQuestionApiResponse;
import com.hellonature.hellonature_back.model.network.response.ProductQuestionListResponse;
import com.hellonature.hellonature_back.repository.MemberRepository;
import com.hellonature.hellonature_back.repository.ProductQuestionRepository;
import com.hellonature.hellonature_back.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductQuestionService extends BaseService<ProductQuestionApiRequest, ProductQuestionApiResponse, ProductQuestion> {
    private final EntityManager em;
    private final ProductQuestionRepository productQuestionRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    public Header<ProductQuestionApiResponse> create(Header<ProductQuestionApiRequest> request) {
        ProductQuestionApiRequest productQuestionApiRequest = request.getData();

        ProductQuestion productQuestion = ProductQuestion.builder()
                .member(memberRepository.findById(productQuestionApiRequest.getIdx()).get())
                .product(productRepository.findById(productQuestionApiRequest.getIdx()).get())
                .content(productQuestionApiRequest.getContent())
                .build();
        ProductQuestion newProductQuestion = productQuestionRepository.save(productQuestion);
        return Header.OK();
    }

    @Override
    public Header<ProductQuestionApiResponse> read(Long id) {
        return productQuestionRepository.findById(id)
                .map(productQuestion -> response(productQuestion))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("No data"));
    }

    @Override
    public Header<ProductQuestionApiResponse> update(Header<ProductQuestionApiRequest> request) {
        ProductQuestionApiRequest productQuestionApiRequest = request.getData();
        Optional<ProductQuestion> optional = productQuestionRepository.findById(productQuestionApiRequest.getIdx());
        return optional.map(productQuestion -> {
                    productQuestion.setMember(memberRepository.findById(productQuestionApiRequest.getIdx()).get());
                    productQuestion.setProduct(productRepository.findById(productQuestionApiRequest.getIdx()).get());
                    productQuestion.setContent(productQuestionApiRequest.getContent());
                    productQuestion.setAnsFlag(productQuestionApiRequest.getAnsFlag());
                    productQuestion.setAnsContent(productQuestionApiRequest.getAnsContent());
                    productQuestion.setAnsDate(productQuestionApiRequest.getAnsDate());

                    return productQuestion;
                }).map(productQuestion -> productQuestionRepository.save(productQuestion))
                .map(productQuestion -> response(productQuestion))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("No data"));
    }

    @Override
    public Header delete(Long id) {
        Optional<ProductQuestion> optional = productQuestionRepository.findById(id);

        return optional.map(productQuestion -> {
            productQuestionRepository.delete(productQuestion);
            return Header.OK();
        }).orElseGet(()-> Header.ERROR("No data"));
    }

    private ProductQuestionApiResponse response(ProductQuestion productQuestion){
        ProductQuestionApiResponse productQuestionApiResponse = ProductQuestionApiResponse.builder()
                .memIdx(productQuestion.getMember().getIdx())
                .proIdx(productQuestion.getProduct().getIdx())
                .content(productQuestion.getContent())
                .ansFlag(productQuestion.getAnsFlag())
                .ansContent(productQuestion.getAnsContent())
                .ansDate(productQuestion.getAnsDate())
                .build();

        return productQuestionApiResponse;
    }

    public Header<List<ProductQuestionListResponse>> list(Flag ansFlag, String title, String dateStart, String dateEnd, Integer startPage){
        String jpql = "select pq from ProductQuestion pq";
        boolean check = false;

        if(ansFlag!= null ||title != null || dateStart != null || dateEnd != null){
            jpql += " where";
            if(ansFlag != null){
                jpql += " ans_flag = :ansFlag";
                check = true;
            }

            if (title != null){
                if (check) jpql += " and";
                jpql += " title like :title";
                check = true;
            }
            if (dateStart != null){
                if(check) jpql += " and";
                jpql += " TO_char(regdate, 'YYYY-MM-DD') >= :dateStart";
                check = true;
            }
            if(dateEnd != null){
                if (check) jpql += " and";
                jpql += " TO_char(regdate, 'YYYY-MM-DD') <= :dateEnd";
            }
        }

        jpql += " order by idx desc";
        TypedQuery<ProductQuestion> query = em.createQuery(jpql, ProductQuestion.class);

        if (ansFlag != null) query = query.setParameter("ansFlag", ansFlag);
        if (title != null) query = query.setParameter("title", "%"+title+"%");
        if (dateStart != null) query = query.setParameter("dateStart", dateStart);
        if (dateEnd != null) query = query.setParameter("dateEnd", dateEnd);

        List<ProductQuestion> result = query.getResultList();

        int count = 10;

        Integer start = count * startPage;
        Integer end = Math.min(result.size(), start + count);

        List<ProductQuestionListResponse> list = new ArrayList<>();

        for (ProductQuestion productQuestion:
             result.subList(start, end)) {
            list.add(responseList(productQuestion));
        }


        Pagination pagination = new Pagination().builder()
                .totalPages(result.size() / count)
                .currentPage(startPage)
                .build();

        return Header.OK(list, pagination);
    }

    private ProductQuestionListResponse responseList(ProductQuestion productQuestion){
        ProductQuestionListResponse productQuestionListResponse = ProductQuestionListResponse.builder()
                .idx(productQuestion.getIdx())
                .regdate(productQuestion.getRegdate())
                .ansFlag(productQuestion.getAnsFlag())
                .content(productQuestion.getContent())
                .name(productQuestion.getMember().getName())
                .build();
        return productQuestionListResponse;
    }

    public Header<List<ProductQuestionApiResponse>> search(Pageable pageable){
        Page<ProductQuestion> productQuestion = productQuestionRepository.findAll(pageable);
        List<ProductQuestionApiResponse> productQuestionApiResponseList = productQuestion.stream()
                .map(productQuestions -> response(productQuestions))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(productQuestion.getTotalPages()-1)
                .totalElements(productQuestion.getTotalElements())
                .currentPage(productQuestion.getNumber())
                .currentElements(productQuestion.getNumberOfElements())
                .build();
        return Header.OK(productQuestionApiResponseList, pagination);
    }
}
