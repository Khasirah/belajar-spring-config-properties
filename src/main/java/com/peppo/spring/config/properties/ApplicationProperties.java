package com.peppo.spring.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("application") // application adalah prefix, dapat diganti
// akan berdampak ke application.properties file bisa dilhat dengan cara mengetik application
// akan muncul hint
public class ApplicationProperties {

    private Date expireDate;
    private Duration defaultTimeout;
    private String name;
    private Integer version;
    private boolean productionMode;
    private DatabaseProperties database;
    private List<Role> defaultRoles;
    private Map<String, Role> roles; // pembuatan roles dengan tipe data map string objek Role

    @Getter
    @Setter
    public static class DatabaseProperties {


        private String username;
        private String password;
        private String database;
        private String url;
        private List<String> whiteListTables;
        private Map<String, Integer> MaxTablesSize;

    }

    @Getter
    @Setter
    public static class Role {

        private String id;
        private String name;

    }

}
