package com.peppo.spring.config.configurationproperties;

import com.peppo.spring.config.converter.StringToDateConverter;
import com.peppo.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@SpringBootTest
public class ConfigurationPropertiesTest {

    @Autowired
    private ApplicationProperties applicationProperties;
    // auto bind krn otomatis dibuat oleh spring singleton
    
    @Autowired
    private ConversionService conversionService;

    @Test
    void testConversionService() {
        Assertions.assertTrue(conversionService.canConvert(String.class, Date.class));
        Assertions.assertTrue(conversionService.canConvert(String.class, Duration.class));

        Duration result = conversionService.convert("10s", Duration.class);
        Assertions.assertEquals(Duration.ofSeconds(10), result);
    }

    @Test
    void testConfigurationProperties() {
        Assertions.assertEquals("belajar configuration properties", applicationProperties.getName());
        Assertions.assertFalse(applicationProperties.isProductionMode());
        Assertions.assertEquals(1, applicationProperties.getVersion());
    }

    @Test
    void testDatabaseProperties() {
        Assertions.assertEquals("belajar", applicationProperties.getDatabase().getDatabase());
        Assertions.assertEquals("haris", applicationProperties.getDatabase().getUsername());
        Assertions.assertEquals("rahasia", applicationProperties.getDatabase().getPassword());
        Assertions.assertEquals("jdbc:ipAddress", applicationProperties.getDatabase().getUrl());
    }

    @Test
    void testCollection() {
        Assertions.assertEquals(Arrays.asList("products", "customers", "categories"), applicationProperties.getDatabase().getWhiteListTables());
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTablesSize().get("products"));
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTablesSize().get("customers"));
        Assertions.assertEquals(100, applicationProperties.getDatabase().getMaxTablesSize().get("categories"));
    }

    @Test
    void testEmbeddedCollection() {
        Assertions.assertEquals("default", applicationProperties.getDefaultRoles().get(0).getId());
        Assertions.assertEquals("guest role", applicationProperties.getDefaultRoles().get(1).getName());
        Assertions.assertEquals("admin", applicationProperties.getRoles().get("admin").getId());
        Assertions.assertEquals("finance", applicationProperties.getRoles().get("finance").getId());
    }

    @Test
    void testDuration() {
        Assertions.assertEquals(Duration.ofSeconds(10),applicationProperties.getDefaultTimeout());
    }

    @Test
    void testCustomConverter() {
        Date expireDate = applicationProperties.getExpireDate();

        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Assertions.assertEquals("2023-03-12", dateFormat.format(expireDate));
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class
    })
    @Import(StringToDateConverter.class)
    public static class TestApplication {

        @Bean
        public ConversionService conversionService(StringToDateConverter stringToDateConverter) {
            ApplicationConversionService conversionService = new ApplicationConversionService();
            conversionService.addConverter(stringToDateConverter);
            return conversionService;
        }

    }
}
