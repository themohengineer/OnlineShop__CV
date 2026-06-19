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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 11, nullable = false)
    private String mobile;

    @Column(length = 500)
    private String firstName;

    @Column(length = 500)
    private String LastName;

    @Column(length = 50, nullable = false)
    private String password;

    private String email;
    private LocalDateTime registerDate;
    private Boolean enable = true;

    @ManyToMany//یعنی بین User و Role رابطه چند به چند
    @JoinTable(name = "user_role",//برای ذخیره این ارتباط، یک جدول واسط در دیتابیس بساز به نام user_role
            joinColumns = @JoinColumn(name = "user_id"),//در جدول واسط، یک ستون به نام user_id بساز که به جدول User اشاره کند.
            inverseJoinColumns = @JoinColumn(name = "role_id"))//در همان جدول واسط، یک ستون به نام role_id بساز که به جدول Role اشاره کند.
    private Set<Role> roles;

    //اگر JoinTable نگذاریم بازهم
    // جدول واسط ساخته خواهد شد اما با نام پیش فرض

}
