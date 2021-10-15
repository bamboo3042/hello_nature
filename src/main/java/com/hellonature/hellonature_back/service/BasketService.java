package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.Basket;
import com.hellonature.hellonature_back.model.entity.Faq;
import com.hellonature.hellonature_back.model.entity.Popupstore;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.BasketApiRequest;
import com.hellonature.hellonature_back.model.network.request.FaqApiRequest;
import com.hellonature.hellonature_back.model.network.request.PopupstoreApiRequest;
import com.hellonature.hellonature_back.model.network.response.BasketApiResponse;
import com.hellonature.hellonature_back.repository.BasketRepository;
import com.hellonature.hellonature_back.repository.FaqRepository;
import com.hellonature.hellonature_back.repository.MemberRepository;
import com.hellonature.hellonature_back.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService extends BaseService<BasketApiRequest, BasketApiResponse, Basket> {

    private final EntityManager em;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    public Header<BasketApiResponse> create(Header<BasketApiRequest> request) {
        BasketApiRequest basketApiRequest = request.getData();

        Basket basket = Basket.builder()
                .member(memberRepository.findById(basketApiRequest.getIdx()).get())
                .product(productRepository.findById(basketApiRequest.getIdx()).get())
                .proCount(basketApiRequest.getProCount())
                .build();
        Basket newBasket = basketRepository.save(basket);
        return Header.OK();
    }

    @Override
    public Header<BasketApiResponse> read(Long id) {
        return basketRepository.findById(id)
                .map(basket -> response(basket))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("No data"));
    }

    @Override
    public Header<BasketApiResponse> update(Header<BasketApiRequest> request) {
        BasketApiRequest basketApiRequest = request.getData();
        Optional<Basket> optional = basketRepository.findById(basketApiRequest.getIdx());
        return optional.map(basket -> {
                    basket.setMember(memberRepository.findById(basketApiRequest.getIdx()).get());
                    basket.setProduct(productRepository.findById(basketApiRequest.getIdx()).get());
                    basket.setProCount(basketApiRequest.getProCount());

                    return basket;
                }).map(basket -> basketRepository.save(basket))
                .map(basket -> response(basket))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("No data"));
    }


    @Override
    public Header delete(Long id) {
        Optional<Basket> optional = basketRepository.findById(id);
        return optional.map(basket -> {
            basketRepository.delete(basket);
            return Header.OK();
        }).orElseGet(()-> Header.ERROR("No data"));
    }


    private BasketApiResponse response(Basket basket){
        return BasketApiResponse.builder()
                .memIdx(basket.getMember().getIdx())
                .proIdx(basket.getProduct().getIdx())
                .proCount(basket.getProCount())
                .build();
    }
}
