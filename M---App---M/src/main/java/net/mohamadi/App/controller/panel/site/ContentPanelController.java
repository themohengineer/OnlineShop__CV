package net.mohamadi.App.controller.panel.site;


import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.controller.base.CreatController;
import net.mohamadi.App.controller.base.ReadController;
import net.mohamadi.App.controller.base.UpdateController;
import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.site.ContentService;
import net.mohamadi.dto.site.ContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/content")
public class ContentPanelController implements
        CreatController<ContentDto>,
        UpdateController<ContentDto>,
        ReadController<ContentDto> {


    private final ContentService service;

    @Autowired
    public ContentPanelController(ContentService service) {
        this.service = service;
    }


    @Override
    @CheckPermission("add_content")
    public APIResponse<ContentDto> add(ContentDto dto) throws ValidationException {
        return APIResponse.<ContentDto>builder()
                .status(HttpStatus.OK)
                .data(service.create(dto))
                .message("")
                .build();

    }


    @Override
    @CheckPermission("list_content")
    public APIPanelResponse<List<ContentDto>> getAll(Integer page, Integer size) {
        Page<ContentDto> data = service.readAll(page, size);
        return APIPanelResponse.<List<ContentDto>>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(data.getContent())
                .totalCount(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    @Override
    @CheckPermission("edit_content")
    public APIResponse<ContentDto> edit(ContentDto dto) throws ValidationException, NotFoundExceptionss {

        return APIResponse.<ContentDto>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(service.update(dto))
                .build();
    }
}
