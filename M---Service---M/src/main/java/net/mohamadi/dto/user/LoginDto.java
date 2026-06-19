package net.mohamadi.dto.user;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    //این کلاس برای DTO داشتن از سمت کلاینت به سرور است!
    // یعنی برعکس قبلی ها که از سرور به کلاینت بود.
    private String username;
    private String password;


}
