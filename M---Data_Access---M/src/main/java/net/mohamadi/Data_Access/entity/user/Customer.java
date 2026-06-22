package net.mohamadi.Data_Access.entity.user;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users") //در دیتابیس Postgre کلمه user یک کلمه کلیدی است
// برای همین s اضافه کردیم
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 11, nullable = false)
    private String mobile;

    @Column(length = 500)
    private String firstName;

    @Column(length = 500)
    private String LastName;

    @Column(length = 20)
    private String tel;

    @Column(length = 1000)
    private String address;


    @Column(length = 20)
    private String postalCode;


}
