package net.hopkins22.demoapi.configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    @Bean
    public DataSource getDataSource() {
        System.out.println("------ DB INIT PROPERTIES ------");
        System.out.println("host: " + System.getenv("AZ_DB_CSTR_HOST"));
        System.out.println("db: " + System.getenv("AZ_DB_CSTR_DB"));
        System.out.println("user: " + System.getenv("AZ_DB_CSTR_USER"));
        // System.out.println("pwd: " + System.getenv("AZ_DB_CSTR_PWD"));
        System.out.println("tenant: " + System.getenv("AZ_ID_TENANT"));
        System.out.println("client_id: " + System.getenv("AZ_ID_CLIENTID"));
        // System.out.println("client_secret: " + System.getenv("AZ_ID_SECRET"));
        // System.out.println("app_uri: " + System.getenv("AZ_ID_APPURI"));
        System.out.println("url: " + getDataSourceUrl());
        System.out.println("------ DB INIT PROPERTIES ------");

        return DataSourceBuilder.create()
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .url(getDataSourceUrl())
                .username(System.getenv("AZ_DB_CSTR_USER"))
                .password(System.getenv("AZ_DB_CSTR_PWD"))
                .build();
    }

    private String getDataSourceUrl() {
        return "jdbc:sqlserver://"
                + System.getenv("AZ_DB_CSTR_HOST")
                + ";database="
                + System.getenv("AZ_DB_CSTR_DB")
                + ";encrypt=true;trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    }

}
