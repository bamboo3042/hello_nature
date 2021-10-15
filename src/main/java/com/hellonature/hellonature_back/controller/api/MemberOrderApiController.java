package com.hellonature.hellonature_back.controller.api;

import com.hellonature.hellonature_back.controller.CrudController;
import com.hellonature.hellonature_back.model.entity.MemberOrder;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.MemberOrderApiRequest;
import com.hellonature.hellonature_back.model.network.response.MemberOrderApiResponse;
import com.hellonature.hellonature_back.service.MemberOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memberOrder")
@RequiredArgsConstructor
public class MemberOrderApiController extends CrudController<MemberOrderApiRequest, MemberOrderApiResponse, MemberOrder> {
    private final MemberOrderService memberOrderService;

    @Override
    @PostMapping("/create")
    public Header<MemberOrderApiResponse> create(Header<MemberOrderApiRequest> request) {
        return memberOrderService.create(request);
    }

    @Override
    @GetMapping("/read/{idx}")
    public Header<MemberOrderApiResponse> read(Long idx) {
        return memberOrderService.read(idx);
    }

    @Override
    @PutMapping("/update")
    public Header<MemberOrderApiResponse> update(Header<MemberOrderApiRequest> request) {
        return memberOrderService.update(request);
    }

    @Override
    @DeleteMapping("/delete/{idx}")
    public Header<MemberOrderApiResponse> delete(Long idx) {
        return memberOrderService.delete(idx);
    }
}
