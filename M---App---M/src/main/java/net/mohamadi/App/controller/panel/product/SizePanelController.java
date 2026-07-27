package net.mohamadi.App.controller.panel.product;


import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.controller.base.CreatController;
import net.mohamadi.App.controller.base.ReadController;
import net.mohamadi.App.controller.base.UpdateController;
import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.product.SizeService;
import net.mohamadi.dto.product.SizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/size")
public class SizePanelController implements
        CreatController<SizeDto>,
        UpdateController<SizeDto>,
        ReadController<SizeDto> {


    private final SizeService service;

    @Autowired
    public SizePanelController(SizeService service) {
        this.service = service;
    }


    @Override
    @CheckPermission("add_size")
    public APIResponse<SizeDto> add(SizeDto dto) throws ValidationException {
        return APIResponse.<SizeDto>builder()
                .status(HttpStatus.OK)
                .data(service.create(dto))
                .message("")
                .build();

    }


    @Override
    @CheckPermission("list_size")
    public APIPanelResponse<List<SizeDto>> getAll(Integer page, Integer size) {
        Page<SizeDto> data = service.readAll(page, size);
        return APIPanelResponse.<List<SizeDto>>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(data.getContent())
                .totalCount(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    @Override
    @CheckPermission("edit_size")
    public APIResponse<SizeDto> edit(SizeDto dto) throws ValidationException, NotFoundExceptionss {

        return APIResponse.<SizeDto>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(service.update(dto))
                .build();
    }
}
