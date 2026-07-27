package net.mohamadi.App.controller.panel.site;


import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.controller.base.CRUDController;
import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.site.BlogService;
import net.mohamadi.dto.site.BlogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/blog")
public class BlogPanelController implements CRUDController<BlogDto> {


    private final BlogService service;

    @Autowired
    public BlogPanelController(BlogService service) {
        this.service = service;
    }


    @Override
    @CheckPermission("add_blog")
    public APIResponse<BlogDto> add(BlogDto dto) throws ValidationException {
        return APIResponse.<BlogDto>builder()
                .status(HttpStatus.OK)
                .data(service.create(dto))
                .message("")
                .build();

    }

    @Override
    @CheckPermission("delete_blog")
    public APIResponse<Boolean> delete(Long id) {
        return APIResponse.<Boolean>builder()
                .status(HttpStatus.OK)
                .data(service.delete(id))
                .message("")
                .build();
    }

    @Override
    @CheckPermission("list_blog")
    public APIPanelResponse<List<BlogDto>> getAll(Integer page, Integer size) {
        Page<BlogDto> data = service.readAll(page, size);
        return APIPanelResponse.<List<BlogDto>>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(data.getContent())
                .totalCount(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    @Override
    @CheckPermission("edit_blog")
    public APIResponse<BlogDto> edit(BlogDto dto) throws ValidationException, NotFoundExceptionss {

        return APIResponse.<BlogDto>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(service.update(dto))
                .build();
    }
}
