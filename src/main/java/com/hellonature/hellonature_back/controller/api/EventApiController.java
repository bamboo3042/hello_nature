package com.hellonature.hellonature_back.controller.api;

import com.hellonature.hellonature_back.controller.CrudController;
import com.hellonature.hellonature_back.model.entity.Event;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.request.EventApiRequest;
import com.hellonature.hellonature_back.model.network.response.EventApiResponse;
import com.hellonature.hellonature_back.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventApiController extends CrudController<EventApiRequest, EventApiResponse, Event> {
    private final EventService eventService;

    @Override
    @PostMapping("/create")
    public Header<EventApiResponse> create(@RequestBody Header<EventApiRequest> request) {
        return eventService.create(request);
    }

    @Override
    @GetMapping("/read/{idx}")
    public Header<EventApiResponse> read(@PathVariable(name="idx")Long idx) {
        return eventService.read(idx);
    }

    @Override
    @PutMapping("/update")
    public Header<EventApiResponse> update(@RequestBody Header<EventApiRequest> request) {
        return eventService.update(request);
    }

    @Override
    @DeleteMapping("/delete/{idx}")
    public Header<EventApiResponse> delete(@PathVariable(name="idx") Long idx) {
        return eventService.delete(idx);
    }

    @GetMapping("/list")
    public Header<List<EventApiResponse>> list(@RequestParam(name="eventFlag", required = false) Flag typeFlag,
                                               @RequestParam(name="title", required = false) String title,
                                               @RequestParam(name="dateStart", required = false) String dateStart,
                                               @RequestParam(name="dateEnd", required = false) String dateEnd,
                                               @RequestParam(name = "ingFlag", required = false) Flag ingFlag,
                                               @RequestParam(name="startPage", defaultValue = "0") Integer page){
        return eventService.list(typeFlag, title, dateStart, dateEnd, ingFlag, page);
    }

    @GetMapping("/")
    public Header<List<EventApiResponse>> findAll(@PageableDefault(sort={"idx"}, direction= Sort.Direction.DESC) Pageable pageable) {
        return eventService.search(pageable);

    }
}
