package net.mohamadi.dto.product;


import lombok.*;
import net.mohamadi.dto.file.FileDto;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimitedProductDto {

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
