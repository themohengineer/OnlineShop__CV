package net.mohamadi.dto.invoice;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import net.mohamadi.Data_Access.entity.order.Invoice;
import net.mohamadi.Data_Access.entity.order.InvoiceItem;
import net.mohamadi.Data_Access.entity.product.Color;
import net.mohamadi.Data_Access.entity.product.Product;
import net.mohamadi.Data_Access.entity.product.Size;
import net.mohamadi.Data_Access.enums.OrderStatus;
import net.mohamadi.dto.product.ColorDto;
import net.mohamadi.dto.product.ProductDto;
import net.mohamadi.dto.product.SizeDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceItemDto {

    private Long id;
    private ProductDto product;
    private SizeDto size;
    private ColorDto color;
    private Integer quantity;
    private Long price;




}
