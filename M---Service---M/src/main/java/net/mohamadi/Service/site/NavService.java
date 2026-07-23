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


    @Override
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
    public NavDto create(NavDto navDto) throws ValidationException {
        checkValidation(navDto);
        Nav data = mapper.map(navDto, Nav.class);
        data.setEnable(true);
        Integer lastOrderNumber = repository.findLastOrderNumber();
        if (lastOrderNumber == null) {
            lastOrderNumber = 0;
        }
        data.setOrderNumber(++lastOrderNumber);
        return mapper.map(repository.save(data), NavDto.class);
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);

    }

    @Override
    public NavDto update(NavDto navDto) throws ValidationException, NotFoundExceptionss {

        checkValidation(navDto);
        if (navDto.getId() == null || navDto.getId() <= 0)
            throw new ValidationException("Please enter correct id to update !");
        Nav oldData = repository.findById(navDto.getId()).orElseThrow(NotFoundExceptionss::new);
        oldData.setOrderNumber(Optional.ofNullable(navDto.getOrderNumber()).orElse(oldData.getOrderNumber()));
        oldData.setLink(Optional.ofNullable(navDto.getLink()).orElse(oldData.getLink()));
        oldData.setTitle(Optional.ofNullable(navDto.getTitle()).orElse(oldData.getTitle()));
        repository.save(oldData);
        return mapper.map(oldData, NavDto.class);

    }

    @Override
    public void checkValidation(NavDto navDto) throws ValidationException {
        if (navDto == null) {
            throw new ValidationException("Please fill NAV Data !");
        }
        if (navDto.getTitle() == null || navDto.getTitle().isEmpty()) {
            throw new ValidationException("Please fill NAV Title !");
        }

        if (navDto.getLink() == null || navDto.getLink().isEmpty()) {
            throw new ValidationException("Please fill NAV Title !");
        }
    }
}
