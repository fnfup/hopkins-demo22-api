package net.hopkins22.demoapi.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Component
public class RestCorsConfigSource extends UrlBasedCorsConfigurationSource {

        RestCorsConfigSource() {
            this.setCorsConfigurations(Collections.singletonMap("/**", corsConfig()));
            // this.setAlwaysUseFullPath(true);
        }

        private static CorsConfiguration corsConfig() {
            System.out.println("---!!!-- Custom CORS Configuration Read ---!!!--");
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedHeader("*");
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
            config.setAllowCredentials(true);
            config.addAllowedOrigin("*");
            config.setMaxAge(3600L);
            return config;
        }
}

