package com.hellonature.hellonature_back.controller.api;

import com.hellonature.hellonature_back.controller.CrudController;
import com.hellonature.hellonature_back.model.entity.CouponType;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.CouponTypeApiRequest;
import com.hellonature.hellonature_back.model.network.response.CouponTypeApiResponse;
import com.hellonature.hellonature_back.service.CouponTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memberCouponType")
@RequiredArgsConstructor
public class CouponTypeApiController extends CrudController<CouponTypeApiRequest, CouponTypeApiResponse, CouponType> {

    private final CouponTypeService couponTypeService;

    @Override
    @PostMapping("create")
    public Header<CouponTypeApiResponse> create(@RequestBody Header<CouponTypeApiRequest> request) {
        return couponTypeService.create(request);
    }

    @Override
    @GetMapping("read/{idx}")
    public Header<CouponTypeApiResponse> read(@PathVariable(name = "idx") Long idx) {
        return couponTypeService.read(idx);
    }

    @Override
    @PutMapping("update")
    public Header<CouponTypeApiResponse> update(@RequestBody Header<CouponTypeApiRequest> request) {
        return couponTypeService.update(request);
    }

    @Override
    @DeleteMapping("delete/{idx}")
    public Header delete(@PathVariable(name = "idx") Long idx) {
        return couponTypeService.delete(idx);
    }
}
