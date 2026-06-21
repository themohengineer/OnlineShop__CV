package net.mohamadi.dto.site;


import lombok.*;
import net.mohamadi.Data_Access.entity.file.File;

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
    private File image;
    private String description;

}
