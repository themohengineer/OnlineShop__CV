package net.mohamadi.App.controller.open;


import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Data_Access.entity.site.Nav;
import net.mohamadi.Service.site.NavService;
import net.mohamadi.dto.site.NavDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nav")
public class NavController {

    private final NavService service;


    @Autowired
    public NavController(NavService service) {
        this.service = service;
    }


    //https://127.0.0.1/api/nav
    @GetMapping("")
    public APIResponse<List<NavDto>> getAll() {

        return APIResponse.
                <List<NavDto>>builder()
                .status(HttpStatus.OK)
                .data(service.readAll())
                .build();

    }


}
