package ru.otus.hw.service.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.config.TestConfig;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Answer;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("QuestionCsvReaderServiceTest | Question csv reader service ")
public class QuestionCsvReaderServiceTest {

    @Mock
    private TestConfig testConfig;
    @Mock
    private TestFileNameProvider testFileNameProvider;
    @InjectMocks
    private QuestionCsvReaderService questionCsvReaderService;

    private List<QuestionDto> expectedQuestionsDto;

    @BeforeEach
    public void init() {

        expectedQuestionsDto = Arrays.asList(
                new QuestionDto("first question?", Arrays.asList(
                        new Answer("first answer choice", true),
                        new Answer("second answer choice", false)
                )),
                new QuestionDto("second question?", Arrays.asList(
                        new Answer("first answer choice", false),
                        new Answer("second answer choice", true)
                ))
        );

        when(testFileNameProvider.getTestFileName()).thenReturn("questions-test.csv");
    }

    @Test
    @DisplayName("a correct file has been transferred 2 answers should be received")
    void getQuestions_givenValidCsvFile_expected2QuestionReturned() {

        when(testConfig.getCountSkipLinesInQuestionFile()).thenReturn(1);

        assertEquals(2, questionCsvReaderService.getQuestions(testFileNameProvider.getTestFileName()).size());
    }

    @Test
    @DisplayName("a correct file has been transferred but parameter SkipLines excess value 1 answer should be received 1")
    void getQuestions_givenParameterSkipLinesExcessValue_expected1QuestionReturned() {

        when(testConfig.getCountSkipLinesInQuestionFile()).thenReturn(2);

        assertEquals(1, questionCsvReaderService.getQuestions(testFileNameProvider.getTestFileName()).size());
    }

    @Test
    @DisplayName("questions should be correctly read from the file and laid out in dto")
    void getQuestions_givenValidCsvFile_expectedCorrectDto() {

        when(testConfig.getCountSkipLinesInQuestionFile()).thenReturn(1);
        List<QuestionDto> receivedQuestionsDto = questionCsvReaderService.getQuestions(testFileNameProvider.getTestFileName());

        assertEquals(expectedQuestionsDto, receivedQuestionsDto);
    }
}
