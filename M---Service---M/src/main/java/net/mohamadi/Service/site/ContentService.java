package net.mohamadi.Service.site;


import net.mohamadi.Data_Access.repository.site.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {


    private final ContentRepository repository;


    @Autowired
    public ContentService(ContentRepository repository) {
        this.repository = repository;
    }
}
