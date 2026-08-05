package net.mohamadi.Data_Access.entity.file;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String path;
    private String uuid;  //Similar to xxxxxxxx_xxxx_xxxx_xxxx_xxxxxxxxxxxx
    private String extension;
    private Long size;
    private LocalDateTime createDate;


}
