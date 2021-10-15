package com.hellonature.hellonature_back.controller.api;

import com.hellonature.hellonature_back.controller.CrudController;
import com.hellonature.hellonature_back.model.entity.Basket;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.BasketApiRequest;
import com.hellonature.hellonature_back.model.network.response.BasketApiResponse;
import com.hellonature.hellonature_back.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
public class BasketApiController extends CrudController<BasketApiRequest, BasketApiResponse, Basket> {

    private final BasketService basketService;

    @Override
    @PostMapping("/create")
    public Header<BasketApiResponse> create(@RequestBody Header<BasketApiRequest> request) {
        return basketService.create(request);
    }

    @Override
    @GetMapping("/read/{idx}")
    public Header<BasketApiResponse> read(Long idx) {
        return basketService.read(idx);
    }

    @Override
    @PutMapping("/update")
    public Header<BasketApiResponse> update(@RequestBody Header<BasketApiRequest> request) {
        return basketService.update(request);
    }

    @Override
    @DeleteMapping("/delete/{idx}")
    public Header<BasketApiResponse> delete(Long idx) {
        return basketService.delete(idx);
    }
}
