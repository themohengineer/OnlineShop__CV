package net.mohamadi.App.controller.base;

public interface CRUDController<Dto> extends
        CreatController<Dto>,
        ReadController<Dto>,
        UpdateController<Dto>,
        DeleteController<Dto> {
}
