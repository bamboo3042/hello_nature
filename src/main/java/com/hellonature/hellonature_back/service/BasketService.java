package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.*;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.BasketApiRequest;
import com.hellonature.hellonature_back.model.network.request.FaqApiRequest;
import com.hellonature.hellonature_back.model.network.request.PopupstoreApiRequest;
import com.hellonature.hellonature_back.model.network.response.*;
import com.hellonature.hellonature_back.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService extends BaseService<BasketApiRequest, BasketApiResponse, Basket> {

    private final EntityManager em;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    @Override
    public Header<BasketApiResponse> create(Header<BasketApiRequest> request) {
        BasketApiRequest basketApiRequest = request.getData();

        Basket basket = Basket.builder()
                .member(memberRepository.findById(basketApiRequest.getMemIdx()).get())
                .product(productRepository.findById(basketApiRequest.getProIdx()).get())
                .proCount(basketApiRequest.getProCount())
                .build();
        Basket newBasket = basketRepository.save(basket);
        return Header.OK();
    }

    @Override
    public Header<BasketApiResponse> read(Long id) {
        return basketRepository.findById(id)
                .map(this::response)
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
                }).map(basketRepository::save)
                .map(this::response)
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

    public Header<BasketResponse> memberList(Long memIdx, Long proIdx){
        Optional<Member> optionalMember = memberRepository.findById(memIdx);
        if (optionalMember.isEmpty()) return Header.ERROR("회원 정보가 잘못되었습니다");

        Member member = optionalMember.get();

        Optional<Address> optionalAddress = addressRepository.findByMemberAndBaseFlag(member, Flag.TRUE);

        if (optionalAddress.isEmpty()) return Header.ERROR("기본 주소지가 잘못되었습니다");

        List<BasketProductResponse> basketProductResponses = new ArrayList<>();

        if (proIdx == null) basketRepository.findAllByMember(member).stream().map(basket -> basketProductResponses.add(basketProductResponse(basket.getProduct(), basket.getProCount())));
        else{
            Optional<Product> optionalProduct = productRepository.findById(proIdx);
            if (optionalProduct.isEmpty()) return Header.ERROR("상품 정보가 잘못되었습니다");
            basketProductResponses.add(basketProductResponse(optionalProduct.get(), 1));
        }

        BasketResponse basketResponse = BasketResponse.builder()
                .address(basketAddressResponse(optionalAddress.get()))
                .product(basketProductResponses)
                .build();

        return Header.OK(basketResponse);
    }

    public Header<BasketResponse> nonMemberList(List<Long> proList){
        List<Product> products = productRepository.findAllByIdxIn(proList);
        List<BasketProductResponse> basketProductResponses = new ArrayList<>();
        for (Product product: products){
            basketProductResponses.add(basketProductResponse(product, 1));
        }

        BasketResponse basketResponse = BasketResponse.builder()
                .product(basketProductResponses)
                .build();

        return Header.OK(basketResponse);
    }

    private BasketAddressResponse basketAddressResponse(Address address){
        return BasketAddressResponse.builder()
                .idx(address.getIdx())
                .addrName(address.getAddrName())
                .zipcode(address.getZipcode())
                .addr1(address.getAddr1())
                .addr2(address.getAddr2())
                .baseFlag(address.getBaseFlag())
                .dawnFlag(address.getDawnFlag())
                .greenFlag(address.getBaseFlag())
                .build();
    }

    private BasketProductResponse basketProductResponse(Product product, Integer count){
        return BasketProductResponse.builder()
                .idx(product.getIdx())
                .name(product.getName())
                .img(product.getImg1())
                .netPrice(product.getNetPrice())
                .salePrice(product.getSalePrice())
                .price(product.getPrice())
                .count(count)
                .build();
    }
}
