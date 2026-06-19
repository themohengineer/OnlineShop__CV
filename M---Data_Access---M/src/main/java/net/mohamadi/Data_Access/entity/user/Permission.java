package net.mohamadi.Data_Access.entity.user;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "Text")
    private String description;


    //کد پایین یک رابطه خودارجاع (Self-Referencing) برای
    // ساختار درختی (مثل منوها یا سطوح دسترسی) تعریف می‌کند.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Permission parent;

    @OneToMany(mappedBy = "parent")
    private List<Permission> children = new ArrayList<>();

}
