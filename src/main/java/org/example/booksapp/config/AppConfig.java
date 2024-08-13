package org.example.booksapp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    // for mysql
    @Bean
    @Qualifier("mysqldatasource")
    @ConfigurationProperties(prefix = "spring.ds.mysql")
    public DataSource mysqldatasource() {
        return DataSourceBuilder.create().build();
    }


    // for postgres
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.ds.pg")
    @Qualifier("psdatasource")
    public DataSource psdatasource() {
        return DataSourceBuilder.create().build();
    }
}
