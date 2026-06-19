package net.mohamadi.Service.site;


import net.mohamadi.Data_Access.repository.site.BlogRepository;
import net.mohamadi.Data_Access.repository.site.NavRepository;
import net.mohamadi.dto.site.NavDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavService {


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


}
