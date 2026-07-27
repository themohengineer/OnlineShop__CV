package net.mohamadi.App.controller.base;

import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.ValidationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CreatController<Dto> {


    @PostMapping("add")
    APIResponse<Dto> add(@RequestBody Dto dto) throws ValidationException;


}
