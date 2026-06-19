package net.mohamadi.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//حواست به org در خط پایین باشه
@ComponentScan(
        basePackages =
                {
                        "net.mohamadi"
                        //اینطوری مناسب و دقیق
                        //__________________________________________

                        /*
                        "org.*",
                        "net.*"
                        اینطوری خیلی کلی اسکن میکند که خوب نیست
                        کارایی پایین – اسپرینگ تمام پکیج‌های org.* و net.* را
                         اسکن می‌کند که شامل هزاران کلاس از کتابخانه‌های خود
                        اسپرینگ، لومبوک، جکسون و غیره می‌شود.
                         این کار زمان بوت برنامه را افزایش می‌دهد.
اسکن ناخواسته – ممکن است Beanهای سیستمی
                         (مثل org.springframework خودش) دوباره اسکن شوند و تداخل ایجاد کنند.
____________________________________________________________________________________________________


                       "net.mohamadi.app",
                       "net.mohamadi.service",
                      "net.mohamadi.dataaccess",
                       "net.mohamadi.config"
                         */
//                             اینطوری هم کاملا جزئی و دقیق !!

                }
)
@EntityScan(basePackages = {"net.mohamadi.Data_Access"})
@EnableJpaRepositories(basePackages = "net.mohamadi.Data_Access")


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
