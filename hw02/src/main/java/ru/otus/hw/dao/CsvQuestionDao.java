package ru.otus.hw.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.config.TestConfig;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.service.mapper.QuestionMapper;
import ru.otus.hw.service.reader.QuestionReaderService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final TestFileNameProvider fileNameProvider;

    private final TestConfig testConfig;

    private final QuestionMapper questionMapper;

    private final QuestionReaderService questionReaderService;

    @Override
    public List<Question> findAll() {

        List<QuestionDto> questionDtoList = questionReaderService.getQuestions(
                fileNameProvider.getTestFileName(),
                testConfig.getCountSkipLinesInQuestionFile()
        );

        return questionMapper.questionDtosToQuestions(questionDtoList);

    }
}
