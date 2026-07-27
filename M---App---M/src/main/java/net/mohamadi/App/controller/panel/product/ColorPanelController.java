package net.mohamadi.App.controller.panel.product;


import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.controller.base.CreatController;
import net.mohamadi.App.controller.base.ReadController;
import net.mohamadi.App.controller.base.UpdateController;
import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.product.ColorService;
import net.mohamadi.dto.product.ColorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/color")
public class ColorPanelController implements
        CreatController<ColorDto>,
        UpdateController<ColorDto>,
        ReadController<ColorDto> {


    private final ColorService service;

    @Autowired
    public ColorPanelController(ColorService service) {
        this.service = service;
    }


    @Override
    @CheckPermission("add_color")
    public APIResponse<ColorDto> add(ColorDto dto) throws ValidationException {
        return APIResponse.<ColorDto>builder()
                .status(HttpStatus.OK)
                .data(service.create(dto))
                .message("")
                .build();

    }


    @Override
    @CheckPermission("list_color")
    public APIPanelResponse<List<ColorDto>> getAll(Integer page, Integer color) {
        Page<ColorDto> data = service.readAll(page, color);
        return APIPanelResponse.<List<ColorDto>>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(data.getContent())
                .totalCount(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    @Override
    @CheckPermission("edit_color")
    public APIResponse<ColorDto> edit(ColorDto dto) throws ValidationException, NotFoundExceptionss {

        return APIResponse.<ColorDto>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(service.update(dto))
                .build();
    }
}
