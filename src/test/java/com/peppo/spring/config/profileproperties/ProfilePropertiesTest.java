package com.peppo.spring.config.profileproperties;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProfilePropertiesTest.TestApplication.class)
@ActiveProfiles({
        "production",
        "test"
})
public class ProfilePropertiesTest {

    @Autowired
    private TestApplication.ProfileProperties profileProperties;

    @Test
    void testProfileEnvironment() {
        Assertions.assertEquals("default", profileProperties.getDefaultFile());
        Assertions.assertEquals("production", profileProperties.getProductionFile());
        Assertions.assertEquals("test", profileProperties.getTestFile());
    }

    @SpringBootApplication
    public static class TestApplication {

        @Getter
        @Component
        public static class ProfileProperties {

            @Value("${spring.profile.default}")
            private String defaultFile;

            @Value("${spring.profile.production}")
            private String productionFile;

            @Value("${spring.profile.test}")
            private String testFile;

        }

    }
}
