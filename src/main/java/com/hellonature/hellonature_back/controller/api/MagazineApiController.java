package com.hellonature.hellonature_back.controller.api;

import com.hellonature.hellonature_back.controller.CrudController;
import com.hellonature.hellonature_back.model.entity.Magazine;
import com.hellonature.hellonature_back.model.enumclass.MagazineType;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.MagazineApiRequest;
import com.hellonature.hellonature_back.model.network.response.MagazineApiResponse;
import com.hellonature.hellonature_back.model.network.response.MagazineDetailResponse;
import com.hellonature.hellonature_back.service.MagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/magazine")
@RequiredArgsConstructor
public class MagazineApiController extends CrudController<MagazineApiRequest, MagazineApiResponse, Magazine> {

    private final MagazineService magazineService;

    @RequestMapping(value ="/create", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public Header<MagazineApiResponse> create(@RequestPart(value = "key") Header<MagazineApiRequest> request,
                                              @RequestPart(value = "files") List<MultipartFile> fileList) throws Exception {

        return magazineService.create(request, fileList);
    }

    @Override
    @GetMapping("/read/{idx}")
    public Header<MagazineApiResponse> read(@PathVariable(name="idx") Long idx) {
        return magazineService.read(idx);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = { "multipart/form-data"})
    public Header<MagazineApiResponse> update(@RequestBody Header<MagazineApiRequest> request,
                                              @RequestPart(value = "files") List<MultipartFile> fileList) throws Exception {
        return magazineService.update(request, fileList);
    }

    @Override
    @DeleteMapping("/delete/{idx}")
    public Header delete(@PathVariable(name="idx") Long idx) {
        return magazineService.delete(idx);
    }

//    127.0.0.1:8080/api/magazine/list?dateStart=2001-09-15&dateEnd=2021-09-10
    @GetMapping("/list")
    public Header<List<MagazineApiResponse>> list(@RequestParam(name="cateIdx", required = false) Long cateIdx,
                                                  @RequestParam(name="title", required = false) String title,
                                                  @RequestParam(name="dateStart", required = false) String dateStart,
                                                  @RequestParam(name="dateEnd", required = false) String dateEnd,
                                                  @RequestParam(name="startPage", defaultValue = "0") Integer startPage){
        return magazineService.list(cateIdx, title, dateStart, dateEnd, startPage);
    }

    @GetMapping("detail/{idx}")
    public Header<MagazineDetailResponse> detail(@PathVariable(name = "idx") Long idx){
        return magazineService.detail(idx);
    }

    @GetMapping("user/list")
    public Header<List<MagazineApiResponse>> userList(@RequestParam(name = "type") MagazineType type,
                                                      @RequestParam(name = "page", defaultValue = "0") Integer page){
        return magazineService.userList(type, page);
    }

}
