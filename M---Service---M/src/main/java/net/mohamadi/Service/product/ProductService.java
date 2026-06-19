package net.mohamadi.Service.product;


import net.mohamadi.Data_Access.repository.product.ColorRepository;
import net.mohamadi.Data_Access.repository.product.ProductCategoryRepository;
import net.mohamadi.Data_Access.repository.product.ProductRepository;
import net.mohamadi.Data_Access.repository.product.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    private final ProductRepository repository;
    private final ProductCategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @Autowired
    public ProductService(
            ProductRepository repository,
            ProductCategoryRepository categoryRepository,
            ColorRepository colorRepository,
            SizeRepository sizeRepository
    ) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
    }
}
