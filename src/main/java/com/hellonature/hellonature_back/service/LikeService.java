package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.Like;
import com.hellonature.hellonature_back.model.entity.Magazine;
import com.hellonature.hellonature_back.model.entity.Member;
import com.hellonature.hellonature_back.model.entity.Product;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.LikeApiRequest;
import com.hellonature.hellonature_back.model.network.response.LikeApiResponse;
import com.hellonature.hellonature_back.model.network.response.MagazineApiResponse;
import com.hellonature.hellonature_back.model.network.response.ProductApiResponse;
import com.hellonature.hellonature_back.repository.LikeRepository;
import com.hellonature.hellonature_back.repository.MagazineRepository;
import com.hellonature.hellonature_back.repository.MemberRepository;
import com.hellonature.hellonature_back.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService extends BaseService<LikeApiRequest, LikeApiResponse, Like> {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final MagazineRepository magazineRepository;

    @Override
    public Header<LikeApiResponse> create(Header<LikeApiRequest> request) {
        LikeApiRequest likeApiRequest = request.getData();

        Optional<Member> optional = memberRepository.findById(likeApiRequest.getMemIdx());
        Member member = optional.get();

        Like like = Like.builder()
                .member(memberRepository.findById(likeApiRequest.getMemIdx()).get())
                .build();

        if(likeApiRequest.getProIdx() != null){
            Optional<Product> optionalProduct = productRepository.findById(likeApiRequest.getProIdx());
            Product product = optionalProduct.get();
            product.plusLike();
            like.setProduct(product);
        }
        else{
            Optional<Magazine> optionalMagazine = magazineRepository.findById(likeApiRequest.getMgIdx());
            Magazine magazine = optionalMagazine.get();
            magazine.plusLike();
            like.setMagazine(magazine);
        }

        likeRepository.save(like);

        return Header.OK();
    }

    @Override
    public Header<LikeApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<LikeApiResponse> update(Header<LikeApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        Optional<Like> optional = likeRepository.findById(id);

        return optional.map(like -> {
            likeRepository.delete(like);

            if (like.getProduct() != null){
                Product product = like.getProduct();
                product.minusLike();
            }
            else{
                Magazine magazine = like.getMagazine();
                magazine.minusLike();
            }

            return Header.OK();
        }).orElseGet( () -> Header.ERROR("삭제 실패"));
    }

    public Header<List<LikeApiResponse>> productList(Long memIdx){
        Optional<Member> optional = memberRepository.findById(memIdx);
        List<Like> list = likeRepository.findAllByMemberAndProductIsNotNull(optional.get());

        List<LikeApiResponse> productList = new ArrayList<>();
        for (Like like: list) {
            productList.add(productResponse(like.getProduct()));
        }

        return Header.OK(productList);
    }

    public Header<List<LikeApiResponse>> magazineList(Long memIdx){
        Optional<Member> optional = memberRepository.findById(memIdx);
        List<Like> list = likeRepository.findAllByMemberAndMagazineIsNotNull(optional.get());

        List<LikeApiResponse> magazineList = new ArrayList<>();
        for (Like like: list) {
            magazineList.add(magazineResponse(like.getMagazine()));
        }

        return Header.OK(magazineList);
    }

    private LikeApiResponse productResponse(Product product){
        return LikeApiResponse.builder()
                .proIdx(product.getIdx())
                .proName(product.getName())
                .proImg(product.getImg1())
                .proWeightSize(product.getSizeWeight())
                .proLikeCount(product.getLike())
                .build();
    }

    private LikeApiResponse magazineResponse(Magazine magazine){
        return LikeApiResponse.builder()
                .mgIdx(magazine.getIdx())
                .mgTitle(magazine.getTitle())
                .mgImg(magazine.getImg())
                .mgLikeCount(magazine.getLike())
                .build();
    }
}
