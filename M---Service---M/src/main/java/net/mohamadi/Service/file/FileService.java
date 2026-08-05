package net.mohamadi.Service.file;


import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Data_Access.entity.file.File;
import net.mohamadi.Data_Access.repository.file.FileRepository;
import net.mohamadi.Service.base.DeleteService;
import net.mohamadi.Service.base.ReadService;
import net.mohamadi.dto.file.FileDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService implements
        ReadService<FileDto>,
        DeleteService<FileDto> {


    private final FileRepository repository;
    private final ModelMapper mapper;

    @Value("${app.file.upload.path}")
    private String uploadPath;


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

    public FileDto upload(MultipartFile file) throws IOException, ValidationException {

        if (file == null)
            throw new ValidationException("Please Select File");


        String head = Objects
                .requireNonNull(
                        file
                                .getContentType())
                .substring(
                        0,
                        Objects
                                .requireNonNull(
                                        file
                                                .getOriginalFilename())
                                .lastIndexOf("."));

        String extension = file
                .getOriginalFilename()
                .substring(
                        file
                                .getOriginalFilename()
                                .lastIndexOf(".") + 1);


        String fileName = head + "." + extension;

        File entity = File
                .builder()
                .createDate(LocalDateTime.now())
                .extension(extension)
                .name(head)
                .path(fileName)
                .uuid(UUID.randomUUID().toString())
                .size(file.getSize())
                .build();


        String filePath = uploadPath + java.io.File.separator + fileName;
        Path savePath = Paths.get(filePath);
        java.nio.file.Files.write(savePath, file.getBytes());

        File savedFile = repository.save(entity);
        return mapper.map(savedFile, FileDto.class);

    }
}
