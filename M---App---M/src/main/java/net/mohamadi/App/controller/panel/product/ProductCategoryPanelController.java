package net.mohamadi.App.controller.panel.product;


import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.controller.base.CreatController;
import net.mohamadi.App.controller.base.ReadController;
import net.mohamadi.App.controller.base.UpdateController;
import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.product.ProductCategoryService;
import net.mohamadi.dto.product.ProductCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/productCategory")
public class ProductCategoryPanelController implements
        CreatController<ProductCategoryDto>,
        UpdateController<ProductCategoryDto>,
        ReadController<ProductCategoryDto> {


    private final ProductCategoryService service;

    @Autowired
    public ProductCategoryPanelController(ProductCategoryService service) {
        this.service = service;
    }


    @Override
    @CheckPermission("add_product_category")
    public APIResponse<ProductCategoryDto> add(ProductCategoryDto dto) throws ValidationException {
        return APIResponse.<ProductCategoryDto>builder()
                .status(HttpStatus.OK)
                .data(service.create(dto))
                .message("")
                .build();

    }


    @Override
    @CheckPermission("list_product_category")
    public APIPanelResponse<List<ProductCategoryDto>> getAll(Integer page, Integer productCategory) {
        Page<ProductCategoryDto> data = service.readAll(page, productCategory);
        return APIPanelResponse.<List<ProductCategoryDto>>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(data.getContent())
                .totalCount(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    @Override
    @CheckPermission("edit_product_category")
    public APIResponse<ProductCategoryDto> edit(ProductCategoryDto dto) throws ValidationException, NotFoundExceptionss {

        return APIResponse.<ProductCategoryDto>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(service.update(dto))
                .build();
    }
}
