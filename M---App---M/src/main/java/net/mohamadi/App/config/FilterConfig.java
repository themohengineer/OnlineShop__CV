package net.mohamadi.App.config;


import jakarta.servlet.Filter;
import net.mohamadi.App.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


//این کلاس به اسپرینگ می‌گوید که حاوی Beanهای تنظیماتی (مثل فیلترها) است.
@Configuration
public class FilterConfig {

    private final JwtFilter jwtFilter;



    //تزریق وابستگی از طریق سازنده. فرض بر این است که JwtFilter خودش با
    // @Component (یا @Bean) تعریف شده تا اسپرینگ نمونه‌ای از آن را ساخته باشد
    @Autowired
    public FilterConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE) // اجرای فیلتر در بالاترین اولویت
    public FilterRegistrationBean<Filter> jwtFilterRegistration() {
        // ساخت شیء پیکربندی برای ثبت فیلتر
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        // تنظیم فیلتر JWT
        registrationBean.setFilter(jwtFilter);

        // مسیرهایی که فیلتر روی آنها اعمال می‌شود
        registrationBean.addUrlPatterns("/api/panel/*");

        // نام فیلتر در context
        registrationBean.setName("JwtFilter");

        // ترتیب اجرای فیلتر میان فیلترهای دیگر (کوچک‌تر = اول‌تر)
        registrationBean.setOrder(1);

        return registrationBean;
    }


}
