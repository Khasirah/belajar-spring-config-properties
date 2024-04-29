package com.peppo.spring.config.value;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = ValueTest.TestApplication.class)
public class ValueTest {

    @Autowired
    private TestApplication.ApplicationProperties properties;
    @Autowired
    private TestApplication.SystemProperties systemProperties;

    @Test
    void testValue() {
        Assertions.assertEquals("ahmadhariskurniawan", properties.getName());
        Assertions.assertEquals(1, properties.getVersion());
        Assertions.assertEquals(false, properties.getProductionMode());
    }

    @Test
    void testSystemPropertiesR() {
        Assertions.assertEquals("haris", systemProperties.getUser());
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        @Getter
        public static class SystemProperties {

            @Value("${USER}")
            private String user;

        }

        @Component
        @Getter
        public static class ApplicationProperties {

            @Value("${spring.application.author}")
            private String name;
            // biasanya menggunakan @Autowired tapi autowired digunakan untuk mencari di application context
            // kita hanya butuh nilai yang ada di application properties jadi menggunakan @Value

            @Value("${spring.application.version}")
            private Integer version;

            @Value("${spring.application.productuion-mode}")
            private Boolean productionMode;

        }

    }
}
