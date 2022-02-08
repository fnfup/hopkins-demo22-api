package net.hopkins22.demoapi.configs;

import com.azure.spring.aad.webapp.AADWebSecurityConfigurerAdapter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoWebSecurityConfig extends AADWebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/login/**",
            "/logout/**",
            "/login**",
            "/logout**",
            "/auth**",
            "/oauth2/**",
            "/oauth2**",
    };

    @Bean
    //@Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.setAllowedOrigins(Arrays.asList(
                "https://localhost:4200", "http://localhost:4200",
                "http://localhost:8080", "https://localhost:8433",
                "http://localhost:1025",
                "http://hopkins-demo22-api-sc-hopkins-demo22-ui.azuremicroservices.io",
                "https://hopkins-demo22-api-sc-hopkins-demo22-ui.azuremicroservices.io"
                 ));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "POST", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList(
                "X-Requested-With", "Origin", "Content-Type",
                "Accept", "Authorization", "Access-Control-Request-Method",
                "User-Agent", "Host", "Referer"));
//        config.setExposedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        System.out.println("---!!! Setting up CORS config !!!---");
        return bean;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("Setting up web security");
        super.configure(http);
        http
                .cors();
//                .and()
//                .authorizeRequests()
//                .antMatchers(
//                        "/catalog**", "/user**", "/order**"
//                ).authenticated()
//                .antMatchers(AUTH_WHITELIST).permitAll();


        //                .requiresChannel()
//                .anyRequest().requiresInsecure()
//                .antMatchers("/login/oauth2/code/").requiresInsecure()
//                .anyRequest().requiresSecure()
//                .and()
    }


}
