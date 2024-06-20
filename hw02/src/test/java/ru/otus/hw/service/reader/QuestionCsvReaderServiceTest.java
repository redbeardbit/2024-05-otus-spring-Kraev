package ru.otus.hw.service.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw.config.TestConfig;
import ru.otus.hw.config.TestFileNameProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("QuestionCsvReaderServiceTest | Question csv reader service ")
public class QuestionCsvReaderServiceTest {

    private TestConfig testConfig;
    private TestFileNameProvider testFileNameProvider;
    private QuestionCsvReaderService questionCsvReaderService;

    @BeforeEach
    public void init() {
        testConfig = mock(TestConfig.class);
        testFileNameProvider = mock(TestFileNameProvider.class);

        questionCsvReaderService = new QuestionCsvReaderService(testConfig);
    }

    @Test
    @DisplayName("a correct file has been transferred 2 answers should be received")
    void getQuestions_givenValidCsvFile_expected2QuestionReturned() {

        when(testFileNameProvider.getTestFileName()).thenReturn("questions-test.csv");
        when(testConfig.getCountSkipLinesInQuestionFile()).thenReturn(1);

        assertEquals(2, questionCsvReaderService.getQuestions(testFileNameProvider.getTestFileName()).size());
    }

    @Test
    @DisplayName("a correct file has been transferred but parameter SkipLines excess value 1 answer should be received 1")
    void getQuestions_givenParameterSkipLinesExcessValue_expected1QuestionReturned() {

        when(testFileNameProvider.getTestFileName()).thenReturn("questions-test.csv");
        when(testConfig.getCountSkipLinesInQuestionFile()).thenReturn(2);

        assertEquals(1, questionCsvReaderService.getQuestions(testFileNameProvider.getTestFileName()).size());
    }

}
