package net.mohamadi.Service.product;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.file.File;
import net.mohamadi.Data_Access.entity.product.Product;
import net.mohamadi.Data_Access.repository.product.ProductRepository;
import net.mohamadi.Service.base.CRUDService;
import net.mohamadi.Service.base.HasValidation;
import net.mohamadi.dto.product.LimitedProductDto;
import net.mohamadi.dto.product.ProductDto;
import net.mohamadi.enums.ProductQueryType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements
        CRUDService<ProductDto>,
        HasValidation<ProductDto> {


    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ProductService(
            ProductRepository repository,
            ModelMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<LimitedProductDto> read6TopProducts(ProductQueryType type) {

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
                        .map(x, LimitedProductDto.class))
                .toList();
    }


    @Transactional(readOnly = true)
    // باز نگه داشتن سشن Hibernate برای جلوگیری از LazyInitializationException در زمان مپ کردن
    public ProductDto read(Long id) throws NotFoundExceptionss {
        Product product = repository
                .findById(id)
                .orElseThrow(NotFoundExceptionss::new);
        return mapper.map(product, ProductDto.class);
    }


    @Override
    public Page<ProductDto> readAll(Integer page, Integer size) {

        if (page == null)
            page = 0;
        if (size == null)
            size = 10;

        return repository
                .findAll(Pageable.ofSize(size)
                        .withPage(page))
                .map(
                        x -> mapper
                                .map(x, ProductDto.class)
                );
    }


    @Override
    public ProductDto create(ProductDto dto) throws ValidationException {
        checkValidation(dto);
        Product data = mapper.map(dto, Product.class);
        data.setVisitCount(0L);
        data.setEnable(true);
        data.setExist(true);
        data.setAddDate(LocalDateTime.now());
        return mapper.map(repository.save(data), ProductDto.class);
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public ProductDto update(ProductDto dto) throws ValidationException, NotFoundExceptionss {

        checkValidation(dto);
        if (dto.getId() == null || dto.getId() <= 0)
            throw new ValidationException("Please enter correct id to update !");
        Product oldData = repository.findById(dto.getId()).orElseThrow(NotFoundExceptionss::new);
        oldData.setTitle(Optional.ofNullable(dto.getTitle()).orElse(oldData.getTitle()));
        oldData.setDescription(Optional.ofNullable(dto.getDescription()).orElse(oldData.getDescription()));
        oldData.setPrice(Optional.ofNullable(dto.getPrice()).orElse(oldData.getPrice()));
        oldData.setEnable(Optional.ofNullable(dto.getEnable()).orElse(oldData.getEnable()));
        oldData.setExist(Optional.ofNullable(dto.getExist()).orElse(oldData.getExist()));
        if (dto.getImage() != null)
            oldData.setImage(Optional.ofNullable(
                            mapper.map(dto.getImage(), File.class))
                    .orElse(oldData.getImage()));
        repository.save(oldData);
        return mapper.map(oldData, ProductDto.class);

    }

    @Override
    public void checkValidation(ProductDto dto) throws ValidationException {
        if (dto == null) {
            throw new ValidationException("Please fill Data !");
        }
        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new ValidationException("Please fill Title !");
        }
        if (dto.getPrice() == null || dto.getPrice() < 0) {
            throw new ValidationException("Please fill price !");
        }
    }


}
