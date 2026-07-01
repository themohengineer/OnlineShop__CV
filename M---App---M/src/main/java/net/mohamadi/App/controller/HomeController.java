package net.mohamadi.App.controller;


import net.mohamadi.App.model.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {

    @GetMapping("verify")
    public APIResponse<String> verify(
            @RequestParam String Authority,
            @RequestParam String Status
    ) {

        return APIResponse.<String>builder()
                .status(HttpStatus.OK)
                .data(Authority)
                .message(Status)
                .build();


    }


}
