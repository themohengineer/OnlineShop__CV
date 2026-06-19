package net.mohamadi.Service.file;


import net.mohamadi.Data_Access.repository.file.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {


    private final FileRepository repository;


    @Autowired
    public FileService(FileRepository repository) {
        this.repository = repository;
    }
}
