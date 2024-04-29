package com.peppo.spring.config.environment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class EnvironmentTest {

    @Autowired
    private Environment environment;

    @Test
    void testEnvironment() {
        String user = environment.getProperty("USER");
        Assertions.assertEquals("haris", user);
    }

    @SpringBootApplication
    public static class TestApplication {}

}
