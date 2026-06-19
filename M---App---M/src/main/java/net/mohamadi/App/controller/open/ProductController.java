package net.mohamadi.App.controller.open;


import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Data_Access.entity.product.ProductCategory;
import net.mohamadi.Service.product.ProductService;
import net.mohamadi.Service.site.NavService;
import net.mohamadi.dto.product.ProductCategoryDto;
import net.mohamadi.dto.site.NavDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;


    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("category")
    public APIResponse<List<ProductCategoryDto>> getAllCategories() {

        return APIResponse
                .<List<ProductCategoryDto>>builder()
                .status(HttpStatus.OK)
                .data(service.readAllCategories())
                .build();

    }


}
