package net.mohamadi.App.controller.open;


import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Service.product.ProductService;
import net.mohamadi.dto.product.ProductCategoryDto;
import net.mohamadi.dto.product.ProductDto;
import net.mohamadi.enums.ProductQueryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @GetMapping("top/{type}")
    public APIResponse<List<ProductDto>> getTopProducts(
            @PathVariable ProductQueryType type) {

        return APIResponse
                .<List<ProductDto>>builder()
                .status(HttpStatus.OK)
                .data(service.read6TopProducts(type))
                .build();

    }


}
