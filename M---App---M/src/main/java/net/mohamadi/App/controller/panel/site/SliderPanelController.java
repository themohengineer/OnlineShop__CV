package net.mohamadi.App.controller.panel.site;


import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.controller.base.CRUDController;
import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.site.SliderService;
import net.mohamadi.dto.site.SliderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/slider")
public class SliderPanelController implements CRUDController<SliderDto> {


    private final SliderService service;

    @Autowired
    public SliderPanelController(SliderService service) {
        this.service = service;
    }


    @Override
    @CheckPermission("add_slider")
    public APIResponse<SliderDto> add(SliderDto dto) throws ValidationException {
        return APIResponse.<SliderDto>builder()
                .status(HttpStatus.OK)
                .data(service.create(dto))
                .message("")
                .build();

    }

    @Override
    @CheckPermission("delete_slider")
    public APIResponse<Boolean> delete(Long id) {
        return APIResponse.<Boolean>builder()
                .status(HttpStatus.OK)
                .data(service.delete(id))
                .message("")
                .build();
    }

    @Override
    @CheckPermission("list_slider")
    public APIPanelResponse<List<SliderDto>> getAll(Integer page, Integer size) {
        Page<SliderDto> data = service.readAll(page, size);
        return APIPanelResponse.<List<SliderDto>>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(data.getContent())
                .totalCount(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    @Override
    @CheckPermission("edit_slider")
    public APIResponse<SliderDto> edit(SliderDto dto) throws ValidationException, NotFoundExceptionss {

        return APIResponse.<SliderDto>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(service.update(dto))
                .build();
    }
}
