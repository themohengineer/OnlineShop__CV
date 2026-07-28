package net.mohamadi.Service.file;


import net.mohamadi.Data_Access.repository.file.FileRepository;
import net.mohamadi.Service.base.DeleteService;
import net.mohamadi.Service.base.ReadService;
import net.mohamadi.dto.file.FileDto;
import net.mohamadi.dto.site.NavDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FileService implements
        ReadService<FileDto>,
        DeleteService<FileDto> {


    private final FileRepository repository;
    private final ModelMapper mapper;


    @Autowired
    public FileService(FileRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<FileDto> readAll(Integer page, Integer size) {

        if (page == null)
            page = 0;
        if (size == null)
            size = 10;

        return repository
                .findAll(Pageable.ofSize(size)
                        .withPage(page))
                .map(
                        x -> mapper
                                .map(x, FileDto.class)
                );
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
