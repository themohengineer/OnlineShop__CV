package net.mohamadi.Service.product;


import net.mohamadi.Data_Access.entity.product.Product;
import net.mohamadi.Data_Access.repository.product.ColorRepository;
import net.mohamadi.Data_Access.repository.product.ProductCategoryRepository;
import net.mohamadi.Data_Access.repository.product.ProductRepository;
import net.mohamadi.Data_Access.repository.product.SizeRepository;
import net.mohamadi.dto.product.ProductCategoryDto;
import net.mohamadi.dto.product.ProductDto;
import net.mohamadi.enums.ProductQueryType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {


    private final ProductRepository repository;
    private final ProductCategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ModelMapper mapper;

    @Autowired
    public ProductService(
            ProductRepository repository,
            ProductCategoryRepository categoryRepository,
            ColorRepository colorRepository,
            SizeRepository sizeRepository, ModelMapper mapper
    ) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
        this.mapper = mapper;
    }

    public List<ProductCategoryDto> readAllCategories() {
        return categoryRepository
                .findAllByEnableIsTrue()
                .stream()
                .map(x -> mapper
                        .map(x, ProductCategoryDto.class))
                .toList();

    }


    public List<ProductDto> read6TopProducts(ProductQueryType type) {

        List<Product> result = new ArrayList<>();
        switch (type) {
            case Popular -> result = repository.find6PopularProducts();
            case Newest -> result = repository.find6NewestProducts();
            case Cheapest -> result = repository.find6CheapestProducts();
            case Expensive -> result = repository.find6ExpensiveProducts();
        }

        return result
                .stream()
                .map(x -> mapper
                        .map(x, ProductDto.class))
                .toList();
    }


}
