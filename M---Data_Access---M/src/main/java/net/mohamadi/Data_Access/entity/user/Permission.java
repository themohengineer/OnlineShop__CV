package net.mohamadi.Data_Access.entity.user;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;//نام دسترسی
    // (مثلاً "READ", "WRITE", "DELETE" یا "ADMIN_PANEL").

    @Column(columnDefinition = "Text")
    private String description;


    //کد پایین یک رابطه خودارجاع (Self-Referencing) برای
    // ساختار درختی (مثل منوها یا سطوح دسترسی) تعریف می‌کند.


    /*
    این دو فیلد زیر هیچ ستونی در دیتابیس ایجاد نمی‌کند!
    دلیل:
    در دیتابیس، نمی‌توان یک شیء را در یک ستون ذخیره کرد.
فقط id آن شیء به عنوان کلید خارجی ذخیره می‌شود
     اسم این ستون را با @JoinColumn مشخص می‌کنیم.
    JPA پشت صحنه می‌رود و از روی parent_id که در دیتابیس ذخیره شده
    ، شیء Permission مربوطه را پیدا کرده و به تو برمی‌گرداند.
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
//ستون parent_id به خود جدول permission اشاره می‌کنه! یعنی یک کلید خارجی (Foreign Key) به id خودش.
    private Permission parent;   //هر Permission می‌تونه یک parent داشته باشه.

    @OneToMany(mappedBy = "parent")
    private List<Permission> children = new ArrayList<>();

}
