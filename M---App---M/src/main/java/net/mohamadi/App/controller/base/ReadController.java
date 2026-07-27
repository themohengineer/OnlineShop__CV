package net.mohamadi.App.controller.base;

import net.mohamadi.App.model.APIPanelResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReadController<Dto> {


    @GetMapping("")
    APIPanelResponse<List<Dto>> getAll(@RequestParam(required = false) Integer page,
                                       @RequestParam(required = false) Integer size);


}
