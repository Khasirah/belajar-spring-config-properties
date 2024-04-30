package com.peppo.spring.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("application") // application adalah prefix, dapat diganti
// akan berdampak ke application.properties file bisa dilhat dengan cara mengetik application
// akan muncul hint
public class ApplicationProperties {

    private String name;
    private Integer version;
    private boolean productionMode;
    private DatabaseProperties database;

    @Getter
    @Setter
    public static class DatabaseProperties {

        private String username;
        private String password;
        private String database;
        private String url;
    }

}
