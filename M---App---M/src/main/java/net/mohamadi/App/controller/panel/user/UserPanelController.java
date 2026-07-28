package net.mohamadi.App.controller.panel.user;


import jakarta.servlet.http.HttpServletRequest;

import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.controller.base.CRUDController;
import net.mohamadi.App.model.APIPanelResponse;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Common.exceptions.NotFoundExceptionss;
import net.mohamadi.Common.exceptions.ValidationException;
import net.mohamadi.Service.user.UserService;
import net.mohamadi.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/user")
public class UserPanelController implements CRUDController<UserDto> {


    private final UserService service;

    @Autowired
    public UserPanelController(UserService service) {

        this.service = service;
    }



    //http://127.0.0.1:8080/api/panel/user/test/1
    @GetMapping("/test/{id}")//فقط برای تست
    public UserDto testGetById(@PathVariable Long id) {

        try {
            return service.read(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    //http://127.0.0.1:8080/api/panel/user/1
    @CheckPermission("info_user")
    @GetMapping("{id}")
    public APIResponse<UserDto> getById(
            @PathVariable("id") Long id,
            HttpServletRequest request
    )
    {
        try {
            return APIResponse
                    .<UserDto>builder()
                    .status(HttpStatus.OK)
                    .data(service.read(id))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @CheckPermission("add_user")
    public APIResponse<UserDto> add(UserDto dto) throws ValidationException {
        return APIResponse.<UserDto>builder()
                .status(HttpStatus.OK)
                .data(service.create(dto))
                .message("")
                .build();

    }

    @Override
    @CheckPermission("delete_user")
    public APIResponse<Boolean> delete(Long id) {
        return APIResponse.<Boolean>builder()
                .status(HttpStatus.OK)
                .data(service.delete(id))
                .message("")
                .build();
    }

    @Override
    @CheckPermission("list_user")
    public APIPanelResponse<List<UserDto>> getAll(Integer page, Integer size) {
        Page<UserDto> data = service.readAll(page, size);
        return APIPanelResponse.<List<UserDto>>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(data.getContent())
                .totalCount(data.getTotalElements())
                .totalPages(data.getTotalPages())
                .build();
    }

    @Override
    @CheckPermission("edit_user")
    public APIResponse<UserDto> edit(UserDto dto) throws ValidationException, NotFoundExceptionss {
        return APIResponse.<UserDto>builder()
                .message("")
                .status(HttpStatus.OK)
                .data(service.update(dto))
                .build();
    }



}
