package ru.otus.hw.service.reader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.hw.config.*;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Answer;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AppConfigTest.class)
@EnableConfigurationProperties(value = AppProperties.class)
@ActiveProfiles("test")
@DisplayName("QuestionCsvReaderServiceTest | Question csv reader service ")
class QuestionCsvReaderServiceTest {

    private static List<QuestionDto> expectedQuestionsDto;

    @MockBean
    private LocaleConfig localeConfig;
    @MockBean
    private TestConfig testConfig;
    @MockBean
    private TestFileNameProvider testFileNameProvider;
    @Autowired
    private QuestionCsvReaderService questionCsvReaderService;

    @BeforeAll
    public static void init() {

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
    }

    @BeforeEach
    public void init_common() {
        when(testConfig.getCountSkipLinesInQuestionFile()).thenReturn(1);
        when(testFileNameProvider.getTestFileName()).thenReturn("questions_en_UK.csv");
        when(localeConfig.getLocale()).thenReturn(Locale.forLanguageTag("ru-Ru"));
    }

    @Test
    @DisplayName("a correct file has been transferred 2 answers should be received")
    void getQuestions_givenValidCsvFile_expected2QuestionReturned() {
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

        List<QuestionDto> receivedQuestionsDto = questionCsvReaderService.getQuestions(testFileNameProvider.getTestFileName());

        assertThat(expectedQuestionsDto).hasSameElementsAs(receivedQuestionsDto);

    }
}
