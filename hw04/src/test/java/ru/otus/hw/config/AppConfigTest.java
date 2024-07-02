package ru.otus.hw.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class AppConfigTest {

    @MockBean
    private CommandLineRunner applicationRunner;
}
