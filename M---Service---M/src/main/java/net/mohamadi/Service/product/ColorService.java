package net.mohamadi.Service.product;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.product.Color;
import net.mohamadi.Data_Access.repository.product.ColorRepository;
import net.mohamadi.Service.base.CRUDService;
import net.mohamadi.Service.base.HasValidation;
import net.mohamadi.dto.product.ColorDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorService implements CRUDService<ColorDto>, HasValidation<ColorDto> {


    private final ColorRepository repository;
    private final ModelMapper mapper;


    @Autowired
    public ColorService(ColorRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Page<ColorDto> readAll(Integer page, Integer color) {

        if (page == null)
            page = 0;
        if (color == null)
            color = 10;

        return repository
                .findAll(Pageable.ofSize(color)
                        .withPage(page))
                .map(
                        x -> mapper
                                .map(x, ColorDto.class)
                );
    }


    @Override
    public ColorDto create(ColorDto dto) throws ValidationException {
        checkValidation(dto);
        Color data = mapper.map(dto, Color.class);
        return mapper.map(repository.save(data), ColorDto.class);
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public ColorDto update(ColorDto dto) throws ValidationException, NotFoundExceptionss {

        checkValidation(dto);
        if (dto.getId() == null || dto.getId() <= 0)
            throw new ValidationException("Please enter correct id to update !");
        Color oldData = repository.findById(dto.getId()).orElseThrow(NotFoundExceptionss::new);
        oldData.setName(Optional.ofNullable(dto.getName()).orElse(oldData.getName()));
        oldData.setHex(Optional.ofNullable(dto.getHex()).orElse(oldData.getHex()));
        repository.save(oldData);
        return mapper.map(oldData, ColorDto.class);

    }

    @Override
    public void checkValidation(ColorDto dto) throws ValidationException {
        if (dto == null) {
            throw new ValidationException("Please fill Data !");
        }
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new ValidationException("Please fill name !");
        }
        if (dto.getHex() == null || dto.getHex().isEmpty()) {
            throw new ValidationException("Please fill hex !");
        }
    }

}
