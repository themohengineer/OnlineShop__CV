package net.mohamadi.Service.site;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.site.Content;
import net.mohamadi.Data_Access.entity.site.Content;
import net.mohamadi.Data_Access.repository.site.ContentRepository;
import net.mohamadi.Service.base.CreateService;
import net.mohamadi.Service.base.HasValidation;
import net.mohamadi.Service.base.ReadService;
import net.mohamadi.Service.base.UpdateService;
import net.mohamadi.dto.site.ContentDto;
import net.mohamadi.dto.site.ContentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService implements
        CreateService<ContentDto>,
        UpdateService<ContentDto>,
        ReadService<ContentDto>,
        HasValidation<ContentDto> {


    private final ContentRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ContentService(ContentRepository repository,
                          ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<ContentDto> readAll() {

        return repository
                .findAll()
                .stream()
                .map(x -> mapper
                        .map(x, ContentDto.class))
                .toList();
    }

    public ContentDto readByKey(String key) throws NotFoundExceptionss {
        Content content = repository.findFirstByKeyNameEqualsIgnoreCase(key)
                .orElseThrow(NotFoundExceptionss::new);

        return mapper
                .map(content, ContentDto.class);
    }

    @Override
    public Page<ContentDto> readAll(Integer page, Integer size) {

        if (page == null)
            page = 0;
        if (size == null)
            size = 10;

        return repository
                .findAll(Pageable.ofSize(size)
                        .withPage(page))
                .map(
                        x -> mapper
                                .map(x, ContentDto.class)
                );
    }


    @Override
    public ContentDto create(ContentDto dto) throws ValidationException {
        checkValidation(dto);
        Content data = mapper.map(dto, Content.class);
        return mapper.map(repository.save(data), ContentDto.class);
    }


    @Override
    public ContentDto update(ContentDto dto) throws ValidationException, NotFoundExceptionss {

        checkValidation(dto);
        if (dto.getId() == null || dto.getId() <= 0)
            throw new ValidationException("Please enter correct id to update !");
        Content oldData = repository.findById(dto.getId()).orElseThrow(NotFoundExceptionss::new);
        oldData.setKeyName(Optional.ofNullable(dto.getKeyName()).orElse(oldData.getKeyName()));
        oldData.setValueContent(Optional.ofNullable(dto.getValueContent()).orElse(oldData.getValueContent()));
        repository.save(oldData);
        return mapper.map(oldData, ContentDto.class);

    }

    @Override
    public void checkValidation(ContentDto dto) throws ValidationException {
        if (dto == null) {
            throw new ValidationException("Please fill Data !");
        }
        if (dto.getKeyName() == null || dto.getKeyName().isEmpty()) {
            throw new ValidationException("Please fill key !");
        }
        if (dto.getValueContent() == null || dto.getValueContent().isEmpty()) {
            throw new ValidationException("Please fill value !");
        }
    }
}
