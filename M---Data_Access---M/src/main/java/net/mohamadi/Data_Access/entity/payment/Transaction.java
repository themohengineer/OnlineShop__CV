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
    private Invoice invoice;//ممکن است یک فاکتور چندین تراکنش داشته باشد (مثلاً تلاش‌های ناموفق،
    // بازگشت وجه، یا تراکنش‌های آزمایشی).

    @ManyToOne
    private User customer;

    private String authority;
    private String code;
    private String description;
    private String resultMessage;

    @ManyToOne
    private Payment payment;//یعنی هر تراکنش (Transaction) از یک
    // روش پرداخت (Payment) استفاده می‌کند، اما هر روش پرداخت
    // می‌تواند در چندین تراکنش به کار رود.


}
