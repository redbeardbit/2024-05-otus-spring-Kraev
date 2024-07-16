package ru.otus.hw.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.otus.hw.domain.TestContext;

@Component
@Getter
@Setter
public class TestContextService {

    private TestContext testContext;

    public TestContextService() {
        this.testContext = new TestContext();
    }
}
