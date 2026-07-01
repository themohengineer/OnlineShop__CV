package net.mohamadi.dto.user;


import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String mobile;
    private String firstName;
    private String lastName;
    private String tel;
    private String address;
    private String postalCode;


}
