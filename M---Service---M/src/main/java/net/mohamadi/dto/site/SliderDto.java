package net.mohamadi.dto.site;


import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import net.mohamadi.Data_Access.entity.file.File;
import net.mohamadi.dto.file.FileDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SliderDto {

    private Long id;
    private String title;
    private String link;
    private Integer orderNumber;
    private FileDto image;

}
