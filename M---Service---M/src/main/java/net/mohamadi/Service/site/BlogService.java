package net.mohamadi.Service.site;


import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Data_Access.entity.site.Blog;
import net.mohamadi.Data_Access.repository.file.FileRepository;
import net.mohamadi.Data_Access.repository.site.BlogRepository;
import net.mohamadi.dto.site.BlogDto;
import net.mohamadi.dto.site.SingleBlogDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {


    private final BlogRepository repository;
    private final ModelMapper mapper;


    @Autowired
    public BlogService(BlogRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<BlogDto> readAll(Integer page, Integer size) {

        if (page == null)
            page = 0;
        if (size == null)
            size = 16;

        return repository
                .findAllPublished(Pageable.ofSize(size).withPage(page))
                .stream()
                .map(x -> mapper
                        .map(x, BlogDto.class))
                .toList();

    }


    public SingleBlogDto read(Long id) throws NotFoundExceptionss {
        Blog blog = repository
                .findById(id)
                .orElseThrow(NotFoundExceptionss::new);
        return mapper.map(blog, SingleBlogDto.class);
    }


}
