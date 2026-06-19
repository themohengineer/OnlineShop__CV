package net.mohamadi.Data_Access.entity.order;


import jakarta.persistence.*;
import lombok.*;
import net.mohamadi.Data_Access.entity.file.File;
import net.mohamadi.Data_Access.enums.BlogStatus;
import net.mohamadi.Data_Access.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


//Invoice means " Factor "
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String number;

    private LocalDateTime createDate;
    private LocalDateTime payedDate;
    private OrderStatus status;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItem> items;

}
