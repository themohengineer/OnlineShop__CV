package net.mohamadi.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    private Long id;
    private String name;
    private String description;
    private PermissionDto parent;



}
