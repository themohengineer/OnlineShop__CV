package net.mohamadi.Service.site;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.file.File;
import net.mohamadi.Data_Access.entity.site.Slider;
import net.mohamadi.Data_Access.repository.site.BlogRepository;
import net.mohamadi.Data_Access.repository.site.SliderRepository;
import net.mohamadi.Service.base.CRUDService;
import net.mohamadi.Service.base.HasValidation;
import net.mohamadi.dto.site.SliderDto;
import net.mohamadi.dto.site.SliderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SliderService implements CRUDService<SliderDto>, HasValidation<SliderDto> {


    private final SliderRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public SliderService(SliderRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<SliderDto> readAll() {

        return repository
                .findAllByEnableIsTrueOrderByOrderNumberAsc()
                .stream()
                .map(
                        x -> mapper
                                .map(x, SliderDto.class)
                )
                .toList();

    }


    @Override
    public Page<SliderDto> readAll(Integer page, Integer size) {

        if (page == null)
            page = 0;
        if (size == null)
            size = 10;

        return repository
                .findAll(Pageable.ofSize(size)
                        .withPage(page))
                .map(
                        x -> mapper
                                .map(x, SliderDto.class)
                );
    }


    @Override
    public SliderDto create(SliderDto dto) throws ValidationException {
        checkValidation(dto);
        Slider data = mapper.map(dto, Slider.class);
        data.setEnable(true);
        Integer lastOrderNumber = repository.findLastOrderNumber();
        if (lastOrderNumber == null) {
            lastOrderNumber = 0;
        }
        data.setOrderNumber(++lastOrderNumber);
        return mapper.map(repository.save(data), SliderDto.class);
    }

    @Override
    public Boolean delete(Long id) {

        repository.deleteById(id);
        return true;

    }

    @Override
    public SliderDto update(SliderDto dto) throws ValidationException, NotFoundExceptionss {

        checkValidation(dto);
        if (dto.getId() == null || dto.getId() <= 0)
            throw new ValidationException("Please enter correct id to update !");
        Slider oldData = repository.findById(dto.getId()).orElseThrow(NotFoundExceptionss::new);
        oldData.setOrderNumber(Optional.ofNullable(dto.getOrderNumber()).orElse(oldData.getOrderNumber()));
        oldData.setLink(Optional.ofNullable(dto.getLink()).orElse(oldData.getLink()));
        oldData.setTitle(Optional.ofNullable(dto.getTitle()).orElse(oldData.getTitle()));
        if (dto.getImage() != null)
            oldData.setImage(Optional.ofNullable(
                            mapper.map(dto.getImage(), File.class))
                    .orElse(oldData.getImage()));
        repository.save(oldData);
        return mapper.map(oldData, SliderDto.class);

    }

    @Override
    public void checkValidation(SliderDto dto) throws ValidationException {
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
