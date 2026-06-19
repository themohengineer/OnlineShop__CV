package net.mohamadi.App.controller.open;



import net.mohamadi.App.model.APIResponse;
import net.mohamadi.Service.user.UserService;
import net.mohamadi.dto.user.LimitedUserDto;
import net.mohamadi.dto.user.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //متد زیر برای DTO داشتن از سمت کلاینت به سرور است!
    // یعنی برعکس قبلی ها که از سرور به کلاینت بود.
    // البته فقط با PostMan و  CMD میتوان آن را صدا زد!
    @PostMapping("login")
    public APIResponse<LimitedUserDto> login(@RequestBody LoginDto dto) {

        try {
            return APIResponse.<LimitedUserDto>builder()
                    .data(userService.login(dto))
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            return APIResponse.<LimitedUserDto>builder()
                    .data(null)
                    .status(HttpStatus.valueOf("Error from UserController !!!"))
                    .build();

        }

    }

    //_________________________________________________________________________________________________
    //متد زیر همان کار متد بالا را میکند اما با URL برای وقتی که PostMan نداریم!
    //برای تست موقتی چون امنیت کمی دارد نسبت به روش Post
    //http://127.0.0.1:8080/api/user/test-login?username=admin&password=123
    @GetMapping("/test-login")
    public APIResponse<LimitedUserDto> testLogin(@RequestParam String username, @RequestParam String password) {
        LoginDto dto = new LoginDto(username, password);
        try {
            return APIResponse.<LimitedUserDto>builder()
                    .data(userService.login(dto))
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            return APIResponse.<LimitedUserDto>builder()
                    .data(null)
                    .message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();

        }
    }

    //همان کار متد بالایی اما بدون APIRespons
    @GetMapping("/test-login2")
    public LimitedUserDto testLogin2(@RequestParam String username, @RequestParam String password) throws Exception {
        LoginDto dto = new LoginDto(username, password);
        return userService.login(dto);
    }


}
