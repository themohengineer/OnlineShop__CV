package net.mohamadi.Service.product;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.file.File;
import net.mohamadi.Data_Access.entity.product.ProductCategory;
import net.mohamadi.Data_Access.repository.product.ProductCategoryRepository;
import net.mohamadi.Service.base.CRUDService;
import net.mohamadi.Service.base.HasValidation;
import net.mohamadi.dto.product.ProductCategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryService implements CRUDService<ProductCategoryDto>, HasValidation<ProductCategoryDto> {


    private final ProductCategoryRepository repository;
    private final ModelMapper mapper;


    @Autowired
    public ProductCategoryService(ProductCategoryRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Page<ProductCategoryDto> readAll(Integer page, Integer color) {

        if (page == null)
            page = 0;
        if (color == null)
            color = 10;

        return repository
                .findAll(Pageable.ofSize(color)
                        .withPage(page))
                .map(
                        x -> mapper
                                .map(x, ProductCategoryDto.class)
                );
    }


    @Override
    public ProductCategoryDto create(ProductCategoryDto dto) throws ValidationException {
        checkValidation(dto);
        ProductCategory data = mapper.map(dto, ProductCategory.class);
        return mapper.map(repository.save(data), ProductCategoryDto.class);
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public ProductCategoryDto update(ProductCategoryDto dto) throws ValidationException, NotFoundExceptionss {

        checkValidation(dto);
        if (dto.getId() == null || dto.getId() <= 0)
            throw new ValidationException("Please enter correct id to update !");
        ProductCategory oldData = repository.findById(dto.getId()).orElseThrow(NotFoundExceptionss::new);
        oldData.setTitle(Optional.ofNullable(dto.getTitle()).orElse(oldData.getTitle()));
        oldData.setDescription(Optional.ofNullable(dto.getDescription()).orElse(oldData.getDescription()));
        if (dto.getImage() != null)
            oldData.setImage(Optional.ofNullable(
                            mapper.map(dto.getImage(), File.class))
                    .orElse(oldData.getImage()));
        repository.save(oldData);
        return mapper.map(oldData, ProductCategoryDto.class);

    }

    @Override
    public void checkValidation(ProductCategoryDto dto) throws ValidationException {
        if (dto == null) {
            throw new ValidationException("Please fill Data !");
        }
        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new ValidationException("Please fill Title !");
        }
        if (dto.getDescription() == null || dto.getDescription().isEmpty()) {
            throw new ValidationException("Please fill Description !");
        }
    }

}
