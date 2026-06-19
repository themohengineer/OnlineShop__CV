package net.mohamadi.App.controller.open;


import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Service.site.NavService;
import net.mohamadi.Service.site.SliderService;
import net.mohamadi.dto.site.NavDto;
import net.mohamadi.dto.site.SliderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/slider")
public class SliderController {

    private final SliderService service;


    @Autowired
    public SliderController(SliderService service) {
        this.service = service;
    }



    //https://127.0.0.1/api/slider
    @GetMapping("")
    public APIResponse<List<SliderDto>> getAll() {

        return APIResponse.
                <List<SliderDto>>builder()
                .status(HttpStatus.OK)
                .data(service.readAll())
                .build();

    }


}
