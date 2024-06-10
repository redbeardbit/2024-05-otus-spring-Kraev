package ru.otus.hw.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Setter;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Setter
public class QuestionCsvReaderService implements QuestionReaderService {

    private Integer skipLines;

    // Использовать CsvToBean
    // https://opencsv.sourceforge.net/#collection_based_bean_fields_one_to_many_mappings
    // Использовать QuestionReadException
    // Про ресурсы: https://mkyong.com/java/java-read-a-file-from-resources-folder/

    @Override
    public List<QuestionDto> getQuestions(String sourceName) {

        ClassLoader classLoader = getClass().getClassLoader();

        List<QuestionDto> questionDtoList;

        try (InputStream inputStream = classLoader.getResourceAsStream(sourceName);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {

            questionDtoList = new CsvToBeanBuilder(inputStreamReader)
                    .withType(QuestionDto.class)
                    .withSkipLines(skipLines)
                    .withSeparator(';')
                    .build().parse();

        } catch (Exception e) {
            throw new QuestionReadException("can't read questions from file " + sourceName, e);
        }

        return questionDtoList;
    }

}
