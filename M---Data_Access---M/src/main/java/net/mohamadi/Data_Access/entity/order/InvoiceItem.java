package net.mohamadi.Data_Access.entity.order;


import jakarta.persistence.*;
import lombok.*;
import net.mohamadi.Data_Access.entity.product.Color;
import net.mohamadi.Data_Access.entity.product.Product;
import net.mohamadi.Data_Access.entity.product.Size;
import net.mohamadi.Data_Access.enums.OrderStatus;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


//Invoice means " Factor "
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Invoice invoice;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Size size;
    @ManyToOne
    private Color color;

    //quantity menas "tedad" in persian
    private Integer quantity;
    private Long price;


}
