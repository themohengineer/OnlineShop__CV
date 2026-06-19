package net.mohamadi.Data_Access.entity.site;


import jakarta.persistence.*;
import lombok.*;
import net.mohamadi.Data_Access.entity.file.File;
import net.mohamadi.Data_Access.enums.BlogStatus;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String title;
    @Column(length = 1000, nullable = false)
    private String subTitle;
    private LocalDateTime publishDate;
    private BlogStatus status;
    private Long visitCount;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(nullable = false)
    private File image;

}
