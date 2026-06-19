package net.mohamadi.Data_Access.entity.site;


import jakarta.persistence.*;
import lombok.*;
import net.mohamadi.Data_Access.entity.user.Role;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Nav {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String link;


    private Integer orderNumber;

    private Boolean enable = true;


}
