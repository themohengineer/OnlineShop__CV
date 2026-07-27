package net.mohamadi.Service.site;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.site.Blog;
import net.mohamadi.Data_Access.entity.site.Blog;
import net.mohamadi.Data_Access.enums.BlogStatus;
import net.mohamadi.Data_Access.repository.file.FileRepository;
import net.mohamadi.Data_Access.repository.site.BlogRepository;
import net.mohamadi.Service.base.CRUDService;
import net.mohamadi.Service.base.HasValidation;
import net.mohamadi.dto.site.BlogDto;
import net.mohamadi.dto.site.BlogDto;
import net.mohamadi.dto.site.LimitedBlogDto;
import net.mohamadi.dto.site.SingleBlogDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements CRUDService<BlogDto>, HasValidation<BlogDto> {


    private final BlogRepository repository;
    private final ModelMapper mapper;


    @Autowired
    public BlogService(BlogRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<LimitedBlogDto> readAllPublished(Integer page, Integer size) {

        if (page == null)
            page = 0;
        if (size == null)
            size = 16;

        return repository
                .findAllPublished(Pageable.ofSize(size).withPage(page))
                .stream()
                .map(x -> mapper
                        .map(x, LimitedBlogDto.class))
                .toList();

    }


    public SingleBlogDto read(Long id) throws NotFoundExceptionss {
        Blog blog = repository
                .findById(id)
                .orElseThrow(NotFoundExceptionss::new);
        return mapper.map(blog, SingleBlogDto.class);
    }


    @Override
    public Page<BlogDto> readAll(Integer page, Integer size) {

        if (page == null)
            page = 0;
        if (size == null)
            size = 10;

        return repository
                .findAll(Pageable.ofSize(size)
                        .withPage(page))
                .map(
                        x -> mapper
                                .map(x, BlogDto.class)
                );
    }


    @Override
    public BlogDto create(BlogDto dto) throws ValidationException {
        checkValidation(dto);
        Blog data = mapper.map(dto, Blog.class);
        if (data.getPublishDate() == null)
            data.setPublishDate(LocalDateTime.now());
        if (data.getStatus() == null)
            data.setStatus(BlogStatus.Published);
        data.setVisitCount(0L);
        return mapper.map(repository.save(data), BlogDto.class);
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public BlogDto update(BlogDto dto) throws ValidationException, NotFoundExceptionss {

        checkValidation(dto);
        if (dto.getId() == null || dto.getId() <= 0)
            throw new ValidationException("Please enter correct id to update !");
        Blog oldData = repository.findById(dto.getId()).orElseThrow(NotFoundExceptionss::new);
        oldData.setTitle(Optional.ofNullable(dto.getTitle()).orElse(oldData.getTitle()));
        oldData.setSubTitle(Optional.ofNullable(dto.getSubTitle()).orElse(oldData.getSubTitle()));
        oldData.setPublishDate(Optional.ofNullable(dto.getPublishDate()).orElse(oldData.getPublishDate()));
        oldData.setStatus(Optional.ofNullable(dto.getStatus()).orElse(oldData.getStatus()));
        oldData.setDescription(Optional.ofNullable(dto.getDescription()).orElse(oldData.getDescription()));
        repository.save(oldData);
        return mapper.map(oldData, BlogDto.class);

    }

    @Override
    public void checkValidation(BlogDto dto) throws ValidationException {
        if (dto == null) {
            throw new ValidationException("Please fill Data !");
        }
        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new ValidationException("Please fill Title !");
        }
        if (dto.getSubTitle() == null || dto.getSubTitle().isEmpty()) {
            throw new ValidationException("Please fill Title !");
        }
    }

}
