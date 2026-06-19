package net.mohamadi.Service.site;


import net.mohamadi.Data_Access.repository.file.FileRepository;
import net.mohamadi.Data_Access.repository.site.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {


    private final BlogRepository repository;


    @Autowired
    public BlogService(BlogRepository repository) {
        this.repository = repository;
    }
}
