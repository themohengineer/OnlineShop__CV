package net.mohamadi.Service.base;

import org.springframework.data.domain.Page;

public interface ReadService<Dto> {

    Page<Dto> readAll(Integer page, Integer size);


}
