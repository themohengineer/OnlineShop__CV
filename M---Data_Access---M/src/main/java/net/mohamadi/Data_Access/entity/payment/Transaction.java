package net.mohamadi.Data_Access.entity.payment;


import jakarta.persistence.*;
import lombok.*;
import net.mohamadi.Data_Access.entity.order.Invoice;
import net.mohamadi.Data_Access.entity.user.User;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trx")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;

    @ManyToOne
    private Invoice invoice;

    @ManyToOne
    private User customer;

    private String authority;
    private String code;
    private String description;
    private String resultMessage;

    @ManyToOne
    private Payment payment;


}
