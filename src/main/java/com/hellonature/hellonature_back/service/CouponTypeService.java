package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.CouponType;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.CouponTypeApiRequest;
import com.hellonature.hellonature_back.model.network.response.CouponTypeApiResponse;
import com.hellonature.hellonature_back.repository.CouponTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponTypeService extends BaseService<CouponTypeApiRequest, CouponTypeApiResponse, CouponType>{

    private final CouponTypeRepository couponTypeRepository;

    @Override
    public Header<CouponTypeApiResponse> create(Header<CouponTypeApiRequest> request) {
        CouponTypeApiRequest couponTypeApiRequest = request.getData();

        CouponType couponType = CouponType.builder()
                .title(couponTypeApiRequest.getTitle())
                .count(couponTypeApiRequest.getCount())
                .discount(couponTypeApiRequest.getDiscount())
                .minPrice(couponTypeApiRequest.getMinPrice())
                .dateStart(couponTypeApiRequest.getDateStart())
                .dateEnd(couponTypeApiRequest.getDateEnd())
                .build();

        couponTypeRepository.save(couponType);

        return Header.OK();
    }

    @Override
    public Header<CouponTypeApiResponse> read(Long id) {
        return couponTypeRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("쿠폰 정보가 없습니다"));
    }

    @Override
    public Header<CouponTypeApiResponse> update(Header<CouponTypeApiRequest> request) {
        CouponTypeApiRequest couponTypeApiRequest = request.getData();
        return couponTypeRepository.findById(couponTypeApiRequest.getIdx())
                .map(couponType -> {
                    couponType.setTitle(couponTypeApiRequest.getTitle());
                    couponType.setCount(couponTypeApiRequest.getCount());
                    couponType.setDiscount(couponType.getDiscount());
                    couponType.setMinPrice(couponType.getMinPrice());
                    couponType.setDateStart(couponType.getDateStart());
                    couponType.setDateEnd(couponType.getDateEnd());

                    return couponType;
                })
                .map(couponTypeRepository::save)
                .map(this::response)
                .map(Header::OK).orElseGet(() -> Header.ERROR("쿠폰 수정이 실패했습니다"));
    }

    @Override
    public Header delete(Long id) {
        Optional<CouponType> optionalCouponType = couponTypeRepository.findById(id);
        return optionalCouponType.map(couponType -> {
            couponTypeRepository.delete(couponType);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("쿠폰 삭제를 실패했습니다"));
    }

    private CouponTypeApiResponse response(CouponType couponType){
        return CouponTypeApiResponse.builder()
                .idx(couponType.getIdx())
                .title(couponType.getTitle())
                .count(couponType.getCount())
                .discount(couponType.getDiscount())
                .minPrice(couponType.getMinPrice())
                .dateStart(couponType.getDateStart())
                .dateEnd(couponType.getDateEnd())
                .regdate(couponType.getRegdate())
                .build();
    }
}
