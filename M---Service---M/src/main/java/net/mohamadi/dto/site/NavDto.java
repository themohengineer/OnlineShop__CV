package net.mohamadi.dto.site;


import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NavDto {

    private Long id;
    private String link;
    private String title;
    private Integer orderNumber;


}
