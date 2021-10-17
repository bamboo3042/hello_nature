package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.Coupon;
import com.hellonature.hellonature_back.model.entity.CouponType;
import com.hellonature.hellonature_back.model.entity.Member;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.CouponApiRequest;
import com.hellonature.hellonature_back.model.network.response.CouponApiResponse;
import com.hellonature.hellonature_back.repository.CouponRepository;
import com.hellonature.hellonature_back.repository.CouponTypeRepository;
import com.hellonature.hellonature_back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService extends BaseService<CouponApiRequest, CouponApiResponse, Coupon>{

    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;
    private final CouponTypeRepository couponTypeRepository;

    @Override
    public Header<CouponApiResponse> create(Header<CouponApiRequest> request) {
        CouponApiRequest couponApiRequest = request.getData();
        Optional<CouponType> optionalCouponType = couponTypeRepository.findById(couponApiRequest.getCtIdx());
        Integer count = optionalCouponType.get().getCount();

        if(count == 0) return Header.ERROR("쿠폰 소진");
        else if(count > 0) optionalCouponType.get().minusCount();

        Coupon coupon = Coupon.builder()
                .member(memberRepository.findById(couponApiRequest.getMemIdx()).get())
                .couponType(optionalCouponType.get())
                .dateStart(couponApiRequest.getDateStart())
                .dateEnd(couponApiRequest.getDateEnd())
                .build();
        couponRepository.save(coupon);
        return Header.OK();
    }

    @Override
    public Header<CouponApiResponse> read(Long id) {
        return couponRepository.findById(id)
                .map(coupon -> response(coupon))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("쿠폰 없음")
                );
    }

    @Override
    public Header<CouponApiResponse> update(Header<CouponApiRequest> request) {
        CouponApiRequest couponApiRequest = request.getData();
        Optional<Coupon> optional = couponRepository.findById(couponApiRequest.getIdx());
        
        return optional.map(coupon -> {
            coupon.setUsedFlag(couponApiRequest.getUsedFlag());
            coupon.setDateStart(couponApiRequest.getDateStart());
            coupon.setDateEnd(couponApiRequest.getDateEnd());
            
            return coupon;
        }).map(couponRepository::save)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("수정 실패"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Coupon> optional = couponRepository.findById(id);
        return optional.map(coupon -> {
            couponRepository.delete(coupon);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("삭제 실패"));
    }

    private CouponApiResponse response(Coupon coupon){
        return CouponApiResponse.builder()
                .idx(coupon.getIdx())
                .memIdx(coupon.getMember().getIdx())
                .ctIdx(coupon.getCouponType().getIdx())
                .usedFlag(coupon.getUsedFlag())
                .dateStart(coupon.getDateStart())
                .dateEnd(coupon.getDateEnd())
                .build();
    }

    public Header<Long> count(Long memIdx){
        Member member = memberRepository.findById(memIdx).get();
        return Header.OK(couponRepository.countAllByMemberAndUsedFlag(member, Flag.FALSE));
    }

    public Header<List<CouponApiResponse>> list(Long memIdx, Flag usedFlag){
        Member member = memberRepository.findById(memIdx).get();
        List<Coupon> list = couponRepository.findAllByMemberAndUsedFlagOrderByIdxDesc(member, usedFlag);
        List<CouponApiResponse> newList = new ArrayList<>();
        for (Coupon coupon:
             list) {
            newList.add(response(coupon));
        }
        return Header.OK(newList);
    }
}
