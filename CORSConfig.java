package net.hopkins22.demoapi.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@EnableWebMvc
public class CORSConfig implements WebMvcConfigurer {

    @Override
    @Order(4)
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT")
                //.allowedHeaders("Content-Type", "Authorization")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(32400);  // 9 hours max age
    }
}
