package net.mohamadi.App.controller.base;

import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UpdateController<Dto> {


    @PutMapping("edit")
    APIResponse<Dto> edit(@RequestBody Dto dto) throws ValidationException, NotFoundExceptionss;


}
