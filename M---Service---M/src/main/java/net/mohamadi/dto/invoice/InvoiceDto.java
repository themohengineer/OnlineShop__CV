package net.mohamadi.dto.invoice;


import jakarta.persistence.*;
import lombok.*;
import net.mohamadi.Data_Access.entity.order.InvoiceItem;
import net.mohamadi.Data_Access.enums.OrderStatus;
import net.mohamadi.enums.PaymentGateway;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceDto {



    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime payedDate;
    private Long totalAmount;
    private OrderStatus status;
    private List<InvoiceItemDto> items;



}
