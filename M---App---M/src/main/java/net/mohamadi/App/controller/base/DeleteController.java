package net.mohamadi.App.controller.base;

import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DeleteController<Dto> {


    @DeleteMapping("{id}")
    APIResponse<Boolean> delete(@PathVariable Long id);


}
