package net.mohamadi.App.controller.panel.site;


import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.controller.base.CRUDController;
import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.site.NavService;
import net.mohamadi.dto.site.NavDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/nav")
public class NavPanelController implements CRUDController<NavDto> {


    private final NavService service;

    @Autowired
    public NavPanelController(NavService service) {
        this.service = service;
    }


    @Override
    @CheckPermission("add_nav")
    public APIResponse<NavDto> add(NavDto dto) throws ValidationException {
        return APIResponse.<NavDto>builder()
                .status(HttpStatus.OK)
                .data(service.create(dto))
                .message("")
                .build();

    }

    @Override
    @CheckPermission("delete_nav")
    public APIResponse<Boolean> delete(Long id) {
        return APIResponse.<Boolean>builder()
                .status(HttpStatus.OK)
                .data(service.delete(id))
                .message("")
                .build();
    }

    @Override
    @CheckPermission("list_nav")
    public APIPanelResponse<List<NavDto>> getAll(Integer page, Integer size) {
        Page<NavDto> data = service.readAll(page, size);
        return APIPanelResponse.<List<NavDto>>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(data.getContent())
                .totalCount(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    @Override
    @CheckPermission("edit_nav")
    public APIResponse<NavDto> edit(NavDto dto) throws ValidationException, NotFoundExceptionss {

        return APIResponse.<NavDto>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(service.update(dto))
                .build();
    }
}
