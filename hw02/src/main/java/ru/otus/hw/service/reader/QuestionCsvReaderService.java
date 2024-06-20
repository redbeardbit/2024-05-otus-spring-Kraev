package ru.otus.hw.service.reader;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.config.TestConfig;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionCsvReaderService implements QuestionReaderService {

    private final TestConfig testConfig;

    @Override
    public List<QuestionDto> getQuestions(String sourceName) {

        ClassLoader classLoader = getClass().getClassLoader();

        List<QuestionDto> questionDtoList;

        try (InputStream inputStream = classLoader.getResourceAsStream(sourceName);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {

            questionDtoList = new CsvToBeanBuilder(inputStreamReader)
                    .withType(QuestionDto.class)
                    .withSkipLines(testConfig.getCountSkipLinesInQuestionFile())
                    .withSeparator(';')
                    .build().parse();

        } catch (Exception e) {
            throw new QuestionReadException("can't read questions from file " + sourceName, e);
        }

        return questionDtoList;
    }
}