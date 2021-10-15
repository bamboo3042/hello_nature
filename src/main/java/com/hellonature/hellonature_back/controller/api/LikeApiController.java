package com.hellonature.hellonature_back.controller.api;

import com.hellonature.hellonature_back.controller.CrudController;
import com.hellonature.hellonature_back.model.entity.Like;
import com.hellonature.hellonature_back.model.entity.Magazine;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.LikeApiRequest;
import com.hellonature.hellonature_back.model.network.response.LikeApiResponse;
import com.hellonature.hellonature_back.model.network.response.MagazineApiResponse;
import com.hellonature.hellonature_back.model.network.response.ProductApiResponse;
import com.hellonature.hellonature_back.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberslikes")
@RequiredArgsConstructor
public class LikeApiController extends CrudController<LikeApiRequest, LikeApiResponse, Like> {

    private final LikeService likeService;

    @Override
    @PostMapping("/create")
    public Header<LikeApiResponse> create(@RequestBody Header<LikeApiRequest> request) {
        return likeService.create(request);
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
    @DeleteMapping("/delete/{idx}")
    public Header<LikeApiResponse> delete(@PathVariable(name = "idx") Long idx) {
        return likeService.delete(idx);
    }

    @GetMapping("/list")
    public Header<List<LikeApiResponse>> likeList(@RequestParam(name = "type") Integer type,
                                                  @RequestParam(name = "memIdx") Long memIdx){
        return type == 1 ? likeService.productList(memIdx) : likeService.magazineList(memIdx);
    }
}
