package net.mohamadi.App.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory>
//    redirectHttpToHttps() {
//        return factory ->
//                factory.addConnectorCustomizers(
//                        connector -> {
//                            connector.setScheme("http");
//                            connector.setPort(80);
//                            connector.setSecure(false);
//                            connector.setRedirectPort(443);
//                        }
//                );
//    }


    @Value("${app.cors.allowed-origins}")
    private List<String> allowedOrigins;

    // For Active CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins.toArray(new String[0]))
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);


    }
}
