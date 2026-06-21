package net.mohamadi.App.controller.open;


import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Data_Access.entity.site.Content;
import net.mohamadi.Service.site.ContentService;
import net.mohamadi.Service.site.NavService;
import net.mohamadi.dto.site.ContentDto;
import net.mohamadi.dto.site.NavDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService service;


    @Autowired
    public ContentController(ContentService service) {
        this.service = service;
    }


    //https://127.0.0.1/api/content
    @GetMapping("")
    public APIResponse<List<ContentDto>> getAll() {

        return APIResponse.
                <List<ContentDto>>builder()
                .status(HttpStatus.OK)
                .data(service.readAll())
                .build();

    }

    //https://127.0.0.1/api/content
    @GetMapping("{key}")
    public APIResponse<ContentDto> getByKey(@PathVariable String key) {

        try {
            return APIResponse.
                    <ContentDto>builder()
                    .status(HttpStatus.OK)
                    .data(service.readByKey(key))
                    .build();
        } catch (NotFoundExceptionss e) {
            return APIResponse.
                    <ContentDto>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
        }

    }


}
