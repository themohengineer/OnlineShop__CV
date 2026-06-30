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

public class Slider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String link;


    private Integer orderNumber;

    private Boolean enable = true;

    @ManyToOne
    @JoinColumn(nullable = false)
    private File image;   //یعنی هر اسلایدر (Slider) یک فایل تصویر دارد، ولی
    // هر فایل (File) می‌تواند برای چندین اسلایدر استفاده شود.

}
