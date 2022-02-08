package net.hopkins22.demoapi.configs;

import com.azure.spring.aad.webapp.AADWebSecurityConfigurerAdapter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

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
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Setting up web security");
        super.configure(http);
        http
//                .requiresChannel()
//                .anyRequest().requiresInsecure()
//                .and()
                // .antMatchers("/login/oauth2/code/").requiresInsecure()
//                .anyRequest().requiresSecure()
//                .and()
                .authorizeRequests()
//                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated();
    }


}
