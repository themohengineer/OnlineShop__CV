package net.mohamadi.dto.file;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

    private Long id;
    private String name;
    private String uuid;  //Similar to xxxxxxxx_xxxx_xxxx_xxxx_xxxxxxxxxxxx
    private String extension;
    private Long size;




}
