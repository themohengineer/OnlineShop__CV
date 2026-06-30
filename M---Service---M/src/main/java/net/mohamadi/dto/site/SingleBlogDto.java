package net.mohamadi.dto.site;


import lombok.*;
import net.mohamadi.Data_Access.entity.file.File;
import net.mohamadi.dto.file.FileDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleBlogDto {


    private Long id;
    private String title;
    private String subTitle;
    private LocalDateTime publishDate;
    private Long visitCount;
    private FileDto image;
    private String description;

}
