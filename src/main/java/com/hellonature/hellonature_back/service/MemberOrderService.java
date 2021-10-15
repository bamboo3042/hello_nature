package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.MemberOrder;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.MemberOrderApiRequest;
import com.hellonature.hellonature_back.model.network.response.MemberOrderApiResponse;
import com.hellonature.hellonature_back.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberOrderService extends BaseService<MemberOrderApiRequest, MemberOrderApiResponse, MemberOrder> {

    private final EntityManager em;

    private final MemberOrderRepository memberOrderRepository;
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final MemberPaymentRepository memberPaymentRepository;
    private final ProductReviewRepository productReviewRepository;

    @Override
    public Header<MemberOrderApiResponse> create(Header<MemberOrderApiRequest> request) {
        MemberOrderApiRequest memberOrderApiRequest = request.getData();

        MemberOrder memberOrder = MemberOrder.builder()
                .state(memberOrderApiRequest.getState())
                .dawnFlag(memberOrderApiRequest.getDawnFlag())
                .alarm(memberOrderApiRequest.getAlarm())
                .zipcode(memberOrderApiRequest.getZipcode())
                .address1(memberOrderApiRequest.getAddress1())
                .address2(memberOrderApiRequest.getAddress2())
                .requestType(memberOrderApiRequest.getRequestType())
                .requestMemo1(memberOrderApiRequest.getRequestMemo1())
                .requestMemo2(memberOrderApiRequest.getRequestMemo2())

                .member(memberRepository.findById(memberOrderApiRequest.getIdx()).get())
                .coupon(couponRepository.findById(memberOrderApiRequest.getCpIdx()).get())
                .payment(memberPaymentRepository.findById(memberOrderApiRequest.getMpayIdx()).get())
                .build();
        MemberOrder newMemberOrder = memberOrderRepository.save(memberOrder);
        return Header.OK();
    }

    @Override
    public Header<MemberOrderApiResponse> read(Long id) {
        return memberOrderRepository.findById(id)
                .map(memberOrder -> response(memberOrder))
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

        }).map(memberOrder -> memberOrderRepository.save(memberOrder))
                .map(memberOrder -> response(memberOrder))
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
        MemberOrderApiResponse memberOrderApiResponse = MemberOrderApiResponse.builder()
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
                .cpIdx(memberOrder.getCoupon().getIdx())
                .mpayIdx(memberOrder.getPayment().getIdx())
                .build();
        return memberOrderApiResponse;

    }
}
