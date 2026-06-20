package net.mohamadi.dto.product;


import jakarta.persistence.*;
import lombok.*;
import net.mohamadi.Data_Access.entity.file.File;
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
public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private Long price;
    private Long visitCount;
    private LocalDateTime addDate;
    private FileDto image;
    private ProductCategoryDto category;

    private Set<ColorDto> colors;
    private Set<SizeDto> sizes;




}
