package net.mohamadi.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * کلاس پیکربندی برای ModelMapper.
 * ModelMapper یک کتابخانه برای تبدیل خودکار اشیاء (مثل Entity به DTO) است.
 * این Bean در سراسر برنامه قابل استفاده است و با @Autowired تزریق می‌شود.
 */

@Configuration
public class ModelMapperConfig {

    @Bean
     public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
