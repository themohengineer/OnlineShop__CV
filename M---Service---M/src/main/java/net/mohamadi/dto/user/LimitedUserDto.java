package net.mohamadi.dto.user;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LimitedUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String LastName;
    private String token;

}
