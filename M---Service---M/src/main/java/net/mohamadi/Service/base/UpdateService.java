package net.mohamadi.Service.base;

import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;

public interface UpdateService<Dto> {


    Dto update(Dto dto) throws ValidationException, NotFoundExceptionss;


}
