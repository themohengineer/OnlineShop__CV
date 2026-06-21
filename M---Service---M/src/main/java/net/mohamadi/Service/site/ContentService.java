package net.mohamadi.Service.site;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Data_Access.entity.site.Content;
import net.mohamadi.Data_Access.repository.site.ContentRepository;
import net.mohamadi.dto.site.ContentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {


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


}
