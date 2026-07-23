package net.mohamadi.Service.base;

import net.mohamadi.Common.exceptions.ValidationException;

import java.util.List;

public interface CreateService<Dto> {


    Dto create(Dto dto) throws ValidationException;


}
