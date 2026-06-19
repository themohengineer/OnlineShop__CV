package net.mohamadi.Data_Access.entity.site;


import jakarta.persistence.*;
import lombok.*;
import net.mohamadi.Data_Access.entity.file.File;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String keyName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String valueContent;



}
