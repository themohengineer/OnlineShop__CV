package net.mohamadi.Service.base;

import net.mohamadi.Common.exceptions.ValidationException;

public interface HasValidation<Dto> {


    void checkValidation(Dto dto) throws ValidationException;


}
