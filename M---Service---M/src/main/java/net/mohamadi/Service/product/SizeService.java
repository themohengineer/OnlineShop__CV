package net.mohamadi.Service.product;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.product.Size;
import net.mohamadi.Data_Access.repository.product.SizeRepository;
import net.mohamadi.Service.base.CRUDService;
import net.mohamadi.Service.base.HasValidation;
import net.mohamadi.dto.product.SizeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SizeService implements CRUDService<SizeDto>, HasValidation<SizeDto> {


    private final SizeRepository repository;
    private final ModelMapper mapper;


    @Autowired
    public SizeService(SizeRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Page<SizeDto> readAll(Integer page, Integer size) {

        if (page == null)
            page = 0;
        if (size == null)
            size = 10;

        return repository
                .findAll(Pageable.ofSize(size)
                        .withPage(page))
                .map(
                        x -> mapper
                                .map(x, SizeDto.class)
                );
    }


    @Override
    public SizeDto create(SizeDto dto) throws ValidationException {
        checkValidation(dto);
        Size data = mapper.map(dto, Size.class);
        return mapper.map(repository.save(data), SizeDto.class);
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public SizeDto update(SizeDto dto) throws ValidationException, NotFoundExceptionss {

        checkValidation(dto);
        if (dto.getId() == null || dto.getId() <= 0)
            throw new ValidationException("Please enter correct id to update !");
        Size oldData = repository.findById(dto.getId()).orElseThrow(NotFoundExceptionss::new);
        oldData.setTitle(Optional.ofNullable(dto.getTitle()).orElse(oldData.getTitle()));
        oldData.setDescription(Optional.ofNullable(dto.getDescription()).orElse(oldData.getDescription()));
        oldData.setDescription(Optional.ofNullable(dto.getDescription()).orElse(oldData.getDescription()));
        repository.save(oldData);
        return mapper.map(oldData, SizeDto.class);

    }

    @Override
    public void checkValidation(SizeDto dto) throws ValidationException {
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
