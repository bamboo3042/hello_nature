package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.*;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.MemberOrderApiRequest;
import com.hellonature.hellonature_back.model.network.response.AddressApiResponse;
import com.hellonature.hellonature_back.model.network.response.CouponApiResponse;
import com.hellonature.hellonature_back.model.network.response.MemberOrderApiResponse;
import com.hellonature.hellonature_back.model.network.response.MemberOrderLoadResponse;
import com.hellonature.hellonature_back.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberOrderService extends BaseService<MemberOrderApiRequest, MemberOrderApiResponse, MemberOrder> {

    private final MemberOrderRepository memberOrderRepository;
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final MemberPaymentRepository memberPaymentRepository;
    private final ProductRepository productRepository;
    private final MemberOrderProductRepository memberOrderProductRepository;
    private final HellocashRepository hellocashRepository;
    private final AddressRepository addressRepository;
    private final BasketRepository basketRepository;
    private final PurchaseRepository purchaseRepository;

    @Override
    @Transactional
    public Header<MemberOrderApiResponse> create(Header<MemberOrderApiRequest> request) {
        MemberOrderApiRequest memberOrderApiRequest = request.getData();
        System.out.println(memberOrderApiRequest);

        Optional<Member> optionalMember = memberRepository.findById(memberOrderApiRequest.getMemIdx());
        if (optionalMember.isEmpty()) return Header.ERROR("회원 정보가 잘못되었습니다");

        Member member = optionalMember.get();

        Optional<Coupon> optionalCoupon = Optional.empty();
        if (memberOrderApiRequest.getCpIdx() != null) {
            optionalCoupon = couponRepository.findById(memberOrderApiRequest.getCpIdx());
        }

        MemberOrder memberOrder = MemberOrder.builder()
                .state(1)
                .dawnFlag(memberOrderApiRequest.getDawnFlag())
                .recName(memberOrderApiRequest.getRecName())
                .recHp(memberOrderApiRequest.getRecHp())
                .alarm(memberOrderApiRequest.getAlarm())
                .zipcode(memberOrderApiRequest.getZipcode())
                .address1(memberOrderApiRequest.getAddress1())
                .address2(memberOrderApiRequest.getAddress2())
                .requestType(memberOrderApiRequest.getRequestType())
                .requestMemo1(memberOrderApiRequest.getRequestMemo1())
                .requestMemo2(memberOrderApiRequest.getRequestMemo2())
                .greenFlag(memberOrderApiRequest.getGreenFlag())
                .member(member)
                .coupon(optionalCoupon.isEmpty() ? null : optionalCoupon.get())
                .build();
        MemberOrder newMemberOrder = memberOrderRepository.save(memberOrder);

        List<Product> products = productRepository.findAllByIdxIn(memberOrderApiRequest.getProIdxList());

        for (int i = 0; i < products.size(); i++){
            MemberOrderProduct memberOrderProduct = MemberOrderProduct.builder()
                    .order(newMemberOrder)
                    .product(products.get(i))
                    .proPrice(products.get(i).getPrice())
                    .proCount(memberOrderApiRequest.getProCountList().get(i))
                    .build();
            memberOrderProductRepository.save(memberOrderProduct);

            Optional<Basket> optionalBasket = basketRepository.findByMemberAndProduct(member, products.get(i));
            if (optionalBasket.isEmpty()) continue;
            basketRepository.delete(optionalBasket.get());

            Purchase purchase = Purchase.builder()
                    .member(member)
                    .product(products.get(i))
                    .count(memberOrderApiRequest.getProCountList().get(i))
                    .build();

            purchaseRepository.save(purchase);
        }

        MemberPayment memberPayment = MemberPayment.builder()
                .order(newMemberOrder)
                .member(member)
                .price(memberOrderApiRequest.getPrice())
                .state(1)
                .paymentType(memberOrderApiRequest.getPaymentType())
                .num(memberOrderApiRequest.getCardNum())
                .build();

        memberPaymentRepository.save(memberPayment);

        Hellocash hellocash;

        if (memberOrderApiRequest.getHellocash() != 0){
            int point = memberOrderApiRequest.getHellocash();
            hellocash = Hellocash.builder()
                    .member(member)
                    .point(point)
                    .type(2)
                    .title("상품 구매 포인트 사용")
                    .build();

            hellocashRepository.save(hellocash);
            member.minusHelloCash(point);

            memberRepository.save(member);
        }

        int point = (int)(memberOrderApiRequest.getPrice() / 0.01);
        hellocash = Hellocash.builder()
                .member(member)
                .point(point)
                .type(1)
                .title("상품 구매 포인트 적립")
                .build();

        hellocashRepository.save(hellocash);
        member.plusHelloCash(point);
        memberRepository.save(member);

        return Header.OK();
    }

    @Override
    public Header<MemberOrderApiResponse> read(Long id) {
        return memberOrderRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("No data"));
    }

    @Override
    public Header<MemberOrderApiResponse> update(Header<MemberOrderApiRequest> request) {
        MemberOrderApiRequest memberOrderApiRequest = request.getData();
        Optional<MemberOrder> optional = memberOrderRepository.findById(memberOrderApiRequest.getIdx());

        return optional.map(memberOrder -> {
            memberOrder.setMember(memberRepository.findById(memberOrderApiRequest.getIdx()).get());
            memberOrder.setAlarm(memberOrderApiRequest.getAlarm());
            memberOrder.setState(memberOrderApiRequest.getState());
            memberOrder.setDawnFlag(memberOrderApiRequest.getDawnFlag());
            memberOrder.setAddress1(memberOrderApiRequest.getAddress1());
            memberOrder.setAddress2(memberOrderApiRequest.getAddress2());

            return memberOrder;

        }).map(memberOrderRepository::save)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("No data"));
    }

    @Override
    public Header delete(Long id) {
        Optional<MemberOrder> optional = memberOrderRepository.findById(id);
        return optional.map(memberOrder -> {
            memberOrderRepository.delete(memberOrder);
            return Header.OK();
        }).orElseGet(()-> Header.ERROR("No data"));

    }

    private MemberOrderApiResponse response(MemberOrder memberOrder){
        return MemberOrderApiResponse.builder()
                .idx(memberOrder.getIdx())
                .dawnFlag(memberOrder.getDawnFlag())
                .alarm(memberOrder.getAlarm())
                .zipcode(memberOrder.getZipcode())
                .address1(memberOrder.getAddress1())
                .address2(memberOrder.getAddress2())
                .requestType(memberOrder.getRequestType())
                .requestMemo1(memberOrder.getRequestMemo1())
                .requestMemo2(memberOrder.getRequestMemo2())
                .memIdx(memberOrder.getMember().getIdx())
                .cpIdx(memberOrder.getCoupon() != null ? memberOrder.getCoupon().getIdx() : null)
                .mpayIdx(memberOrder.getPayment().getIdx())
                .build();
    }

    public Header<MemberOrderLoadResponse> load(Long memIdx){
        Optional<Member> optionalMember = memberRepository.findById(memIdx);
        if (optionalMember.isEmpty()) return Header.ERROR("회원 정보가 잘못되었습니다");
        Member member = optionalMember.get();

        Optional<Address> optionalAddress = addressRepository.findByMemberAndBaseFlag(member, Flag.TRUE);
        if (optionalAddress.isEmpty()) return Header.ERROR("기본 배송지가 없습니다");
        Address address = optionalAddress.get();
        AddressApiResponse addressApiResponse = AddressApiResponse.builder()
                .idx(address.getIdx())
                .memIdx(address.getMember().getIdx())
                .memEmail(address.getMember().getEmail())
                .memName(address.getName())
                .memHp(address.getHp())
                .name(address.getName())
                .hp(address.getHp())
                .addrName(address.getAddrName())
                .zipcode(address.getZipcode())
                .addr1(address.getAddr1())
                .addr2(address.getAddr2())
                .dawnFlag(address.getDawnFlag())
                .greenFlag(address.getGrFlag())
                .baseFlag(address.getBaseFlag())
                .requestMemo1(address.getRequestMemo1())
                .requestMemo2(address.getRequestMemo2())
                .requestType(address.getRequestType())
                .regdate(address.getRegdate())
                .build();

        List<Coupon> coupons = couponRepository.findAllByMemberAndUsedFlagOrderByIdxDesc(member, Flag.FALSE);
        List<CouponApiResponse> couponApiResponses = new ArrayList<>();

        for (Coupon coupon: coupons){
            couponApiResponses.add(CouponApiResponse.builder()
                    .idx(coupon.getIdx())
                    .memIdx(coupon.getMember().getIdx())
                    .ctIdx(coupon.getCouponType().getIdx())
                    .usedFlag(coupon.getUsedFlag())
                    .dateStart(coupon.getDateStart())
                    .dateEnd(coupon.getDateEnd())
                    .build());
        }

        MemberOrderLoadResponse memberOrderLoadResponse = MemberOrderLoadResponse.builder()
                .address(addressApiResponse)
                .couponList(couponApiResponses)
                .hellocash(member.getHellocash())
                .build();

        return Header.OK(memberOrderLoadResponse);
    }
}
