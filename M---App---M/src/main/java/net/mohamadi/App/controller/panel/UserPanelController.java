package net.mohamadi.App.controller.panel;


import jakarta.servlet.http.HttpServletRequest;

import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Service.user.UserService;
import net.mohamadi.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/panel/user")
public class UserPanelController {


    private final UserService userService;

    @Autowired
    public UserPanelController(UserService userService) {

        this.userService = userService;
    }



    //http://127.0.0.1:8080/api/panel/user/test/1
    @GetMapping("/test/{id}")//فقط برای تست
    public UserDto testGetById(@PathVariable Long id) {

        try {
            return userService.getUserById(id);
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
                    .data(userService.getUserById(id))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}
