package net.mohamadi.App.controller.panel.product;


import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.controller.base.CRUDController;
import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.product.ProductService;
import net.mohamadi.dto.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/product")
public class ProductPanelController implements CRUDController<ProductDto> {


    private final ProductService service;

    @Autowired
    public ProductPanelController(ProductService service) {
        this.service = service;
    }


    @Override
    @CheckPermission("add_product")
    public APIResponse<ProductDto> add(ProductDto dto) throws ValidationException {
        return APIResponse.<ProductDto>builder()
                .status(HttpStatus.OK)
                .data(service.create(dto))
                .message("")
                .build();

    }

    @Override
    @CheckPermission("delete_product")
    public APIResponse<Boolean> delete(Long id) {
        return APIResponse.<Boolean>builder()
                .status(HttpStatus.OK)
                .data(service.delete(id))
                .message("")
                .build();
    }

    @Override
    @CheckPermission("list_product")
    public APIPanelResponse<List<ProductDto>> getAll(Integer page, Integer size) {
        Page<ProductDto> data = service.readAll(page, size);
        return APIPanelResponse.<List<ProductDto>>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(data.getContent())
                .totalCount(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    @Override
    @CheckPermission("edit_product")
    public APIResponse<ProductDto> edit(ProductDto dto) throws ValidationException, NotFoundExceptionss {

        return APIResponse.<ProductDto>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(service.update(dto))
                .build();
    }
}
