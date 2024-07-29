package ru.otus.hw.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties(value = AppProperties.class)
@ActiveProfiles("test")
@DisplayName("integration test of reading settings from a file")
class AppPropertiesTest {

    private static final int RIGHT_ANSWERS_COUNT_TO_PASS = 3;
    private static final int COUNTSKIP_LINES_IN_QUESTION_FILE = 1;
    private static final Locale TEST_LOCALE = Locale.forLanguageTag("ru-Ru");
    private static final String FILE_NAME_BY_LOCALE_TAG_RU_RU = "questions_ru_RU.csv";

    @Autowired
    private AppProperties appProperties;

    @Test
    @DisplayName("Correctly subtracted property rightAnswersCountToPass")
    void readCorrectProperty_rightAnswersCountToPass_FromYaml() {
        assertEquals(RIGHT_ANSWERS_COUNT_TO_PASS, appProperties.getRightAnswersCountToPass());
    }

    @Test
    @DisplayName("Correctly subtracted property countSkipLinesInQuestionFile")
    void readCorrectProperty_countSkipLinesInQuestionFile_FromYaml() {
        assertEquals(COUNTSKIP_LINES_IN_QUESTION_FILE, appProperties.getCountSkipLinesInQuestionFile());
    }

    @Test
    @DisplayName("Correctly subtracted property locale")
    void readCorrectProperty_locale_FromYaml() {
        assertEquals(TEST_LOCALE, appProperties.getLocale());
    }

    @Test
    @DisplayName("Correctly subtracted property getTestFileName")
    void readCorrectProperty_getTestFileName_FromYaml() {
        assertEquals(FILE_NAME_BY_LOCALE_TAG_RU_RU, appProperties.getTestFileName());
    }
}
