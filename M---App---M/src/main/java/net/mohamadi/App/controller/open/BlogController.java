package net.mohamadi.App.controller.open;


import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Service.site.BlogService;
import net.mohamadi.Service.site.ContentService;
import net.mohamadi.dto.site.BlogDto;
import net.mohamadi.dto.site.ContentDto;
import net.mohamadi.dto.site.SingleBlogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService service;


    @Autowired
    public BlogController(BlogService service) {
        this.service = service;
    }


    //    https://127.0.0.1/api/blog?page=1&size=1    @GetMapping("")
    public APIResponse<List<BlogDto>> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        return APIResponse.
                <List<BlogDto>>builder()
                .status(HttpStatus.OK)
                .data(service.readAll(page, size))
                .build();

    }

    //https://127.0.0.1/api/blog
    @GetMapping("{id}")
    public APIResponse<SingleBlogDto> getByKey(@PathVariable Long id) {

        try {
            return APIResponse.
                    <SingleBlogDto>builder()
                    .status(HttpStatus.OK)
                    .data(service.read(id))
                    .build();
        } catch (NotFoundExceptionss e) {
            return APIResponse.
                    <SingleBlogDto>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
        }

    }


}
