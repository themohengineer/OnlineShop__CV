package net.mohamadi.Service.site;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.site.Nav;
import net.mohamadi.Data_Access.repository.site.BlogRepository;
import net.mohamadi.Data_Access.repository.site.NavRepository;
import net.mohamadi.Service.base.CRUDService;
import net.mohamadi.Service.base.HasValidation;
import net.mohamadi.Service.base.ReadService;
import net.mohamadi.dto.site.NavDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NavService implements CRUDService<NavDto>, HasValidation<NavDto> {


    private final NavRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public NavService(NavRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<NavDto> readAll() {
        return repository
                .findAllByEnableIsTrueOrderByOrderNumberAsc()
                .stream()
                .map(
                        x -> mapper
                                .map(x, NavDto.class)
                )
                .toList();
    }

    @Override
    public Page<NavDto> readAll(Integer page, Integer size) {

        if (page == null)
            page = 0;
        if (size == null)
            size = 10;

        return repository
                .findAll(Pageable.ofSize(size)
                        .withPage(page))
                .map(
                        x -> mapper
                                .map(x, NavDto.class)
                );
    }


    @Override
    public NavDto create(NavDto dto) throws ValidationException {
        checkValidation(dto);
        Nav data = mapper.map(dto, Nav.class);
        data.setEnable(true);
        Integer lastOrderNumber = repository.findLastOrderNumber();
        if (lastOrderNumber == null) {
            lastOrderNumber = 0;
        }
        data.setOrderNumber(++lastOrderNumber);
        return mapper.map(repository.save(data), NavDto.class);
    }

    @Override
    public Boolean delete(Long id) {

        repository.deleteById(id);
        return true;

    }

    @Override
    public NavDto update(NavDto dto) throws ValidationException, NotFoundExceptionss {

        checkValidation(dto);
        if (dto.getId() == null || dto.getId() <= 0)
            throw new ValidationException("Please enter correct id to update !");
        Nav oldData = repository.findById(dto.getId()).orElseThrow(NotFoundExceptionss::new);
        oldData.setOrderNumber(Optional.ofNullable(dto.getOrderNumber()).orElse(oldData.getOrderNumber()));
        oldData.setLink(Optional.ofNullable(dto.getLink()).orElse(oldData.getLink()));
        oldData.setTitle(Optional.ofNullable(dto.getTitle()).orElse(oldData.getTitle()));
        repository.save(oldData);
        return mapper.map(oldData, NavDto.class);

    }

    @Override
    public void checkValidation(NavDto dto) throws ValidationException {
        if (dto == null) {
            throw new ValidationException("Please fill Data !");
        }
        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new ValidationException("Please fill Title !");
        }
        if (dto.getLink() == null || dto.getLink().isEmpty()) {
            throw new ValidationException("Please fill Title !");
        }
    }
}
