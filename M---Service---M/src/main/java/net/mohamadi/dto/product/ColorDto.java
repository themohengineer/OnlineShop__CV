package net.mohamadi.dto.product;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import net.mohamadi.Data_Access.entity.product.Color;
import net.mohamadi.Data_Access.entity.product.Size;
import net.mohamadi.dto.file.FileDto;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorDto {

    private Long id;
    private String name;
    private String hex;




}
