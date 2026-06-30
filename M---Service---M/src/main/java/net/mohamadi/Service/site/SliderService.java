package net.mohamadi.Service.site;


import net.mohamadi.Data_Access.repository.site.BlogRepository;
import net.mohamadi.Data_Access.repository.site.SliderRepository;
import net.mohamadi.dto.site.NavDto;
import net.mohamadi.dto.site.SliderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SliderService {


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
}
