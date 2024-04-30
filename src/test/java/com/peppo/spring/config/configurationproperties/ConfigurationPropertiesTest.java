package com.peppo.spring.config.configurationproperties;

import com.peppo.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigurationPropertiesTest {

    @Autowired
    private ApplicationProperties applicationProperties;
    // auto bind krn otomatis dibuat oleh spring singleton

    @Test
    void testConfigurationProperties() {
        Assertions.assertEquals("belajar configuration properties", applicationProperties.getName());
        Assertions.assertEquals(false, applicationProperties.isProductionMode());
        Assertions.assertEquals(1, applicationProperties.getVersion());
    }

    @Test
    void testDatabaseProperties() {
        Assertions.assertEquals("belajar", applicationProperties.getDatabase().getDatabase());
        Assertions.assertEquals("haris", applicationProperties.getDatabase().getUsername());
        Assertions.assertEquals("rahasia", applicationProperties.getDatabase().getPassword());
        Assertions.assertEquals("jdbc:ipAddress", applicationProperties.getDatabase().getUrl());
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class
    })
    public static class TestApplication {}
}
