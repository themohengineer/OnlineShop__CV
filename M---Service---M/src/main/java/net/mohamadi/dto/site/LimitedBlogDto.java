package net.mohamadi.dto.site;


import lombok.*;
import net.mohamadi.Data_Access.enums.BlogStatus;
import net.mohamadi.dto.file.FileDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimitedBlogDto {


    private Long id;
    private String title;
    private String subTitle;
    private LocalDateTime publishDate;
    private Long visitCount;
    private FileDto image;

}
