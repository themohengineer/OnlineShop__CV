package net.mohamadi.Data_Access.entity.payment;


import jakarta.persistence.*;
import lombok.*;
import net.mohamadi.Data_Access.entity.order.InvoiceItem;
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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

}
