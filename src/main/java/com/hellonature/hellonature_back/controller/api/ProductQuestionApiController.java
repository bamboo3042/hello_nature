package com.hellonature.hellonature_back.controller.api;


import com.hellonature.hellonature_back.controller.CrudController;
import com.hellonature.hellonature_back.model.entity.ProductQuestion;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.ProductQuestionApiRequest;
import com.hellonature.hellonature_back.model.network.response.ProductQuestionApiResponse;
import com.hellonature.hellonature_back.model.network.response.ProductQuestionListResponse;
import com.hellonature.hellonature_back.service.ProductQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productQuestion")
@RequiredArgsConstructor
public class ProductQuestionApiController extends CrudController<ProductQuestionApiRequest, ProductQuestionApiResponse, ProductQuestion> {
    private final ProductQuestionService productQuestionService;

    @Override
    @PostMapping("/create")
    public Header<ProductQuestionApiResponse> create(Header<ProductQuestionApiRequest> request) {
        return productQuestionService.create(request);
    }

    @Override
    @GetMapping("/read/{idx}")
    public Header<ProductQuestionApiResponse> read(Long idx) {
        return productQuestionService.read(idx);
    }

    @Override
    @PutMapping("/update")
    public Header<ProductQuestionApiResponse> update(Header<ProductQuestionApiRequest> request) {
        return productQuestionService.update(request);
    }

    @Override
    @DeleteMapping("/delete/{idx}")
    public Header delete(Long idx) {
        return productQuestionService.delete(idx);
    }

    @GetMapping("/list")
    public Header<List<ProductQuestionListResponse>> list(@RequestParam(name="typeFlag", required = false) Flag typeFlag,
                                                          @RequestParam(name="title", required = false) String title,
                                                          @RequestParam(name="dateStart", required = false) String dateStart,
                                                          @RequestParam(name="dateEnd", required = false) String dateEnd,
                                                          @RequestParam(name="startPage", defaultValue = "0") Integer startPage){
        return productQuestionService.list(typeFlag, title, dateStart, dateEnd, startPage);
    }

    @GetMapping("/detail/list")
    public Header<List<ProductQuestionApiResponse>> productDetailList(@RequestParam(name = "proIdx") Long proIdx,
                                                                      @RequestParam(name = "page", defaultValue = "0") Integer page){
        return productQuestionService.productDetailList(proIdx, page);
    }
}
